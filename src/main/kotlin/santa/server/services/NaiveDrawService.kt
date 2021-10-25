package santa.server.services

import santa.server.strategy.AbstractDrawStrategy
import santa.server.strategy.IDrawStrategy
import kotlin.random.*

/**
 * NaiveDrawService
 * @author Alexandre Erard <alexandre.erard@protonmail.ch>
 * */

/**
 * NaiveDrawService
 * inherits AbstractDrawStrategy and add two element draft logic
 * implement Naive version of draw algorithm
 * @param T Type of object to draw
 * */
class NaiveDrawService<T> : AbstractDrawStrategy<T>() {

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

        result.putAll(this.twoElementDraft(hat, origin[origin.size - 2], origin[origin.size - 1]))

        return result
    }
}