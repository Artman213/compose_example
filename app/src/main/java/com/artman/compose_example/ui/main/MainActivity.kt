package com.artman.compose_example.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import com.artman.compose_example.ui.main.compose.ChatGPTView
import com.artman.compose_example.ui.theme.Compose_exampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    var word = ""
    var sentence = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        viewModel.error.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            Compose_exampleTheme {
                ChatGPTView(
                    title = word.ifEmpty { sentence },
                    chatGPTModels = viewModel.messageChat,
                    animateState = viewModel.animationState,
                    onEvent = viewModel::sendMessage
                )
            }
        }
    }
}

