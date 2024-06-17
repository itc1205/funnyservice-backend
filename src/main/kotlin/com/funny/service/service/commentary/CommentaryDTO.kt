package com.funny.service.service.commentary

import com.funny.service.persistence.entity.CommentaryEntity
import java.time.LocalDateTime
import java.util.*

data class CommentaryDTO (
    val id: UUID?,
    val creatorId: UUID,
    val articleId: UUID,
    val body: String,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
)
fun CommentaryEntity.toCommentaryDTO() = CommentaryDTO(
    id,
    creatorId = this.createdBy.id!!,
    articleId = this.article.id!!,
    body,
    createdAt,
    updatedAt
)