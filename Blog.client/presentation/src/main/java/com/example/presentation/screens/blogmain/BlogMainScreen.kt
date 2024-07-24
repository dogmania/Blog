package com.example.presentation.screens.blogmain

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.response.ArticleResponseVo
import com.example.presentation.ui.screens.blogmain.BlogMainViewModel
import com.example.presentation.ui.theme.BlogclientTheme
import com.example.presentation.ui.theme.Gray

@Composable
fun BlogMainScreen(
    viewModel: BlogMainViewModel = hiltViewModel()
) {
    val allArticles = viewModel.allArticleList.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllArticles()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
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
                items(allArticles.value) { item ->
                    ArticleItem(item = item)
                }
            }
        }
    }
}

@Composable
fun ArticleItem(item: ArticleResponseVo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = item.title
        )
        Text(
            text = item.content
        )
        Text(
            text = item.createdAt
        )
        Text(
            text = item.updatedAt
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBlogMain() {
    BlogclientTheme {
        BlogMainScreen()
    }
}