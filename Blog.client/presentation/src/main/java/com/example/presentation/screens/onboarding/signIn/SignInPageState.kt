package com.example.presentation.screens.onboarding.signIn

import com.example.presentation.base.PageState

data class SignInPageState(
    val email: String = "",
    val password: String = ""
): PageState
