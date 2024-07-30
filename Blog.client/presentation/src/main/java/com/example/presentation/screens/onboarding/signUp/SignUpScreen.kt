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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.screens.onboarding.signIn.LoginTextField
import com.example.presentation.ui.theme.Main

@Composable
fun SignUpScreen(
    popScreen: () -> Unit
) {
    val viewModel: SignUpViewModel = hiltViewModel()
    val onClickBtnJoin = { email: String, password: String ->
        viewModel.join(email, password)
        popScreen()
    }

    SignUpContent(onClickBtnJoin)
}

@Composable
fun SignUpContent(
    onClickBtnJoin: (String, String) -> Unit
) {
    val email = remember{ mutableStateOf("") }
    val password = remember{ mutableStateOf("") }

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
            LoginTextField("아이디", email)
            Spacer(modifier = Modifier.height(5.dp))
            LoginTextField("비밀번호", password)
            Spacer(modifier = Modifier.height(5.dp))
            Button(
                onClick = { onClickBtnJoin(email.value, password.value) },
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
    SignUpContent { email: String, password: String -> Unit }
}