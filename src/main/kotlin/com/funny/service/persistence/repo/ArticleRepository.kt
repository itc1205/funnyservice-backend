package com.funny.service.persistence.repo

import com.funny.service.persistence.entity.ArticleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface ArticleRepository : JpaRepository<ArticleEntity, UUID>, PagingAndSortingRepository<ArticleEntity, UUID> {
    fun countBySlug(slug: String) : Long
    fun findBySlug(slug: String) : Optional<ArticleEntity>
}