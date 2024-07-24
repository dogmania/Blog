package com.example.domain.repository

import com.example.domain.model.request.ArticleRequestVo
import com.example.domain.model.response.ArticleResponseVo
import kotlinx.coroutines.flow.Flow

interface BlogRepository {
    suspend fun getAllArticles(): Flow<List<ArticleResponseVo>>
    suspend fun createArticle(request: ArticleRequestVo)
}