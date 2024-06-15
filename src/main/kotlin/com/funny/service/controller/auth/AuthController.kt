package com.funny.service.controller.auth

import com.funny.service.service.auth.AuthService
import com.funny.service.service.auth.dto.JwtAuthResponse
import com.funny.service.service.auth.dto.SignInRequest
import com.funny.service.service.auth.dto.SignUpRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Аутентификация")
class AuthController (
    private val authService: AuthService
) {

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: @Valid SignUpRequest) {
        return authService.signUp(request)
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    fun signIn(@RequestBody request: @Valid SignInRequest): JwtAuthResponse {
        return authService.signIn(request)
    }
}
