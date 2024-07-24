package com.example.presentation.screens.blog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.response.ArticleResponseVo
import com.example.domain.usecase.blog.GetAllArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogMainViewModel @Inject constructor(
    private val getAllArticlesUseCase: GetAllArticlesUseCase
): ViewModel() {
    private val allArticleListStateFlow: MutableStateFlow<List<ArticleResponseVo>> = MutableStateFlow(emptyList())
    val allArticleList = allArticleListStateFlow.asStateFlow()

    fun getAllArticles() {
        viewModelScope.launch {
            getAllArticlesUseCase.invoke(Unit).collect { articleList ->
                allArticleListStateFlow.update { articleList }
            }
        }
    }
}