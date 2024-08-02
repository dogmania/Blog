package com.example.presentation.screens.onboarding.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.request.AddUserRequestVo
import com.example.domain.usecase.auth.JoinUseCase
import com.example.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val joinUseCase: JoinUseCase
) : BaseViewModel<SignUpPageState>(SignUpPageState()) {

    fun updateEmail(email: String) {
        updateState(
            uiState.value.copy(
                email = email
            )
        )
    }

    fun updatePassword(password: String) {
        updateState(
            uiState.value.copy(
                password = password
            )
        )
    }

    fun join() {
        viewModelScope.launch {
            joinUseCase.invoke(
                AddUserRequestVo(uiState.value.email, uiState.value.password)
            )
            popScreen()
        }
    }

    private fun popScreen() {
        emitEventFlow(SignUpEvent.PopScreenEvent)
    }
}