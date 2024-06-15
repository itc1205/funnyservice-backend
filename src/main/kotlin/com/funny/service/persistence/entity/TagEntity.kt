package com.funny.service.persistence.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "tag")
class TagEntity (
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID?,
    @Column(name = "name")
    var name: String,
)