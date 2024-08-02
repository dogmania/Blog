package com.example.presentation.screens.onboarding.signIn

import com.example.presentation.base.Event

sealed class SignInEvent: Event {
    data object GoToBlogMainEvent: SignInEvent()
}