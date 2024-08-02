package com.example.data.repository

import android.util.Log
import com.example.data.datasource.BlogDataSource
import com.example.data.mapper.ArticleResponseMapper
import com.example.domain.model.request.ArticleRequestVo
import com.example.domain.model.response.ArticleResponseVo
import com.example.domain.repository.BlogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BlogRepositoryImpl @Inject constructor(
    private val blogDataSource: BlogDataSource,
    private val articleResponseMapper: ArticleResponseMapper
): BlogRepository {
    override suspend fun getAllArticles(): Flow<List<ArticleResponseVo>> {
        return blogDataSource.getAllArticles()
            .map { articleDto ->
                articleDto.map { articleResponseMapper.dtoToVo(it) }
            }
            .catch { e ->
                Log.e("BlogRepositoryImpl", e.stackTraceToString())
                emit(emptyList())
            }
    }

    override suspend fun createArticle(request: ArticleRequestVo) {
        kotlin.runCatching {
            blogDataSource.createArticle(request)
        }.onFailure { e ->
            Log.e("createArticle", e.stackTraceToString())
        }
    }

    override suspend fun getArticle(request: Long): Flow<ArticleResponseVo> {
        return blogDataSource.getArticle(request)
            .map {
                articleResponseMapper.dtoToVo(it)
            }
            .catch { e ->
                Log.e("BlogRepositoryImpl getArticle", e.stackTraceToString())
                emit(ArticleResponseVo())
            }

    }

    override suspend fun deleteArticle(request: Long) {
        kotlin.runCatching {
            blogDataSource.deleteArticle(request)
        }.onFailure { e ->
            Log.e("deleteArticle", e.stackTraceToString())
        }
    }
}