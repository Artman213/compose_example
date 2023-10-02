package com.artman.compose_example.module

data class ChatGPTModel(var isAi: Boolean = true,
                        var needShowAvatar: Boolean = false,
                        var needShowProgress: Boolean = false,
                        var msg: String = "")
