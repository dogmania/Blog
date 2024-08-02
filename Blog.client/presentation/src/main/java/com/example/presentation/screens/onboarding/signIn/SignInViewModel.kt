package com.example.presentation.screens.onboarding.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.request.LoginRequestVo
import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.usecase.auth.SaveTokenUseCase
import com.example.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveTokenUseCase: SaveTokenUseCase
): BaseViewModel<SignInPageState>(SignInPageState()) {

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

    fun login() {
        viewModelScope.launch {
            loginUseCase.invoke(
                LoginRequestVo(
                    email = uiState.value.email,
                    password = uiState.value.password
                )
            ).collect { response ->
                saveTokenUseCase.invoke(response.accessToken)
            }
            goToBlogMain()
        }
    }

    private fun goToBlogMain() {
        emitEventFlow(SignInEvent.GoToBlogMainEvent)
    }
}