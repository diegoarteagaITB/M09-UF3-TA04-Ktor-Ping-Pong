package com.example.models


import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val id: String,
    var content: String,
    )


val messageStorage = mutableListOf<Message>()
