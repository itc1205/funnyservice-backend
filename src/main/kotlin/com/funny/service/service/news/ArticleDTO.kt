package com.funny.service.service.news

import com.funny.service.persistence.entity.ArticleEntity
import java.time.LocalDateTime
import java.util.UUID

data class ArticleDTO (
    val id: UUID?,
    val creatorId: UUID,
    val title: String,
    val body: String,
    val slug: String?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

fun ArticleEntity.toArticleDTO() : ArticleDTO = ArticleDTO(
    id,
    creatorId = this.createdBy.id!!,
    title,
    body,
    slug,
    createdAt,
    updatedAt
)
