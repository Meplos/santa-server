package santa.server.strategy

abstract class AbstractDrawStrategy<T> : IDrawStrategy<T> {
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