package santa.server

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.netty.handler.codec.http.HttpResponse
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import santa.server.domain.controller.PartyController
import santa.server.infrastructure.repository.InMemoryRepository.InMemoryPartyRepository
import santa.server.services.FilteredDrawService

fun main(argv: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(argv)


fun Application.module(testing: Boolean = false) {
    val partyController = PartyController(InMemoryPartyRepository(), FilteredDrawService())
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
            val id = call.parameters["id"]
            if (!id.isNullOrEmpty()) {
                val participants = partyController.getById(id).getParticipants()
                call.respond(mapOf("participants" to participants))
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
        get("/{id}/participant/{name}") {
            val id = call.parameters["id"]
            val name = call.parameters["name"]
            if (id.isNullOrEmpty() || name.isNullOrEmpty()) {
                call.respond(HttpStatusCode.BadRequest)
            } else {
                val res = partyController.getSanta(id, name)
                call.respond(mapOf("name" to res))
            }
        }
    }
}