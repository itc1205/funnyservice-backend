package com.funny.service.persistence.entity

import jakarta.persistence.*
import java.util.*

@Entity(name = "tag")
data class TagEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID?,
    @Column(name = "name")
    val name: String
)