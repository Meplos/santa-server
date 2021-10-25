package santa.server.domain.controller

import santa.server.domain.models.Party
import santa.server.domain.repository.IRepository
import santa.server.strategy.IDrawStrategy

class PartyController(
    private val partyRepository: IRepository<Party>,
    private val drawStrategy: IDrawStrategy<String>
) {

    fun getById(id: String): Party {
        return this.partyRepository.findOneBy(id)
    }

    fun createOrUpdate(id: String, participants: List<String>) {
        val association = drawStrategy.draw(participants)
        this.partyRepository.createOrUpdate(id, Party(association))
    }

    fun getSanta(id: String, name: String): String {
        val party = this.getById(id)
        return party.getSantaFor(name)
    }
}