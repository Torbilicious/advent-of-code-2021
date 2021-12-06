package de.torbilicious.adventofcode.days.six

import java.math.BigInteger

//typealias NumberType = Int
typealias NumberType = BigInteger

fun main() {
//    val datasetToUse = exampleFishes
    val datasetToUse = fishes

    // <DaysUntilNewFish, AmountOfFishesInThisDay>
    var pool = mutableMapOf(
        0 to datasetToUse.count { it == 0 }.toBigInteger(),
        1 to datasetToUse.count { it == 1 }.toBigInteger(),
        2 to datasetToUse.count { it == 2 }.toBigInteger(),
        3 to datasetToUse.count { it == 3 }.toBigInteger(),
        4 to datasetToUse.count { it == 4 }.toBigInteger(),
        5 to datasetToUse.count { it == 5 }.toBigInteger(),
        6 to datasetToUse.count { it == 6 }.toBigInteger(),
        7 to datasetToUse.count { it == 7 }.toBigInteger(),
        8 to datasetToUse.count { it == 8 }.toBigInteger(),
    )


    //part one
//    val daysToSimulate = 80

    //part two
    val daysToSimulate = 256

    repeat(daysToSimulate) {
        pool = step(pool)

        println("Day ${it + 1}")
        println("Fish Population: ${pool.values.sumOf { it }}")
        println()
    }

    println("Amount of fishes: ${pool.values.sumOf { it }}")
}

fun step(pool: MutableMap<Int, NumberType>): MutableMap<Int, NumberType> {
    val nextPool = mutableMapOf<Int, NumberType>()

    pool.forEach {
        when (it.key) {
            0 -> {
                nextPool.addTo(6, it.value)
                nextPool.addTo(8, it.value)
            }
            else -> {
                nextPool.addTo(it.key - 1, it.value)
            }
        }
    }

    return nextPool
}

fun MutableMap<Int, NumberType>.addTo(key: Int, value: NumberType) {
    val existingValue = if (this.containsKey(key)) {
        this[key]
    } else {
        0.toBigInteger()
    }!!

    this[key] = existingValue + value
}
