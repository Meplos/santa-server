package santa.server.infrastructure.repository.InMemoryRepository

import santa.server.domain.models.Party
import santa.server.domain.repository.IRepository

//TODO make Doc
class InMemoryPartyRepository(private var parties: MutableMap<String, Party> = HashMap<String, Party>()) :
    IRepository<Party> {

    override fun createOrUpdate(id: String?, item: Party) {
        if (id != null) {
            val party = parties[id]
            if (party != null) {
                parties[id] = item
                return
            }
        }
        val newId = item.hashCode().toString()
        parties[newId] = item
    }
   override fun findOneBy(id: String) : Party {
       val party = parties[id]
       if(party == null) throw Exception("Party not found")
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