package com.artman.compose_example.ui.main.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.artman.compose_example.module.ChatGPTModel
import com.artman.compose_example.ui.theme.Compose_exampleTheme

@Composable
fun ChatGPTView(
    title: String, chatGPTModels: MutableList<ChatGPTModel>,
    animateState: Float, onEvent: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title)
        },
        bottomBar = {
            BottomBar(animateState, onEvent)
        }

    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            SetList(chatGPTModels)

        }
    }
}

@Preview
@Composable
fun PreviewChatGPTView() {
    Compose_exampleTheme() {
        ChatGPTView(
            title = "Title", chatGPTModels = mutableListOf(
                ChatGPTModel(
                    isAi = true,
                    needShowAvatar = true,
                    needShowProgress = false,
                    msg = "Hello"
                ),
                ChatGPTModel(
                    isAi = true,
                    needShowAvatar = false,
                    needShowProgress = true,
                    msg = ""
                ),
                ChatGPTModel(
                    isAi = false,
                    needShowAvatar = false,
                    needShowProgress = false,
                    msg = "Cool Thanks"
                )
            ), 1f
        ) {}
    }

}