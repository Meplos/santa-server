package santa.server.domain.repository


/**
 * IRepository
 * Represent interface that define default function of a repository
 * @param T type of models use by the repo
 * */
interface IRepository<T> {
    /**
     * createOrUpdate
     * create new Resource in the repo if id is specified
     * modify instead
     * @param id of the requested resource. Possibly null
     * @param item new value of the resource
     * @throws Exception if id not null and not exist in the repo
     * */
    fun createOrUpdate(id: String?, item: T)

    /**
     * findOneBy
     * find an item with the id, id
     * @param id of the requested resource
     * @return object stored corresponding with the id
     * */
    fun findOneBy(id: String): T


}