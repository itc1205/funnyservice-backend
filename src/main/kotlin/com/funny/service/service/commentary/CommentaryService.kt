package com.funny.service.service.commentary

import java.util.*

interface CommentaryService {
    fun createCommentary(articleSlug: String, commentary: CommentaryDTO)
    fun deleteCommentary(commentaryId: UUID)
    fun updateCommentary(commentaryId: UUID, articleSlug: String, commentaryBody: String)
    fun getCommentariesFromArticle(articleSlug: String) : Set<CommentaryDTO>
}
