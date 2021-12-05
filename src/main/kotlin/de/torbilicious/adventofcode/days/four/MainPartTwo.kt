package de.torbilicious.adventofcode.days.four


fun main() {
//    val numbers = exampleBingoInput.lines().first().split(",").map { it.toInt() }
    val numbers = bingoInput.lines().first().split(",").map { it.toInt() }

    println(numbers)

    val remainingText =
        bingoInput.replace(bingoInput.lines()[0] + bingoInput.lines()[1] + "\n\n", "")
//        exampleBingoInput.replace(exampleBingoInput.lines()[0] + exampleBingoInput.lines()[1] + "\n\n", "")

    val boards = remainingText.split("\n\n").map { BingoBoard(it) }
    boards.forEach { println(it) }

    val boardsToTime: MutableMap<BingoBoard, Int> = boards.associateWith { 0 }.toMutableMap()

    var allBoardsHaveBingoed = false
    var numberIndex = 0
    while (!allBoardsHaveBingoed) {
        val number = numbers[numberIndex]

        boardsToTime.forEach {
            if (!it.key.hasBingo()) {
                it.key.addNumber(number)
            }
        }

        allBoardsHaveBingoed = boards.all { it.hasBingo() }

        if (allBoardsHaveBingoed) {
            println(numbers[numberIndex])
            val bingoBoard = boardsToTime.filter { it.value == 0 }.keys.first()

            val result = bingoBoard.calculateSumOfUnmarkedFields() * numbers[numberIndex]
            println("result: $result")
        }

        boardsToTime.filter { it.key.hasBingo() }.forEach { boardsToTime[it.key] = it.value + 1 }
        numberIndex++
    }

}

