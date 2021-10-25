package santa.server.domain.repository

import santa.server.domain.models.Party

interface IPartyRepository : IRepository<Party> {
    fun getSantaFor(id: String, name: String): String
    fun getParticipantOf(id: String): Set<String>
}