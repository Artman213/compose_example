package com.artman.compose_example.data

import com.artman.compose_example.module.MessageRequest
import com.artman.compose_example.network.ChatGptApi
import retrofit2.Retrofit
import javax.inject.Inject

class RemoteDataSource @Inject constructor(retrofit: Retrofit) {

    private val  chatGptApi: ChatGptApi = retrofit.create(ChatGptApi::class.java)

    suspend fun getMessage(messageRequest: MessageRequest) =
        chatGptApi.sendMessage(messageRequest)
}