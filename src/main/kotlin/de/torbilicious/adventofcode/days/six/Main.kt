package de.torbilicious.adventofcode.days.six

fun main() {
//    var pool = exampleFishes.toMutableList()
    var pool = fishes

    repeat(80) {
        pool = step(pool)
    }

    println("Amount of fishes: ${pool.size}")
}

fun step(pool: List<Int>): List<Int> {

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
    }

    return newPool + toBeAdded
}
