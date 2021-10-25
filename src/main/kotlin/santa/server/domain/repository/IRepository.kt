package santa.server.domain.repository


//TODO make Doc
interface IRepository<T> {
    fun createOrUpdate(id: String?, item: T)
    fun findOneBy(id: String): T


}