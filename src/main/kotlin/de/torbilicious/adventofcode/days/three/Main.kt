package de.torbilicious.adventofcode.days.three

data class PositionInformation(
    val position: Int,
    var amountOfOnes: Int,
    var amountOfZeros: Int,
)

fun main() {
//    val datasetToUse = exampleDiagnostics
    val datasetToUse = diagnosticts

    val counters = calculatePositionInformation(datasetToUse.lines())

//    println(counters)

    calculateGammaAndEpsilonRate(counters)

    calculateLifeSupportRating(datasetToUse)
}

fun calculateLifeSupportRating(text: String) {
    println(text)

    val amountOfPositions = text.lines().first().length
    var currentLines = text.lines()
    var position = 0
    var amountOfLinesRemaining = currentLines.size
    while (amountOfLinesRemaining != 1) {
        val infos = calculatePositionInformation(currentLines)
        val info = infos[amountOfPositions - position - 1]
        val digitToKeep = if (info.amountOfOnes >= info.amountOfZeros) {
            '1'
        } else {
            '0'
        }

        println(info)
        println("For $position keep $digitToKeep")

        currentLines = currentLines.filter { it[position] == digitToKeep }


        println(currentLines)

        amountOfLinesRemaining = currentLines.size
        position++
    }

    val oxygenGeneratorRating = currentLines.first().binaryToInt()


    currentLines = text.lines()
    position = 0
    amountOfLinesRemaining = currentLines.size
    while (amountOfLinesRemaining != 1) {
        val infos = calculatePositionInformation(currentLines)
        val info = infos[amountOfPositions - position - 1]
        val digitToKeep = if (info.amountOfOnes >= info.amountOfZeros) {
            '0'
        } else {
            '1'
        }

        println(info)
        println("For $position keep $digitToKeep")

        currentLines = currentLines.filter { it[position] == digitToKeep }


        println(currentLines)

        amountOfLinesRemaining = currentLines.size
        position++
    }


    val cO2ScrubberRating = currentLines.first().binaryToInt()


    println("oxygenGeneratorRating: $oxygenGeneratorRating")
    println("cO2ScrubberRating: $cO2ScrubberRating")
    println("result: ${oxygenGeneratorRating * cO2ScrubberRating}")
}

private fun String.binaryToInt(): Int {
    var int = 0
    this.reversed().forEachIndexed { index, c ->
        val numberToShift = c.digitToInt()

        int = int or (numberToShift shl index)

    }

    return int
}

fun calculatePositionInformation(lines: List<String>): List<PositionInformation> {
    val counters = mutableListOf<PositionInformation>()

    (0 until lines.first().length).forEach {
        counters.add(PositionInformation(it, 0, 0))
    }

    lines
        .forEach { line ->
//            println(line)

            line.reversed().forEachIndexed { index, char ->
                val positionInformation = counters.find { it.position == index }!!
                when (char) {
                    '1' -> positionInformation.amountOfOnes++
                    '0' -> positionInformation.amountOfZeros++
                }
            }
        }

    return counters
}

fun calculateGammaAndEpsilonRate(counters: List<PositionInformation>) {
    var gammaRate = 0
    counters.forEachIndexed { index, info ->
        val numberToShift = if (info.amountOfOnes > info.amountOfZeros) {
            1
        } else {
            0
        }

        gammaRate = gammaRate or (numberToShift shl index)
    }

    var epsilonRate = 0
    counters.forEachIndexed { index, info ->
        val numberToShift = if (info.amountOfOnes < info.amountOfZeros) {
            1
        } else {
            0
        }

        epsilonRate = epsilonRate or (numberToShift shl index)
    }

    println("gammaRate: $gammaRate")
    println("epsilonRate: $epsilonRate")

    println("result: ${gammaRate * epsilonRate}")

}
