package com.example.presentation.screens.blog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
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
import com.example.presentation.ui.theme.BlogclientTheme
import com.example.presentation.ui.theme.Gray
import com.example.presentation.util.StringFormatter

@Composable
fun BlogMainScreen(
    goToWritePage: () -> Unit,
    onClickArticleItem: (Long) -> Unit
) {
    val viewModel: BlogMainViewModel = hiltViewModel()
    val allArticles = viewModel.allArticleList.collectAsState()
    val onClickBtnWrite = {
        goToWritePage()
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllArticles()
    }

    BlogContent(
        articles = allArticles.value,
        onClickBtnWrite,
        onClickArticleItem = onClickArticleItem
    )
}

@Composable
fun BlogContent(
    articles: List<ArticleResponseVo>,
    onClickBtnWrite: () -> Unit,
    onClickArticleItem: (Long) -> Unit = {}
) {
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

                Spacer(modifier = Modifier.height(5.dp))

                Button(
                    onClick = { onClickBtnWrite() }
                ) {
                    Text(
                        text = "글쓰기"
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxSize()
            ) {
                items(articles) { item ->
                    ArticleItem(
                        item = item,
                        onClickArticleItem = onClickArticleItem
                    )
                }
            }
        }
    }
}

@Composable
fun ArticleItem(
    item: ArticleResponseVo,
    onClickArticleItem: (Long) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
            .clickable {
                onClickArticleItem(item.id)
            }
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = item.title,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            maxLines = 1
        )
        Text(
            text = item.content,
            style = TextStyle(
                fontSize = 18.sp
            ),
            maxLines = 2
        )
        Text(
            text = StringFormatter.formatToMonthDay(item.createdAt)
        )
        Spacer(modifier = Modifier.height(5.dp))
        HorizontalDivider()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBlogMain() {
    BlogclientTheme {
        BlogContent(
            articles = listOf(
                ArticleResponseVo(
                    id = 1,
                    title = "title",
                    content = "content",
                    createdAt = "2024-07-24T22:43:48",
                    updatedAt = "2024-07-24"
                ),
                ArticleResponseVo(
                    id = 1,
                    title = "title",
                    content = "contentasngnrpgouwepofinas;lkdfnasjlkdgnpouqwbrgopiuwqaskdjvnaourwbgpwrbgaidjsvbpoiwauebviwubaisjdvbipauwrbev",
                    createdAt = "2024-07-24",
                    updatedAt = "2024-07-24"
                )
            ),
            {}
        )
    }
}