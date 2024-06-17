package com.funny.service.controller.commentary.schema

import com.funny.service.service.commentary.CommentaryDTO
import java.util.UUID

data class CommentaryCreateRequest (
    val articleId: UUID,
    val body: String,
)

fun CommentaryCreateRequest.toCommentaryDTO(creatorId: UUID, articleId: UUID) : CommentaryDTO = CommentaryDTO(
    null,
    creatorId,
    articleId,
    body,
    null,
    null
)