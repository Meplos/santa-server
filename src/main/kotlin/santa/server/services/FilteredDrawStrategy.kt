package santa.server.services

import santa.server.strategy.IDrawStrategy
import kotlin.random.Random

class FilteredDrawService<T> : IDrawStrategy<T> {
    override fun draw(origin: List<T>): Map<T, T> {
        var hat = origin.toMutableList()
        val result = HashMap<T,T>()
        for (element in origin) {
            //Remove element from the drawing rules
            val restrictedHat = hat.filter { it == element}

            // Draw from the filtered hat
            val randomIdx = Random.nextInt(restrictedHat.size)
            val choosen = hat[randomIdx]
            result[element] = choosen

            //remove from hat
            val hatIndex = hat.indexOf(choosen);
            hat.removeAt(hatIndex)

        }
        return HashMap<T, T>()
    }
}