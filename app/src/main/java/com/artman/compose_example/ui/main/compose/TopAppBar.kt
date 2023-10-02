package com.artman.compose_example.ui.main.compose

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artman.compose_example.R
import com.artman.compose_example.ui.theme.Compose_exampleTheme

@Composable
fun TopAppBar(title: String) {
    val activity = (LocalContext.current as? Activity)


    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.header_news),
            contentDescription = "header",
            modifier = Modifier
                .matchParentSize(),
            contentScale = ContentScale.Crop
        )
        androidx.compose.material.TopAppBar(modifier = Modifier
            .statusBarsPadding(),
            backgroundColor = Color(0x00FFFFFF).compositeOver(Color(0x00FFFFFF)),
            elevation = 0.dp,
            title = {
                Text(
                    title, modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center, color = Color.White
                )
            },
            navigationIcon = {
                IconButton(onClick = {

                }) {
                    Icon(
                        Icons.Filled.Close, "Back",
                        modifier = Modifier.width(20.dp),
                        tint = Color.White
                    )
                }
            }, actions = {
                Spacer(modifier = Modifier.width(70.dp))

            })


    }
}

@Preview
@Composable
fun TopBarPreview() {
    Compose_exampleTheme() {
        TopAppBar(title = "Title")

    }
}