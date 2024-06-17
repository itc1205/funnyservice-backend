package com.funny.service.controller.news

import com.funny.service.controller.commentary.schema.CommentaryCreateRequest
import com.funny.service.controller.commentary.schema.CommentaryResponse
import com.funny.service.controller.commentary.schema.toCommentaryDTO
import com.funny.service.controller.commentary.schema.toCommentaryResponse
import com.funny.service.controller.news.schema.*
import com.funny.service.service.account.AccountService
import com.funny.service.service.commentary.CommentaryService
import com.funny.service.service.news.NewsService
import com.funny.service.service.tag.TagService
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.UUID

@RestController
@RequestMapping("/api/v1/news")
class NewsController(
    private val newsService: NewsService,
) {
    @GetMapping("/{slug}")
    fun getArticleBySlug(@PathVariable slug: String): ArticleResponse =
        newsService.getArticleBySlug(slug).toArticleResponse()

    @GetMapping("/list")
    fun getAllArticles(pageable: Pageable) : List<ArticleResponse> =
        newsService.getArticlesPaginated(pageable).stream().map { it.toArticleResponse() }.toList()
}