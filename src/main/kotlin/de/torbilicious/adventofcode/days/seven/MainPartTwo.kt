package de.torbilicious.adventofcode.days.seven

import kotlin.math.absoluteValue

fun main() {
//    val datasetToUse = exampleCrabPositions
    val datasetToUse = crabPositions

    val maxPosition = datasetToUse.maxOrNull()!!

    // <Position, FuelNeeded>
    val possibilities = mutableMapOf<Int, Int>()

    (0..maxPosition).forEach {
        val fuelNeeded = calcuLateFuelNeeded(datasetToUse, it)
        possibilities[it] = fuelNeeded
    }

    val idealAlignedPosition = possibilities.minByOrNull { it.value }!!

    println("Ideal position: ${idealAlignedPosition.key}")
    println("Fuel needed: ${idealAlignedPosition.value}")

}

fun calcuLateFuelNeeded(positions: List<Int>, targetPosition: Int): Int {
    // Part one fuel calctulation
//    return positions.sumOf { (it - targetPosition).absoluteValue }

    return positions.sumOf {
        val distance = (it - targetPosition).absoluteValue

        return@sumOf distance.sumUpTo()
    }

}

fun Int.sumUpTo(): Int {
    return (0..this).sumOf { it }
}
