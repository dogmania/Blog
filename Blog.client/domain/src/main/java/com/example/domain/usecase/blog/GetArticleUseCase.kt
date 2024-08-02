package com.example.domain.usecase.blog

import com.example.domain.model.response.ArticleResponseVo
import com.example.domain.repository.BlogRepository
import com.example.domain.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticleUseCase @Inject constructor(
    private val blogRepository: BlogRepository
): UseCase<Long, Flow<ArticleResponseVo>> {
    override suspend fun invoke(params: Long): Flow<ArticleResponseVo> {
        return blogRepository.getArticle(params)
    }
}