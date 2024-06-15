package com.funny.service.service.tag

import com.funny.service.persistence.repo.TagRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TagServiceImpl (
    private val tagRepository: TagRepository
) : TagService {
    override fun findAllTags(tagNames: Set<String>): Set<TagDTO> {
        return tagRepository.findAllByName(tagNames).stream().map {
            it.toTagDTO()
        }.collect(Collectors.toSet())
    }
}