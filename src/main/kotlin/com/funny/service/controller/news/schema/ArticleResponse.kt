package com.funny.service.controller.news.schema

import com.funny.service.service.news.ArticleDTO
import java.time.LocalDateTime
import java.util.*

data class ArticleResponse (
    val id: UUID,
    val creatorId: UUID,
    val title: String,
    val body: String,
    val slug: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

fun ArticleDTO.toArticleResponse() : ArticleResponse = ArticleResponse(
    id!!,
    creatorId!!,
    title,
    body,
    slug!!,
    createdAt!!,
    updatedAt!!
)