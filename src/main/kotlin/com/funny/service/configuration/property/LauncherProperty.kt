package com.funny.service.configuration.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("launcher")
class LauncherProperty {
    lateinit var launcherPath: String
    lateinit var minecraftPath: String
}