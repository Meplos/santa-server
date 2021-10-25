package santa.server.domain.repository


//TODO make Doc
interface IRepository<T> {
    fun createOrUpdate(id: String?, item: T)
    fun findOneBy(id: String): T

    // TODO Move in orther interface IPartyRepository
    fun getSantaFor(id: String, name: String): String
    fun getParticipantOf(id: String): Set<String>

}