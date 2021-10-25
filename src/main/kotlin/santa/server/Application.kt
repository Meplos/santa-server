package santa.server

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import santa.server.routes.ParticipantRoute
import santa.server.routes.PartyRoute

fun main(argv: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(argv)


fun Application.module(testing: Boolean = false) {
    install(CORS)
    routing {
        PartyRoute()
        ParticipantRoute()
    }
}