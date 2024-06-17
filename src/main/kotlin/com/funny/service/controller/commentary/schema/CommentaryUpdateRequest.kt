package com.funny.service.controller.commentary.schema

import java.util.*

data class CommentaryUpdateRequest(
    val id: UUID,
    val body: String
)