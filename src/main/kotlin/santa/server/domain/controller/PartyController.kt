package santa.server.domain.controller

import santa.server.domain.models.Party
import santa.server.domain.repository.IRepository

class PartyController(private val partyRepository: IRepository<Party>) {

    fun getById(id: String): Party {
        return this.partyRepository.findOneBy(id)
    }

    fun createOrUpdate(id: String, party: Party) {
        this.partyRepository.createOrUpdate(id, party)
    }
}