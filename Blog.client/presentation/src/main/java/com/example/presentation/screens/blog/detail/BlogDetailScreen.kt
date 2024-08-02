package com.example.presentation.screens.blog.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BlogDetailScreen(
    id: Long,
    popScreen: () -> Unit
) {
    val viewModel: BlogDetailViewModel = hiltViewModel()
    val article = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getArticle(id)
    }

    BlogDetailContent(
        title = article.value.title,
        content = article.value.content,
        popScreen = popScreen
    )
}

@Composable
fun BlogDetailContent(
    title: String,
    content: String,
    popScreen: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            BlogDetailTopBar(
                popScreen = popScreen
            )

            HorizontalDivider()

            Box(
                modifier = Modifier
                    .height(60.dp)
                    .padding(10.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = title,
                    color = Color.Black
                )
            }

            HorizontalDivider()

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Text(
                    text = content
                )
            }
        }
    }
}

@Composable
fun BlogDetailTopBar(
    popScreen: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { popScreen() }
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "btnBack")
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {  },
            modifier = Modifier
                .height(35.dp)
        ) {
            Text(
                text = "삭제",
                textAlign = TextAlign.Center,
                fontSize = 13.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BlogDetailContentPreview() {
    BlogDetailContent(
        title = "제목",
        content = "내용"
    )
}