package com.example.presentation.screens.onboarding.signUp

import com.example.presentation.base.PageState

data class SignUpPageState(
    val email: String = "",
    val password: String = ""
): PageState
