package com.example.presentation.screens.onboarding.signUp

import com.example.presentation.base.Event

sealed class SignUpEvent: Event {
    data object PopScreenEvent : SignUpEvent()
}