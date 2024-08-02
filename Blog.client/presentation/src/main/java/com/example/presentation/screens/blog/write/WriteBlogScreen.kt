package com.example.presentation.screens.blog.write

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.ui.theme.BlogclientTheme

@Composable
fun WriteBlogScreen(
    popScreen: () -> Unit
) {
    val viewModel: WriteBlogViewModel = hiltViewModel()
    val completeWriteArticle by viewModel.completeWriteArticle.collectAsState()
    val title = remember{ mutableStateOf("") }
    val content = remember { mutableStateOf("") }
    val context = LocalContext.current

    if (completeWriteArticle) popScreen()

    WriteBlogContent(
        onClickBtnClose = { popScreen() },
        saveTitle = { newTitle: String -> title.value = newTitle},
        saveContent = { newContent: String -> content.value = newContent },
        onClickBtnWrite = {
            if (title.value.isEmpty() || content.value.isEmpty()) {
                Toast.makeText(context, "제목과 내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.createArticle(title.value, content.value)
            }
        }
    )
}

@Composable
fun WriteBlogContent(
    onClickBtnClose: () -> Unit,
    saveTitle: (String) -> Unit,
    saveContent: (String) -> Unit,
    onClickBtnWrite: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            WriteBlogTopBar( onClickBtnClose, onClickBtnWrite )
            TitleInputField( saveTitle )
            ContentInputField( saveContent )
        }
    }
}

@Composable
fun WriteBlogTopBar(
    onClickBtnClose: () -> Unit,
    onClickBtnWrite: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { onClickBtnClose() }
        ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "btnClose")
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(text = "글쓰기")

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onClickBtnWrite() },
            modifier = Modifier
                .height(35.dp)
        ) {
            Text(
                text = "작성",
                textAlign = TextAlign.Center,
                fontSize = 13.sp
            )
        }
    }
}

@Composable
fun TitleInputField(
    saveTitle: (String) -> Unit
) {
    val title = remember { mutableStateOf("") }

    TextField(
        value = title.value,
        onValueChange = {
            title.value = it
            saveTitle(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.LightGray,
            focusedIndicatorColor = Color.LightGray
        ),
        placeholder = { Text(text = "제목") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {

            }
        )
    )
}

@Composable
fun ContentInputField(
    saveContent: (String) -> Unit
) {
    val content = remember{ mutableStateOf("") }

    TextField(
        value = content.value,
        onValueChange = {
            content.value = it
            saveContent(it)
        },
        modifier = Modifier
            .fillMaxSize(),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = "내용을 입력하세요.",
                modifier = Modifier
                    .fillMaxSize()
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {

            }
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WriteBlogPreview() {
    BlogclientTheme {
        WriteBlogContent(
            {},
            {},
            {},
            {}
        )
    }
}