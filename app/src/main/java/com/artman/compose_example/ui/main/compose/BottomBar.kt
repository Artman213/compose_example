package com.artman.compose_example.ui.main.compose

import android.app.Activity
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import com.artman.compose_example.R
import com.artman.compose_example.ui.theme.Compose_exampleTheme
import com.artman.compose_example.ui.theme.Purple200

@OptIn(ExperimentalComposeUiApi::class)

@Composable
fun BottomBar(animateState: Float, onEvent: (String) -> Unit) {
    val inputValue = remember { mutableStateOf(TextFieldValue()) }
    val activity = (LocalContext.current as? Activity)
    val animationDuration = 100
    val scaleDown = 0.9f
    val interactionSource = MutableInteractionSource()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()
    val scale = remember {
        Animatable(1f)
    }
    val opacity = remember {
        Animatable(1f)
    }
    LaunchedEffect(animateState) {
        animateState.let {
            opacity.animateTo(it)
        }
    }

    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .imePadding()
            .height(70.dp),
        backgroundColor = Color(0x00FFFFFF).compositeOver(Color(0x00FFFFFF)),
        elevation = 0.dp
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .alpha(opacity.value)
                .padding(10.dp, 3.dp, 10.dp, 3.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(value = inputValue.value, onValueChange = {
                    inputValue.value = it
                },

                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        disabledTextColor = Color.Transparent,
                        backgroundColor = Purple200,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    textStyle = LocalTextStyle.current.copy(fontSize = 13.sp),
                    modifier = Modifier
                        .height(50.dp)
                        .weight(1f)
                        .focusRequester(focusRequester),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = true,
                        keyboardType = KeyboardType.Text,
                    ),
                    placeholder = {
                        Text(
                            text = "Start typing here...",
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start,
                            color = Color.Black
                        )
                    }
                )
                Spacer(modifier = Modifier.width(10.dp))

                Image(painter = painterResource(id = R.drawable.button_chat),
                    contentDescription = "Send button",
                    modifier = Modifier
                        .size(40.dp)
                        .scale(scale = scale.value)
                        .clickable(interactionSource = interactionSource, indication = null) {
                            if (opacity.value != 0.4f) {
                                if (inputValue.value.text.isNotEmpty()) {
                                    coroutineScope.launch {
                                        scale.animateTo(
                                            scaleDown,
                                            animationSpec = tween(animationDuration),
                                        )
                                        scale.animateTo(
                                            1f,
                                            animationSpec = tween(animationDuration),
                                        )
                                        opacity.animateTo(0.4f)
                                    }
                                    onEvent(inputValue.value.text)
                                } else
                                    Toast
                                        .makeText(activity, "Type some message", Toast.LENGTH_SHORT)
                                        .show()


                            } else {
                                Toast
                                    .makeText(activity, "Wait for answer", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            focusManager.clearFocus()
                            inputValue.value = TextFieldValue()
                            keyboardController?.hide()

                        })
            }
        }


    }
}

@Preview
@Composable
fun BottomBarPreview() {
    Compose_exampleTheme {
        BottomBar(animateState = 1f, onEvent = {})
    }
}