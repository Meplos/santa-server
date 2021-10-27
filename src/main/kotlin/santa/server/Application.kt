package santa.server

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import santa.server.domain.controller.PartyController
import santa.server.infrastructure.repository.InMemoryRepository.InMemoryPartyRepository
import santa.server.services.FilteredDrawService

fun main(argv: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(argv)


fun Application.module(testing: Boolean = false) {
    val partyController = PartyController(InMemoryPartyRepository(), FilteredDrawService())
    val formatter = Json { prettyPrint = true }
    install(CORS)
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = testing
        })
    }
    routing {
        get("/test") {
            call.respondText("Hello, world!")

        }
        post("/") {
            val body = call.receive<List<String>>()
            val id = partyController.createOrUpdate(null, body)
            call.respond(mapOf("id" to id))
        }
        get("/{id}") {
            //TODO: Get participants name
            var responseCode = HttpStatusCode.BadRequest
            var message = ""
            val id = call.parameters["id"]
            if (!id.isNullOrEmpty()) {
                val participants = partyController.getById(id)
                call.respond(participants)
            }
            call.respond(responseCode, message)
        }
        get("/{id}/participant/{name}") {
            //TODO: Return persons associate to name
        }
    }
}