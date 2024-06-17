package com.funny.service.controller.commentary

import com.funny.service.controller.commentary.schema.*
import com.funny.service.service.account.AccountService
import com.funny.service.service.auth.exception.InsufficientRightsException
import com.funny.service.service.commentary.CommentaryService
import com.funny.service.service.news.NewsService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import java.util.UUID

@RestController
@RequestMapping("api/v1/admin/commentary")
class CommentaryAdminController (
    val commentaryService: CommentaryService,
    val accountService: AccountService,
    val newsService: NewsService
) {

    @PostMapping("/forSlug/{slug}")
    fun createCommentary(@RequestBody commentary: CommentaryCreateRequest, @PathVariable slug: String, principal: Principal) : UUID {
        val currentUser = accountService.getByLogin(principal.name)
        val article = newsService.getArticleBySlug(slug)
        return commentaryService.createCommentary(commentary.toCommentaryDTO(currentUser.id!!, article.id!!))
    }

    @PutMapping("/")
    fun updateCommentary(@RequestBody commentary: CommentaryUpdateRequest, principal: Principal) {
        val foundCommentary = commentaryService.getById(commentary.id)
        commentaryService.updateCommentary(foundCommentary.id!!, foundCommentary.body)
    }

    @DeleteMapping("/{id}")
    fun deleteCommentary(@RequestBody id: UUID) {
        commentaryService.deleteCommentary(id)
    }
}