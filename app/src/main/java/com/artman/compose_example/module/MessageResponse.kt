package com.artman.compose_example.module

data class MessageResponse(var dialogId: Int,
                           var messge: Message){

    data class Message(var role: String, var content: String, var sha1: String)
}