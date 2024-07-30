package com.example.presentation.screens.onboarding.signIn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.ui.theme.Gray
import com.example.presentation.ui.theme.Main

@Composable
fun SignInScreen(
    goToSignUp: () -> Unit
) {
    val viewModel: SignInViewModel = hiltViewModel()
    val onClickBtnLogin = { email: String, password: String ->
        viewModel.login(email, password)
    }

    SignInContent(goToSignUp, onClickBtnLogin = onClickBtnLogin)
}

@Composable
fun SignInContent(
    goToSignUp: () -> Unit,
    onClickBtnLogin: (String, String) -> Unit
) {
    val email = remember{ mutableStateOf("") }
    val password = remember{ mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
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
                onClick = { onClickBtnLogin(email.value, password.value) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Main
                )
            ) {
                Text(
                    text = "로그인"
                )
            }
            Button(
                onClick = {
                    goToSignUp()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Text(
                    text = "회원가입",
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun LoginTextField(
    placeholder: String = "",
    input: MutableState<String>
) {
    OutlinedTextField(
        value = input.value,
        onValueChange = {
            input.value = it
        },
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.LightGray,
            focusedIndicatorColor = Color.LightGray
        ),
        placeholder = {
            Text(
                text = placeholder,
                color = Color.LightGray
            )
        },
        singleLine = true
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignInPreview() {
    SignInContent(
        {},
        {
            e: String, p: String -> Unit
        }
    )
}