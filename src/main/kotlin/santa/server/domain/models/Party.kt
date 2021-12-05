package santa.server.domain.models

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * Party
 * Describe the association of giftor, gifted person
 * */
@Serializable
data class Party(val map: Map<String, String>) {
    fun getSantaFor(name: String): String {
        val giftFor = this.map[name]
        if (giftFor.isNullOrEmpty()) throw Exception("Santa not found")
        return giftFor
    }

    fun getParticipants() : Set<String> {
        return map.keys
    }
}