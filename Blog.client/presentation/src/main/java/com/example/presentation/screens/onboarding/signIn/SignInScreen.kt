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
fun SignInScreen() {
    val viewModel: SignInViewModel = hiltViewModel()

    SignInContent()
}

@Composable
fun SignInContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            LoginTextField("아이디")
            Spacer(modifier = Modifier.height(5.dp))
            LoginTextField("비밀번호")
            Spacer(modifier = Modifier.height(5.dp))
            Button(
                onClick = { /*TODO*/ },
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
                onClick = { /*TODO*/ },
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
    placeholder: String = ""
) {
    val input = remember{ mutableStateOf("") }

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
    SignInContent()
}