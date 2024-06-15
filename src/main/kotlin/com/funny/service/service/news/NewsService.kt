package com.funny.service.service.news

import com.funny.service.service.tag.TagDTO
import org.springframework.data.domain.Pageable
import java.util.UUID

interface NewsService {
    fun createArticle(article: ArticleDTO, tags: Set<TagDTO>) : UUID
    fun updateArticle(article: ArticleDTO)
    fun getArticleById(articleId: UUID) : ArticleDTO
    fun getArticlesPaginated(pageable: Pageable): List<ArticleDTO>
    fun deleteArticle(articleId: UUID)
    fun getArticleBySlug(slug: String) : ArticleDTO
}