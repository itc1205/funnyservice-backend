package com.funny.service.controller.commentary.schema

import com.funny.service.service.commentary.CommentaryDTO
import java.time.LocalDateTime
import java.util.*

data class CommentaryResponse(
    val id: UUID,
    val creatorId: UUID,
    val articleId: UUID,
    val body: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

fun CommentaryDTO.toCommentaryResponse() : CommentaryResponse = CommentaryResponse(
    id!!,
    creatorId,
    articleId,
    body,
    createdAt!!,
    updatedAt!!
)
