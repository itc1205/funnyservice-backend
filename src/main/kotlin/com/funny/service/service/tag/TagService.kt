package com.funny.service.service.tag

interface TagService {
    fun findAllTags(tagNames: Set<String>) : Set<TagDTO>
}