package com.example.presentation.screens.onboarding.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.request.LoginRequestVo
import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.usecase.auth.SaveTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveTokenUseCase: SaveTokenUseCase
): ViewModel() {

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase.invoke(
                LoginRequestVo(
                    email = email,
                    password = password
                )
            ).collect { response ->
                saveTokenUseCase.invoke(response.accessToken)
            }
        }
    }
}