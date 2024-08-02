package com.example.presentation.screens.blog.detail

import androidx.lifecycle.viewModelScope
import com.example.domain.model.response.ArticleResponseVo
import com.example.domain.usecase.blog.DeleteArticleUseCase
import com.example.domain.usecase.blog.GetArticleUseCase
import com.example.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogDetailViewModel @Inject constructor(
    private val getArticleUseCase: GetArticleUseCase,
    private val deleteArticleUseCase: DeleteArticleUseCase
) : BaseViewModel<BlogDetailPageState>(BlogDetailPageState()) {

    fun getArticle(id: Long) {
        viewModelScope.launch {
            getArticleUseCase.invoke(id).collect {
                resultResponse(it, ::getArticleSuccessHandler)
            }
        }
    }

    private fun getArticleSuccessHandler(article: ArticleResponseVo) {
        updateState(
            uiState.value.copy(
                title = article.title,
                content = article.content
            )
        )
    }

    fun deleteArticle(id: Long) {
        viewModelScope.launch {
            deleteArticleUseCase.invoke(id)
            emitEventFlow(BlogDetailEvent.PopScreen)
        }
    }
}