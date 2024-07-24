package com.example.domain.usecase.blog

import com.example.domain.model.request.ArticleRequestVo
import com.example.domain.repository.BlogRepository
import com.example.domain.usecase.base.UseCase
import javax.inject.Inject

class CreateArticleUseCase @Inject constructor(
    private val blogRepository: BlogRepository
): UseCase<ArticleRequestVo, Unit> {
    override suspend fun invoke(params: ArticleRequestVo) {
        blogRepository.createArticle(params)
    }
}