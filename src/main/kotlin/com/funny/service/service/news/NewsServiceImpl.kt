package com.funny.service.service.news

import com.funny.service.persistence.entity.ArticleEntity
import com.funny.service.persistence.repo.AccountRepository
import com.funny.service.persistence.repo.ArticleRepository
import com.funny.service.persistence.repo.TagRepository
import com.funny.service.service.account.exception.AccountNotFoundException
import com.funny.service.service.news.exception.ArticleNotFoundException
import com.funny.service.service.tag.TagDTO
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import java.util.stream.Collectors

@Service
class NewsServiceImpl(
    private val articleRepository: ArticleRepository,
    private val tagRepository: TagRepository,
    private val accountRepository: AccountRepository
) : NewsService {
    override fun createArticle(article: ArticleDTO, tags: Set<TagDTO>): UUID {

        val foundTags = tagRepository.findAllByName(tags.stream().map { it.name }.collect(Collectors.toSet()))
        val creatorAccount = accountRepository.findById(article.creatorId).orElseThrow { AccountNotFoundException() }
        val newArticle = ArticleEntity(
            id = null,
            createdBy = creatorAccount,
            body = article.body,
            tags = foundTags,
            commentaries = mutableSetOf(),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            title = article.title,
            slug = generateSlug(article.title)
        )
        return articleRepository.save(newArticle).id!!
    }

    override fun updateArticle(article: ArticleDTO) {
        val foundArticle = articleRepository.findById(article.id!!).orElseThrow { ArticleNotFoundException() }

        foundArticle.body = if (article.body != "") { article.body } else { foundArticle.body }
        foundArticle.title = if (article.title != "") { article.title } else { foundArticle.title }

        foundArticle.updatedAt = LocalDateTime.now()
        articleRepository.save(foundArticle)
    }

    override fun getArticleById(articleId: UUID): ArticleDTO {
        return articleRepository.findById(articleId).orElseThrow { ArticleNotFoundException() }.toArticleDTO()

    }

    override fun getArticlesPaginated(pageable: Pageable): List<ArticleDTO> {
        return articleRepository.findAll(pageable).stream().map {
            it.toArticleDTO()
        }.toList()
    }

    override fun deleteArticle(articleId: UUID) {
        articleRepository.deleteById(articleId)
    }

    override fun getArticleBySlug(slug: String): ArticleDTO {
        return articleRepository.findBySlug(slug).orElseThrow { ArticleNotFoundException() }.toArticleDTO()
    }

    private fun generateSlug(title: String) : String {
        var slug = title
            .lowercase()
            .trim()
            .replace(' ', '-')

        val sameSlugCount = articleRepository.countBySlug(slug)

        if (sameSlugCount > 0) {
            slug = slug.plus("-$sameSlugCount")
        }
        return slug
    }
}