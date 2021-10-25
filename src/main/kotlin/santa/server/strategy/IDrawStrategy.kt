package santa.server.strategy

/**
 * IDrawStrategy
 * @author Alexandre Erard <alexandre.erard@protonmail.ch>
 * */

/**
 * Random draw strategy
 * Declare contract about drawing services strategy
 *
 *
 * @param T Type of object to draw
 * */
interface IDrawStrategy<T> {

    /**
     * Return association of element in origin list
     * @param origin, list of element which must be associate and draw.
     * @return Key -> value association. All element are contains in origin. Key must be different of value
     * */
    abstract fun draw(origin: List<T>): Map<T, T>

}