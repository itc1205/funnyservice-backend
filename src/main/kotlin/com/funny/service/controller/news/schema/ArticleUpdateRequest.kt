package com.funny.service.controller.news.schema

import com.funny.service.service.news.ArticleDTO
import java.util.UUID

data class ArticleUpdateRequest (
    val id: UUID,
    val title: String,
    val body: String,
    val tags: Set<String>
)

fun ArticleUpdateRequest.toArticleDTO() : ArticleDTO = ArticleDTO(
    id,
    null,
    title,
    body,
    null,
    null,
    null
)