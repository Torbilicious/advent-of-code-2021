package de.torbilicious.adventofcode.days.seven

import kotlin.math.absoluteValue

fun main() {
//    val datasetToUse = exampleCrabPositions
    val datasetToUse = crabPositions

    val idealAlignedPosition = datasetToUse.median()

    val fuelNeeded = datasetToUse.sumOf { (it - idealAlignedPosition).absoluteValue }

    println("Ideal position: $idealAlignedPosition")
    println("Fuel needed: $fuelNeeded")
}

fun Collection<Int>.median(): Int = this.sorted().let { (it[it.size / 2] + it[(it.size - 1) / 2]) / 2 }
