package com.funny.service.service.news

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

class NewsServiceImpl : NewsService {
    override fun createArticle(article: ArticleDTO): UUID {
        TODO("Not yet implemented")
    }

    override fun updateArticle(article: ArticleDTO) {
        TODO("Not yet implemented")
    }

    override fun getArticleById(articleId: UUID): ArticleDTO {
        TODO("Not yet implemented")
    }

    override fun getArticlesPaginated(pageable: Pageable): Page<ArticleDTO> {
        TODO("Not yet implemented")
    }

    override fun deleteArticle(articleId: UUID) {
        TODO("Not yet implemented")
    }
}