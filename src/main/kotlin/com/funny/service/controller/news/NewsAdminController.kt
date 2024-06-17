package com.funny.service.controller.news

import com.funny.service.controller.news.schema.ArticleCreateRequest
import com.funny.service.controller.news.schema.ArticleUpdateRequest
import com.funny.service.controller.news.schema.toArticleDTO
import com.funny.service.service.account.AccountService
import com.funny.service.service.news.NewsService
import com.funny.service.service.tag.TagService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import java.util.*

@RestController
@RequestMapping("/api/v1/admin/news")
class NewsAdminController (
    private val newsService: NewsService,
    private val accountService: AccountService,
    private val tagService: TagService
) {
    @PostMapping("/")
    fun createNewArticle(@RequestBody article: ArticleCreateRequest, principal: Principal): UUID {
        val account = accountService.getByLogin(principal.name)
        val tags = tagService.findAllTags(article.tags)
        return newsService.createArticle(article.toArticleDTO(), tags, account.id!!)
    }

    @PutMapping("/")
    fun updateArticle(@RequestBody article: ArticleUpdateRequest) {
        val tags = tagService.findAllTags(article.tags)
        return newsService.updateArticle(article.toArticleDTO(), tags)
    }

    @DeleteMapping("/{id}")
    fun deleteArticle(@PathVariable id: UUID) {
        newsService.deleteArticle(id)
    }
}