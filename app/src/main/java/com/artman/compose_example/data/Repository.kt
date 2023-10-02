package com.artman.compose_example.data

import com.artman.compose_example.module.MessageRequest
import com.artman.compose_example.module.MessageResponse
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource) {

    suspend fun sendMessage(content: String, dialogId: Int): MessageResponse {
        val messageRequest = MessageRequest(content, dialogId)
        return remoteDataSource.getMessage(messageRequest)
    }
}