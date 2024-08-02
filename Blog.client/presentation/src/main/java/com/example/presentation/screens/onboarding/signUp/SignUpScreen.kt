package com.example.presentation.screens.onboarding.signUp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.base.Event
import com.example.presentation.screens.onboarding.signIn.LoginTextField
import com.example.presentation.ui.theme.Main
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SignUpScreen(
    popScreen: () -> Unit
) {
    val viewModel: SignUpViewModel = hiltViewModel()
    val onClickBtnJoin = {
        viewModel.join()
    }
    
    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event: Event ->
            when(event) {
                is SignUpEvent.PopScreenEvent -> popScreen()
            }
        }
    }

    SignUpContent(
        onClickBtnJoin = onClickBtnJoin,
        updateEmail = { email: String ->
            viewModel.updateEmail(email)
        },
        updatePassword = { password: String ->
            viewModel.updatePassword(password)
        }
    )
}

@Composable
fun SignUpContent(
    onClickBtnJoin: () -> Unit = {},
    updateEmail: (String) -> Unit = {},
    updatePassword: (String) -> Unit = {}
) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            LoginTextField("아이디", updateEmail)
            Spacer(modifier = Modifier.height(5.dp))
            LoginTextField("비밀번호", updatePassword)
            Spacer(modifier = Modifier.height(5.dp))
            Button(
                onClick = { onClickBtnJoin() },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Main
                )
            ) {
                Text(
                    text = "회원가입"
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignUpScreenPreview() {
    SignUpContent()
}