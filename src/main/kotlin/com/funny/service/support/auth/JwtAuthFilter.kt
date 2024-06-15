package com.funny.service.support.auth

import com.funny.service.service.account.AccountService
import com.funny.service.service.jwt.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    val jwtService: JwtService,
    val accountService: AccountService,
) : OncePerRequestFilter() {
    private val BEARER_PREFIX = "Bearer "
    private val HEADER_NAME = "Authorization"

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader(HEADER_NAME)

        if (authHeader == null) {
            filterChain.doFilter(request, response)
            return
        }

        if (authHeader.isEmpty() || !authHeader.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response)
            return
        }

        val jwt = authHeader.substring(BEARER_PREFIX.length)
        val login = jwtService.extractLogin(jwt)

        if (login.isEmpty()) {
            filterChain.doFilter(request, response)
            return
        }

        if (SecurityContextHolder.getContext().authentication != null) {
            filterChain.doFilter(request, response)
            return
        }

        val userDetails = accountService.userDetailsService().loadUserByUsername(login)

        if (!jwtService.isTokenValid(jwt, userDetails)) {
            filterChain.doFilter(request, response)
            return
        }

        val securityContext = SecurityContextHolder.createEmptyContext()

        val authToken = UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.authorities
        )

        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
        securityContext.authentication = authToken
        SecurityContextHolder.setContext(securityContext)

        filterChain.doFilter(request, response)
    }
}