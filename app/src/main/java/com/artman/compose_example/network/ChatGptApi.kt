package com.artman.compose_example.network

import com.artman.compose_example.module.MessageRequest
import com.artman.compose_example.module.MessageResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatGptApi {

    @POST("/question")
    suspend fun sendMessage (@Body messageRequest: MessageRequest) : MessageResponse
}