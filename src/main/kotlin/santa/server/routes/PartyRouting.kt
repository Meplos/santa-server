package santa.server.routes

import io.ktor.routing.*

fun Route.PartyRoute() {

    get("/{cid}") {

    }
    post("/") {

    }

}

fun Route.ParticipantRoute() {
    get("/{cid}/{name}") {

    }
}