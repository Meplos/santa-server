package santa.server.infrastructure.repository.InMemoryRepository

import santa.server.domain.models.Party
import santa.server.domain.repository.IPartyRepository
import santa.server.domain.repository.IRepository

/**
 * InMemoryPartyRepository
 * implement IPartyRepository
 * In Memory party repository implementation.
 * ! No ata persistance
 * @constructor create empty party map
 * */
class InMemoryPartyRepository(private var parties: MutableMap<String, Party> = HashMap<String, Party>()) :
    IPartyRepository {

    override fun createOrUpdate(id: String?, item: Party) : String  {
        if (id != null) {
            val party = parties[id]
            if (party != null) {
                parties[id] = item
                return id;
            }
        }
        val newId = item.hashCode().toString()
        parties[newId] = item
        return newId;
    }

    override fun findOneBy(id: String): Party {
        val party = parties[id] ?: throw Exception("Party not found")
        return party
    }

    override fun getParticipantOf(id: String): Set<String> {
        val party = findOneBy(id)
        return party.getParticipants()
    }

    override fun getSantaFor(id: String, name: String): String {
        val party = findOneBy(id)
        return party.getSantaFor(name);
    }

}