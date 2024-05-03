package com.example.routes


import com.example.models.Message
import com.example.models.messageStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.messageRouting() {
    route("/message") {
        get {
            if (messageStorage.isNotEmpty()) call.respond(messageStorage)
            else call.respondText("No messages found.", status = HttpStatusCode.OK)
        }
        get("/latest") {
            val latestMessage = messageStorage.lastOrNull()
            if (latestMessage != null) {
                call.respond(latestMessage)
            } else {
                call.respondText("No messages found.", status = HttpStatusCode.NotFound)
            }
        }
        post {
            val message = call.receive<Message>()
            messageStorage.add(message)
            call.respondText("Message stored correctly", status = HttpStatusCode.Created)
        }

    }
}

