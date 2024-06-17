package com.funny.service.controller.launcher

import com.funny.service.configuration.property.LauncherProperty
import com.funny.service.support.checksum.hashFile
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.FileInputStream
import java.nio.file.Path
import kotlin.io.path.Path


@RestController
@RequestMapping("/api/v1/launcher")
class LauncherController (
) {

    @Value("file:\${launcher.launcher-path}")
    lateinit var launcherResource: Resource

    @Value("file:\${launcher.minecraft-path}")
    lateinit var minecraftResource: Resource


    @GetMapping("/minecraft/checksum")
    fun getMinecraftCheckSum(): String {
        return hashFile(minecraftResource.file)
    }

    @GetMapping("/version")
    fun getLauncherVersion(): String {
        return "0.0.2"
    }

    @GetMapping("/minecraft")
    fun getMinecraft(): ResponseEntity<InputStreamResource> {
        val file = minecraftResource.file
        val headers = HttpHeaders()
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=${minecraftResource.filename}")


        val resource = InputStreamResource(FileInputStream(file))

        return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource)
    }

    @GetMapping("/")
    fun getLauncher() : ResponseEntity<InputStreamResource> {
        val file = launcherResource.file
        val resource = InputStreamResource(FileInputStream(file))

        val headers = HttpHeaders()
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=${launcherResource.filename}")

        return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource)
    }
}