package com.example.presentation.screens.onboarding.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.request.AddUserRequestVo
import com.example.domain.usecase.auth.JoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val joinUseCase: JoinUseCase
) : ViewModel() {

    fun join(email: String, password: String) {
        viewModelScope.launch {
            joinUseCase.invoke(
                AddUserRequestVo(email, password)
            )
        }
    }
}