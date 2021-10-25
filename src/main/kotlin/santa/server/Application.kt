package santa.server

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*

fun main(argv: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(argv)


fun Application.module(testing: Boolean = false) {
    install(CORS)
    install(ContentNegotiation) {
        json()
    }
    routing {
        get("/test") {
            call.respondText("Hello, world!")
        }
        post("/") {
            //TODO: Create new party
        }
        get("/{id}") {
            //TODO: Get participants name
        }
        get("/{id}/participant/{name}"){
            //TODO: Return persons associate to name
        }
    }
}