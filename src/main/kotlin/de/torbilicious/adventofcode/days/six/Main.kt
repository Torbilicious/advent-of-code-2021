package de.torbilicious.adventofcode.days.six

import java.math.BigInteger

//typealias NumberType = Int
typealias NumberType = BigInteger

fun main() {
//    val datasetToUse = exampleFishes
    val datasetToUse = fishes

    // <DaysUntilNewFish, AmountOfFishesInThisDay>
    var pool = (0..8)
        .associateWith { days -> datasetToUse.count { it == days }.toBigInteger() }
        .toMutableMap()


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
