package com.example.data.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serial
import java.time.LocalDateTime

data class ArticleResponseDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("content")
    val content: String = "",
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)
