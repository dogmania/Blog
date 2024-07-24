package com.example.domain.model.response

import java.time.LocalDateTime

data class ArticleResponseVo(
    val id: Long = -1,
    val title: String = "",
    val content: String = "",
    val createdAt: String,
    val updatedAt: String
)
