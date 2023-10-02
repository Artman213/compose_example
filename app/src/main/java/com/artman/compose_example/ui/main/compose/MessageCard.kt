package com.artman.compose_example.ui.main.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artman.compose_example.module.ChatGPTModel
import com.artman.compose_example.R
import com.artman.compose_example.ui.theme.Compose_exampleTheme

@Composable
fun MessageCard(chatGPTModel: ChatGPTModel) {


    Box(
        modifier = Modifier
            .padding(all = 4.dp)
            .wrapContentWidth()
    ) {

        Row {

            if (chatGPTModel.isAi) {
                Row(modifier = Modifier.padding(0.dp, 0.dp, 20.dp, 0.dp)) {
                    if (chatGPTModel.needShowAvatar) {
                        Image(
                            painter = painterResource(R.drawable.avatar_ai),
                            contentDescription = "Contact profile picture",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                        )
                    } else {
                        Spacer(modifier = Modifier.width(40.dp))
                    }

                    Spacer(modifier = Modifier.width(8.dp))
                    val columnWidth = 19.dp
                    val columnHeight = 20.dp
                    Card(
                        elevation = 8.dp,
                        backgroundColor = Color.White
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(9.dp)
                                .widthIn(columnWidth)
                                .heightIn(columnHeight),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            if (chatGPTModel.needShowProgress) {
                                DotsPulsing()

                            } else
                                Text(text = chatGPTModel.msg)

                        }

                    }
                }
            } else {
                Box(modifier = Modifier.padding(20.dp, 8.dp, 8.dp, 8.dp)) {
                    Column(
                        Modifier
                            .fillMaxWidth(), horizontalAlignment = Alignment.End
                    ) {
                        Card(
                            elevation = 8.dp,
                            backgroundColor = colorResource(id = R.color.black),
                        ) {
                            Box(
                                Modifier
                                    .padding(9.dp)
                            ) {
                                Text(text = chatGPTModel.msg, color = Color.White)
                            }
                        }
                    }
                }

            }
        }

    }
}

@Preview
@Composable
fun MessageCardPreview() {
    Compose_exampleTheme() {
        MessageCard(
            chatGPTModel = ChatGPTModel(
                isAi = true,
                needShowAvatar = true,
                needShowProgress = false,
                msg = "Can i Help you"
            )
        )
    }

}