package com.funny.service.service.jwt.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("jwt")
class JwtProperty {
    lateinit var key: String
    var accessTokenExpiration: Long = 0
    var refreshTokenExpiration: Long = 0
}