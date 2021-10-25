package santa.server.strategy


/**
 * AbstractDrawStrategy
 * @author Alexandre Erard <alexandre.erard@protonmail.ch>
 * */

/**
 * AbstractDrawStrategy
 * implements IDrawStrategy and add two element draft logic
 *
 * @param T Type of object to draw
 * */
abstract class AbstractDrawStrategy<T> : IDrawStrategy<T> {

    /**
    * Exchange last two element and follow the constraint rules. Key != value
     * @param hat list of element to draw. must have 2 element
     * @param first element which have to draw
     * @param second element which have to draw
     * @return Key -> value association. All element are contains in origin. Key must be different of value
     *  */
    fun twoElementDraft(hat: MutableList<T>, first: T, second: T): HashMap<T, T> {
        val result = HashMap<T, T>()
        if (hat.contains(first)) {
            when (hat.indexOf(first)) {
                0 -> {
                    result[first] = hat.removeAt(1)
                    result[second] = hat.removeFirst()

                }
                1 -> {
                    result[first] = hat.removeAt(0)
                    result[second] = hat.removeFirst()
                }
            }
        } else if (hat.contains(second)) {
            when (hat.indexOf(second)) {
                0 -> {
                    result[second] = hat.removeAt(1)
                    result[first] = hat.removeFirst()

                }
                1 -> {
                    result[second] = hat.removeAt(0)
                    result[first] = hat.removeFirst()
                }
            }
        } else {
            result[first] = hat.removeFirst()
            result[second] = hat.removeFirst()
        }

        return result;

    }

}