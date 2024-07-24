package com.example.data.api

import com.example.data.model.response.ArticleResponseDto
import retrofit2.http.GET

interface BlogService {
    @GET("/api/articles")
    suspend fun getAllArticles() : List<ArticleResponseDto>
}