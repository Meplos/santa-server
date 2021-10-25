package santa.server.domain.models
import kotlinx.serialization.*

@Serializable
data class Party (val id: String, val map : Map<String, String>)