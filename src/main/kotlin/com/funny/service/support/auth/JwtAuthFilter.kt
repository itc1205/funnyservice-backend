package com.funny.service.support.auth

import com.funny.service.service.account.AccountService
import com.funny.service.service.jwt.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    val jwtService: JwtService,
    val accountService: AccountService
) : OncePerRequestFilter() {
    final val BEARER_PREFIX = "Bearer ";
    final val HEADER_NAME = "Authorization";

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        TODO("Not yet implemented")
        filterChain.doFilter(request, response)
    }
}