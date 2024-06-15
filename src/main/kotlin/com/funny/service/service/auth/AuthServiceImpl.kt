package com.funny.service.service.auth

import com.funny.service.persistence.entity.Role
import com.funny.service.service.account.AccountDTO
import com.funny.service.service.account.AccountService
import com.funny.service.service.auth.dto.JwtAuthResponse
import com.funny.service.service.auth.dto.SignInRequest
import com.funny.service.service.auth.dto.SignUpRequest
import com.funny.service.service.jwt.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val accountService: AccountService,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager
) : AuthService {
    override fun signUp(signUpRequest: SignUpRequest) {
        val account = AccountDTO(
            id = null,
            login = signUpRequest.login,
            hashedPassword = passwordEncoder.encode(signUpRequest.password),
            isOnline = false,
            status = "",
            role = Role.USER
        )
        accountService.createAccount(account)
    }

    override fun signIn(signInRequest: SignInRequest) : JwtAuthResponse {
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(
            signInRequest.login,
            signInRequest.password
        ))

        val account = accountService
            .userDetailsService()
            .loadUserByUsername(signInRequest.login)

        val jwt = jwtService.generateToken(account)
        return JwtAuthResponse(jwt)
    }
}