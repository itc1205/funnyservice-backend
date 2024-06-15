package com.funny.service.service.jwt

import org.springframework.security.core.userdetails.UserDetails

interface JwtService {
    fun extractLogin(token: String) : String
    fun extractId(token: String) : String
    fun isTokenValid(token: String, userDetails: UserDetails) : Boolean
    fun generateToken(userDetails: UserDetails) : String
}