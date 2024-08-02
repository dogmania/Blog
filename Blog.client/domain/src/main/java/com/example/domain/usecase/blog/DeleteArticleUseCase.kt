package com.example.domain.usecase.blog

import com.example.domain.repository.BlogRepository
import com.example.domain.usecase.base.UseCase
import javax.inject.Inject

class DeleteArticleUseCase @Inject constructor(
    private val blogRepository: BlogRepository
): UseCase<Long, Unit> {
    override suspend fun invoke(params: Long) {
        blogRepository.deleteArticle(params)
    }
}