package com.funny.service.service.commentary

import com.funny.service.persistence.entity.CommentaryEntity
import com.funny.service.persistence.repo.AccountRepository
import com.funny.service.persistence.repo.ArticleRepository
import com.funny.service.persistence.repo.CommentaryRepository
import com.funny.service.service.account.exception.AccountNotFoundException
import com.funny.service.service.commentary.exception.CommentaryNotFoundException
import com.funny.service.service.news.exception.ArticleNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import java.util.stream.Collectors

@Service
class CommentaryServiceImpl(
    private val articleRepository: ArticleRepository,
    private val accountRepository: AccountRepository,
    private val commentaryRepository: CommentaryRepository
) : CommentaryService {

    override fun createCommentary(commentary: CommentaryDTO) : UUID {
        val article = articleRepository.findById(commentary.articleId).orElseThrow { ArticleNotFoundException() }
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

        return commentaryRepository.save(newCommentary).id!!
    }

    override fun deleteCommentary(commentaryId: UUID) {
        commentaryRepository.deleteById(commentaryId)
    }

    override fun updateCommentary(commentaryId: UUID, commentaryBody: String) {
        val commentary = commentaryRepository.findById(commentaryId).orElseThrow { CommentaryNotFoundException() }
        commentary.body = commentaryBody
        commentaryRepository.save(commentary)
    }

    override fun getById(commentaryId: UUID): CommentaryDTO {
        return commentaryRepository.findById(commentaryId).orElseThrow { CommentaryNotFoundException() }.toCommentaryDTO()
    }

    override fun getCommentariesFromArticle(articleSlug: String): Set<CommentaryDTO> {
        return commentaryRepository.getCommentariesFromArticleBySlug(articleSlug).stream().map {
            it.toCommentaryDTO()
        }.collect(Collectors.toSet())
    }
}