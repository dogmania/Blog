package com.example.presentation.screens.blog.write

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.request.ArticleRequestVo
import com.example.domain.usecase.blog.CreateArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WriteBlogViewModel @Inject constructor(
    private val createArticleUseCase: CreateArticleUseCase
): ViewModel() {
    private val completeWriteArticleStateFlow = MutableStateFlow(false)
    val completeWriteArticle = completeWriteArticleStateFlow.asStateFlow()

    fun createArticle(title: String, content: String) {
        viewModelScope.launch {
            createArticleUseCase.invoke(ArticleRequestVo(title, content))
            completeWriteArticleStateFlow.update { true }
        }
    }
}