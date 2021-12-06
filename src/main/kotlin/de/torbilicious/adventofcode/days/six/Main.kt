package de.torbilicious.adventofcode.days.six

fun main() {
//    var pool = exampleFishes.toMutableList()
    var pool = fishes.toMutableList()

    repeat(80) {
        pool = step(pool)
    }

    println("Amount of fishes: ${pool.size}")
}

fun step(pool: MutableList<Int>): MutableList<Int> {

    val toBeAdded = mutableListOf<Int>()

    val newPool = pool.map {
        when (it) {
            0 -> {
                toBeAdded.add(8)
                6
            }
            else -> {
                it - 1
            }
        }
    }.toMutableList()

    return (newPool + toBeAdded).toMutableList()
}
