package santa.server.domain.controller

import santa.server.domain.models.Party
import santa.server.domain.repository.IRepository
import santa.server.strategy.IDrawStrategy


/**
 * PartyController
 * Controller witch manage Party entity operation
 * */
class PartyController(
    private val partyRepository: IRepository<Party>,
    private val drawStrategy: IDrawStrategy<String>
) {

    /**
     * getById
     * @param id of the requested ressource
     * @return Party with thee id => id
     * @throws Exception (EntityNotFound)
     * */
    fun getById(id: String): Party {
        return this.partyRepository.findOneBy(id)
    }

    /**
     * createOrUpdate
     * create or update the party
     * @param id of the requested ressource
     * @param participants list of the party
     * @throws Exception (EntityNotFound)
     * */
    fun createOrUpdate(id: String?, participants: List<String>) {
        val association = drawStrategy.draw(participants)
        this.partyRepository.createOrUpdate(id, Party(association))
    }
    
    /**
     * getParticipantOf
     * @param id of the requested resource
     * @return list of all participant of the party
     * */
    fun getSanta(id: String, name: String): String {
        val party = this.getById(id)
        return party.getSantaFor(name)
    }
}