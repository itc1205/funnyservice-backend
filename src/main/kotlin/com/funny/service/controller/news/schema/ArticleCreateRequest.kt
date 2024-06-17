package com.funny.service.controller.news.schema

import com.funny.service.service.news.ArticleDTO

data class ArticleCreateRequest(
    val title: String,
    val body: String,
    val tags: Set<String>,
)

fun ArticleCreateRequest.toArticleDTO() : ArticleDTO = ArticleDTO(
    null,
    null,
    title,
    body,
    null,
    null,
    null
)