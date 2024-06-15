package com.funny.service.service.commentary

import com.funny.service.persistence.entity.CommentaryEntity
import com.funny.service.persistence.repo.AccountRepository
import com.funny.service.persistence.repo.ArticleRepository
import com.funny.service.persistence.repo.CommentaryRepository
import com.funny.service.service.account.exception.AccountNotFoundException
import com.funny.service.service.commentary.exception.CommentaryNotFoundException
import com.funny.service.service.news.exception.ArticleNotFoundException
import java.time.LocalDateTime
import java.util.*
import java.util.stream.Collectors

class CommentaryServiceImpl(
    private val articleRepository: ArticleRepository,
    private val accountRepository: AccountRepository,
    private val commentaryRepository: CommentaryRepository
) : CommentaryService {

    override fun createCommentary(articleSlug: String, commentary: CommentaryDTO) {
        val article = articleRepository.findBySlug(articleSlug).orElseThrow { ArticleNotFoundException() }
        val creator = accountRepository.findById(commentary.creatorId).orElseThrow { AccountNotFoundException() }

        val newCommentary = CommentaryEntity(
            null,
            article,
            creator,
            commentary.body,
            LocalDateTime.now(),
            LocalDateTime.now()
        )

        article.commentaries.add(newCommentary)

        commentaryRepository.save(newCommentary)
    }

    override fun deleteCommentary(commentaryId: UUID) {
        commentaryRepository.deleteById(commentaryId)
    }

    override fun updateCommentary(commentaryId: UUID, articleSlug: String, commentaryBody: String) {
        val commentary = commentaryRepository.findById(commentaryId).orElseThrow { CommentaryNotFoundException() }
        commentary.body = commentaryBody
        commentaryRepository.save(commentary)
    }

    override fun getCommentariesFromArticle(articleSlug: String): Set<CommentaryDTO> {
        return commentaryRepository.getCommentariesFromArticleBySlug(articleSlug).stream().map {
            it.toCommentaryDTO()
        }.collect(Collectors.toSet())
    }
}