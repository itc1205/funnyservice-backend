package com.funny.service.service.news

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface NewsService {
    fun createArticle(article: ArticleDTO) : UUID
    fun updateArticle(article: ArticleDTO)
    fun getArticleById(articleId: UUID) : ArticleDTO
    fun getArticlesPaginated(pageable: Pageable): Page<ArticleDTO>
    fun deleteArticle(articleId: UUID)
}