package com.example.presentation.ui.screens.blogmain

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.ui.theme.BlogclientTheme
import com.example.presentation.ui.theme.Gray
import com.example.presentation.ui.theme.Purple80

@Composable
fun BlogMainScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .background(color = Gray)
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = "My Blog",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                ),
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "블로그에 오신 것을 환영합니다."
            )
        }

        LazyColumn(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
        ) {
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBlogMain() {
    BlogclientTheme {
        BlogMainScreen()
    }
}