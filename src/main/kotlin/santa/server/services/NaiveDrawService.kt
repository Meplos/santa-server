package santa.server.services

import santa.server.strategy.IDrawStrategy
import kotlin.random.*

class NaiveDrawService<T> : IDrawStrategy<T> {
    override fun draw(origin: List<T>): Map<T, T> {
        val mutableOrigin = origin.toMutableList()
        val hat = origin.toMutableList()
        val result = HashMap<T, T>()

        while (mutableOrigin.size > 2) {
            val santa = mutableOrigin.removeFirst()
            var childIndex = Random.nextInt(hat.size)
            while (santa === hat[childIndex]) {
                childIndex = Random.nextInt(hat.size)
            }
            result[santa] = hat.removeAt(childIndex)
        }
        val first = mutableOrigin.removeFirst()
        val second = mutableOrigin.removeFirst()

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

        return result
    }
}