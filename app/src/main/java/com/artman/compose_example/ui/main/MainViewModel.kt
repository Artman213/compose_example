package com.artman.compose_example.ui.main

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artman.compose_example.data.Repository
import com.artman.compose_example.module.ChatGPTModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var repository: Repository) : ViewModel() {

    var dialogId = 12
    var sha1 = ""

    var messageChat: MutableList<ChatGPTModel> by mutableStateOf(mutableListOf())
        private set

    var animationState by mutableStateOf(1f)
        private set

    private val _error = MutableLiveData<String>()
    val error = _error

    init {
        sendFirstMessage()
    }

    private fun sendFirstMessage() {
        val mutableList = mutableListOf<ChatGPTModel>()
        val chatGpt = ChatGPTModel(
            isAi = true,
            needShowAvatar = true,
            needShowProgress = false,
            "Can i help you"
        )
        mutableList.add(chatGpt)
        messageChat = mutableList
    }

    fun sendMessage(message: String) {
        animationState = 0.4f
        val chatGPTModel1 = ChatGPTModel(
            isAi = false,
            msg = message, needShowProgress = false,
            needShowAvatar = false
        )
        messageChat.add(chatGPTModel1)
        val chatGPTModel = ChatGPTModel(isAi = true, needShowAvatar = true, needShowProgress = true)
        messageChat.add(chatGPTModel)
        viewModelScope.launch {
            try {
                val response = repository.sendMessage(message, dialogId)
                response.let {
                    dialogId = it.dialogId
                    sha1 = UUID.randomUUID().toString()
                }
                messageChat.last().apply {
                    needShowProgress = false
                    msg = response.messge.content

                }
                messageChat.removeLast()
                messageChat.add(chatGPTModel1)
                animationState = 1f

            } catch (e: Exception) {
                animationState = 1f
                _error.postValue(e.localizedMessage)
            }

        }
    }

}