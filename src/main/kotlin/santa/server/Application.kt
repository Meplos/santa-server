package santa.server

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.gson.*
fun main(argv: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(argv)


fun Application.module(testing: Boolean = false) {
    install(CORS)
    install(ContentNegotiation) {
        gson()
    }
    routing {
        get("/test") {
            call.respondText("Hello, world!")
        }
    }
}