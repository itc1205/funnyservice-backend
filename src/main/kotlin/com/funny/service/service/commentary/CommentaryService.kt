package com.funny.service.service.commentary

import java.util.*

interface CommentaryService {
    fun createCommentary(commentary: CommentaryDTO) : UUID
    fun deleteCommentary(commentaryId: UUID)
    fun updateCommentary(commentaryId: UUID, commentaryBody: String)
    fun getById(commentaryId: UUID) : CommentaryDTO
    fun getCommentariesFromArticle(articleSlug: String) : Set<CommentaryDTO>
}
