package com.funny.service.service.jwt

interface JwtService {
    fun extractLogin(token: String): String
    fun generateToken(): String
    fun isTokenValid(token: String): Boolean
}