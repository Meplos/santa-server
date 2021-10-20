package santa.server

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun main(argv: Array<String>) : Unit = io.ktor.server.netty.EngineMain.main(argv)


fun Application.module(testing: Boolean = false) {
    routing {
        get("/test") {
            call.respondText("Hello, world!")
        }
    }
}