package santa.server.strategy

interface IDrawStrategy<T> {
    abstract fun draw(origin: List<T>): Map<T, T>

}