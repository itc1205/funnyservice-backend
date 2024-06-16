package com.funny.service.controller.tag

import com.funny.service.controller.tag.schema.TagResponse
import com.funny.service.controller.tag.schema.toTagResponse
import com.funny.service.service.tag.TagService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.stream.Collectors

@RestController
@RequestMapping("api/v1/tag")
class TagController(
    private val tagService: TagService
) {
    @GetMapping("/")
    fun getTagByNames(names: Set<String>) : Set<TagResponse> {
        return tagService.findAllTags(names).stream().map {
            it.toTagResponse()
        }.collect(Collectors.toSet())
    }
}


