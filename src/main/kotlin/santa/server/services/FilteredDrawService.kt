package santa.server.services

import santa.server.strategy.AbstractDrawStrategy
import santa.server.strategy.IDrawStrategy
import kotlin.random.Random

class FilteredDrawService<T> : AbstractDrawStrategy<T>() {

    override fun draw(origin: List<T>): Map<T, T> {
        val hat = origin.toMutableList()
        val result = HashMap<T,T>()
        for ((index,element) in origin.withIndex()) {
            if(hat.size <= 2) break

            //Remove element from the drawing rules
            val restrictedHat = hat.filter { it != element}

            // Draw from the filtered hat
            val randomIdx = Random.nextInt(restrictedHat.size)
            val choosen = restrictedHat[randomIdx]
            result[element] = choosen

            //remove from hat
            val hatIndex = hat.indexOf(choosen);
            hat.removeAt(hatIndex)
        }
        result.putAll( this.twoElementDraft(hat, origin[origin.size-2], origin[origin.size-1]))
        return result
    }
}