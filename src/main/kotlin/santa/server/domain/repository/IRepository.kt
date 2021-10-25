package santa.server.domain.repository

interface IRepository<T> {
    fun createOrUpdate(id: String, item: T)
    fun findOneBy(id: String) : T

}