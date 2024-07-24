package com.example.data.api

import com.example.data.model.response.ArticleResponseDto
import com.example.domain.model.request.ArticleRequestVo
import com.example.domain.model.response.ArticleResponseVo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BlogService {
    @GET("/api/articles")
    suspend fun getAllArticles() : List<ArticleResponseDto>

    @POST("/api/articles")
    suspend fun createArticle(@Body request: ArticleRequestVo)
}