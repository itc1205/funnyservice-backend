package com.funny.service.persistence.entity

import jakarta.persistence.*
import java.util.*

@Entity(name = "account")
data class AccountEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID?,
    @Column(name = "login", unique = true)
    val login: String,
    @Column(name = "hashed_password")
    val hashedPassword: String,
    @Column(name = "status")
    var status: String,
    @Column(name = "online")
    var isOnline: Boolean,
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    val role: Role
)