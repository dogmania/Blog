package com.example.data.datasource

import com.example.data.api.BlogService
import com.example.data.model.response.ArticleResponseDto
import com.example.domain.model.request.ArticleRequestVo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BlogDataSource @Inject constructor(
    private val blogService: BlogService
) {
    suspend fun getAllArticles() : Flow<List<ArticleResponseDto>> =  flow {
        emit(blogService.getAllArticles())
    }

    suspend fun createArticle(request: ArticleRequestVo) {
        blogService.createArticle(request)
    }

    suspend fun getArticle(request: Long): Flow<ArticleResponseDto> = flow {
        emit(blogService.getArticle(request))
    }
}