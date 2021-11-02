package santa.server.domain.repository

import santa.server.domain.models.Party

/**
 * IPartyRepository
 * implementsIRepository
 * Extend the base repository interface for PartyEntity
 * */
interface IPartyRepository : IRepository<Party> {

    /**
     * getSantaFor
     * @param id of the requested resource
     * @param name name of the people who offer a gift
     * @return the name of the persons to give a gift
     * */
    fun getSantaFor(id: String, name: String): String

    /**
     * getParticipantOf
     * @param id of the requested resource
     * @return list of all participant of the party
     * */
    fun getParticipantOf(id: String): Set<String>
}