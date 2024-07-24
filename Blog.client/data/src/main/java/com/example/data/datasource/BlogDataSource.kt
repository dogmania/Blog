package com.example.data.datasource

import com.example.data.api.BlogService
import com.example.data.model.response.ArticleResponseDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BlogDataSource @Inject constructor(
    private val blogService: BlogService
) {
    suspend fun getAllArticles() : Flow<List<ArticleResponseDto>> =  flow {
        emit(blogService.getAllArticles())
    }
}