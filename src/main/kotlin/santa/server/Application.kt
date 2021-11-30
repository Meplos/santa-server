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
    install(CORS) {
        anyHost()
        method(HttpMethod.Options)
        method(HttpMethod.Get)
        method(HttpMethod.Post)
        header(HttpHeaders.ContentType)
        header(HttpHeaders.AccessControlAllowOrigin)
        allowCredentials = true
        allowNonSimpleContentTypes = true
    }
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
            log.info("[CREATE PARTY] Create  requested by - ${call.request.host() } - with ${call.request.userAgent()}" )

            val body = call.receive<List<String>>()
            val id = partyController.createOrUpdate(null, body)
            log.info("[CREATE PARTY] Create party $id" )

            call.respond(mapOf("id" to id))
        }
        get("/{id}") {
            val id = call.parameters["id"]
            log.info("[GETPARTICIPANT] party $id requested by - ${call.request.host() } - with ${call.request.userAgent()}" )

            if (!id.isNullOrEmpty()) {
                val participants = partyController.getById(id).getParticipants()
                call.respond(mapOf("participants" to participants))
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
        get("/{id}/participant/{name}") {
            val id = call.parameters["id"]
            log.info("[GETSANTA] party $id requested by - ${call.request.host() } - with ${call.request.userAgent()}" )
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