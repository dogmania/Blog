package com.example.presentation.screens.blog.detail

import com.example.presentation.base.Event

sealed class BlogDetailEvent: Event {
    data object PopScreen: BlogDetailEvent()
}