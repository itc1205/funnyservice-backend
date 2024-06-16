package com.funny.service.controller.tag.schema

import com.funny.service.service.tag.TagDTO

data class TagResponse(
    val name: String
)

fun TagDTO.toTagResponse(): TagResponse = TagResponse(name)

