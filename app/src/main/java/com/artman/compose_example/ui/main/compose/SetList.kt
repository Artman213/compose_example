package com.artman.compose_example.ui.main.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artman.compose_example.module.ChatGPTModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.artman.compose_example.R
import com.artman.compose_example.ui.theme.Compose_exampleTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SetList(chatGPTModels: MutableList<ChatGPTModel>) {

    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    LaunchedEffect(chatGPTModels.size) {
        listState.animateScrollToItem(chatGPTModels.size)
    }
    val isOpen by rememberKeyboard()
    LaunchedEffect(key1 = isOpen) {
        scope.launch {
            delay(200)
            if (isOpen) {
                listState.animateScrollToItem(chatGPTModels.size + 1)
            }
        }
    }
    LazyColumn(modifier = Modifier
        .background(colorResource(id = R.color.white))
        .fillMaxSize()
        .imeNestedScroll(),
        content = {
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            items(chatGPTModels) {
                MessageCard(chatGPTModel = it)
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }, state = listState
    )

}

@Composable
fun rememberKeyboard(): State<Boolean> {
    val isImeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
    return rememberUpdatedState(isImeVisible)
}


@Preview
@Composable
fun PreviewScreen() {
    val chatGPTModels: MutableList<ChatGPTModel> = mutableListOf(
        ChatGPTModel(isAi = true, needShowAvatar = true, needShowProgress = false, msg = "Hello"),
        ChatGPTModel(isAi = true, needShowAvatar = false, needShowProgress = true, msg = ""),
        ChatGPTModel(
            isAi = false,
            needShowAvatar = false,
            needShowProgress = false,
            msg = "Cool Thanks"
        )
    )
    Compose_exampleTheme {
        SetList(chatGPTModels = chatGPTModels)
    }

}