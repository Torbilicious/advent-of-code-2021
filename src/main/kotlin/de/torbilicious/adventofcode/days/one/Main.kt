package de.torbilicious.adventofcode.days.one

fun main() {
    var amountOfIncreases = 0

    var lastSum = 0

    depthMeasurements.forEachIndexed { index, _ ->
        //skip first two entires
        if (index in 0..2) return@forEachIndexed

        val sumOfLastThreeEntries = depthMeasurements[index-2] + depthMeasurements[index-1] + depthMeasurements[index]

        if (sumOfLastThreeEntries > lastSum) amountOfIncreases++

        lastSum = sumOfLastThreeEntries

    }

    println(amountOfIncreases)
}
