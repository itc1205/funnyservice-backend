package com.funny.service.persistence.repo

import com.funny.service.persistence.entity.CommentaryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface CommentaryRepository : JpaRepository<CommentaryEntity, UUID> {
    @Query("SELECT commentary FROM CommentaryEntity commentary WHERE commentary.article.slug = :articleSlug")
    fun getCommentariesFromArticleBySlug(articleSlug: String) : Set<CommentaryEntity>
}