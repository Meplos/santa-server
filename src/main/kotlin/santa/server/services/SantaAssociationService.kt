package santa.server.services

import kotlin.random.*

class SantaAssociationService {
    companion object {
        fun associate(santas: List<String>): Map<String, String> {
            val mutableSantas = santas.toMutableList()
            val children = santas.toMutableList()
            val association = HashMap<String, String>()

            while (mutableSantas.size > 2) {
                val santa = mutableSantas.removeFirst()
                var childIndex = Random.nextInt(children.size)
                while (santa === children[childIndex]) {
                    childIndex = Random.nextInt(children.size)
                }
                association[santa] = children.removeAt(childIndex)
            }
            val first = mutableSantas.removeFirst()
            val second = mutableSantas.removeFirst()

            if (children.contains(first)) {
                when (children.indexOf(first)) {
                    0 -> {
                        association[first] = children.removeAt(0)
                        association[second] = children.removeFirst()

                    }
                    1 -> {
                        association[first] = children.removeAt(1)
                        association[second] = children.removeFirst()
                    }
                }
            } else if (children.contains(second)) {
                when (children.indexOf(second)) {
                    0 -> {
                        association[second] = children.removeAt(0)
                        association[first] = children.removeFirst()

                    }
                    1 -> {
                        association[second] = children.removeAt(1)
                        association[first] = children.removeFirst()
                    }
                }
            } else {
                association[first] = children.removeFirst()
                association[second] = children.removeFirst()
            }

            return HashMap<String, String>()
        }
    }
}