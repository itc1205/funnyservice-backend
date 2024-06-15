package com.funny.service.service.tag

import com.funny.service.persistence.entity.TagEntity
import java.util.UUID

data class TagDTO(
    val id: UUID?,
    val name: String
)

fun TagEntity.toTagDTO() = TagDTO(
    id,
    name
)