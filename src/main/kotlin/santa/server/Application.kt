package santa.server

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import santa.server.domain.controller.PartyController
import santa.server.infrastructure.repository.InMemoryRepository.InMemoryPartyRepository
import santa.server.services.FilteredDrawService

fun main(argv: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(argv)


fun Application.module(testing: Boolean = false) {
    val partyController = PartyController(InMemoryPartyRepository(),FilteredDrawService() )
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