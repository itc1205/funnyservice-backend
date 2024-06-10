package com.funny.service.persistence.entity

import jakarta.persistence.*
import org.hibernate.annotations.BatchSize
import java.time.LocalDateTime
import java.util.UUID

@Entity(name = "article")
data class ArticleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID?,
    @Column(name = "title")
    val title: String,
    @Column(name = "data")
    val data: String,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tag_article")
    @BatchSize(size = 50)
    val tags: Set<TagEntity>,

    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime,
)
