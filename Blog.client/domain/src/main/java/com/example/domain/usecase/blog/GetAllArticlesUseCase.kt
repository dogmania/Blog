package com.example.domain.usecase

import com.example.domain.model.response.ArticleResponseVo
import com.example.domain.repository.BlogRepository
import com.example.domain.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllArticlesUseCase @Inject constructor(
    private val blogRepository: BlogRepository
): UseCase<Unit, Flow<List<ArticleResponseVo>>> {
    override suspend fun invoke(params: Unit): Flow<List<ArticleResponseVo>> {
        return blogRepository.getAllArticles()
    }
}