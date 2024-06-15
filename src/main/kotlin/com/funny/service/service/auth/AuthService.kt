package com.funny.service.service.auth

import com.funny.service.service.auth.dto.JwtAuthResponse
import com.funny.service.service.auth.dto.SignInRequest
import com.funny.service.service.auth.dto.SignUpRequest

interface AuthService {
    fun signUp(signUpRequest: SignUpRequest)
    fun signIn(signInRequest: SignInRequest) : JwtAuthResponse
}