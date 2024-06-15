package com.funny.service.service.jwt

import com.funny.service.service.account.AccountDTO
import com.funny.service.service.jwt.property.JwtProperty
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.Date
import java.util.function.Function


@Service
class JwtServiceImpl(
    private val jwtProperty: JwtProperty
) : JwtService {
    override fun extractLogin(token: String): String = extractClaim(token, Claims::getSubject)

    override fun extractId(token: String): String = extractClaim(token, Claims::getId)

    override fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractLogin(token)
        return (username == userDetails.username && !isTokenExpired(token))
    }

    override fun generateToken(userDetails: UserDetails): String {
        var claims: Map<String, Any>  = HashMap()
        if (userDetails is AccountDTO) {
            claims = claims.plus(Pair("id", userDetails.id as Any))
            claims = claims.plus(Pair("role", userDetails.role as Any))
        }
        return generateTokenWithClaims(claims, userDetails)
    }

    private fun generateTokenWithClaims(extraClaims: Map<String, Any>, userDetails: UserDetails) : String {
        return Jwts.builder()
            .claims()
            .add(extraClaims)
            .subject(userDetails.username)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + jwtProperty.accessTokenExpiration))
            .and()
            .signWith(Keys.hmacShaKeyFor(getSignKey()))
            .compact()
    }

    private fun getSignKey() = jwtProperty.key.toByteArray()

    private fun isTokenExpired(token: String) : Boolean {
        val date = extractClaim(token, Claims::getExpiration)
        return date.before(Date())
    }

    private fun <T> extractClaim(token: String, claimResolver: Function<Claims, T>): T = claimResolver.apply(extractAllClaims(token))

    private fun extractAllClaims(token: String) : Claims {
        return Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(getSignKey()))
            .build()
            .parseSignedClaims(token)
            .payload
    }
}