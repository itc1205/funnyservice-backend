package com.funny.service.persistence.repo

import com.funny.service.persistence.entity.TagEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface TagRepository : JpaRepository<TagEntity, UUID> {
    @Query("SELECT tag FROM TagEntity tag WHERE tag.name IN :names")
    fun findAllByName(names: Set<String>) : MutableList<TagEntity>
}