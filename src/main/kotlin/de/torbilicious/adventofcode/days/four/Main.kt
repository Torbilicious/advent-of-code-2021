package de.torbilicious.adventofcode.days.four

class BingoBoard(
    private val input: String
) {
    val board: Array<IntArray>
    val solvedState: Array<BooleanArray>

    private val numberOfRows: Int
    private val numberOfColumns: Int

    init {
        numberOfRows = input.lines().size
        numberOfColumns = input.lines().first().trim().replace("  ", " ").split(" ").size

        board = Array(numberOfRows) { IntArray(numberOfColumns) }
        solvedState = Array(numberOfRows) { BooleanArray(numberOfColumns) }

        input.lines().forEachIndexed { rowIndex, line ->
            val parts = line.trim().replace("  ", " ").split(" ")

//            println(parts)

            parts.forEachIndexed { columnIndex, digitString ->
                board[rowIndex][columnIndex] = digitString.toInt()
            }
        }
    }


    fun hasBingo(): Boolean {
        (0 until numberOfRows).forEach { row ->
            if ((0 until numberOfColumns).all { col ->
                    solvedState[row][col]
                }) {
                return true
            }
        }

        (0 until numberOfColumns).forEach { col ->
            if ((0 until numberOfRows).all { row ->
                    solvedState[row][col]
                }) {
                return true
            }
        }

        return false
    }

    fun addNumber(number: Int): Boolean {
        (0 until numberOfRows).forEach { row ->
            (0 until numberOfColumns).forEach { col ->
                if (board[row][col] == number) {
                    solvedState[row][col] = true
                }
            }
        }

        return hasBingo()
    }

    fun calculateSumOfUnmarkedFields(): Int {
        var sum = 0
        (0 until numberOfRows).forEach { row ->
            (0 until numberOfColumns).forEach { col ->
                if (!solvedState[row][col]) {
                    sum += board[row][col]
                }
            }
        }

        return sum
    }

    override fun toString(): String {
        return "BingoBoard(board=${board.contentDeepToString()}, solvedState=${solvedState.contentDeepToString()})"
    }
}

fun main() {
//    val numbers = exampleBingoInput.lines().first().split(",").map { it.toInt() }
    val numbers = bingoInput.lines().first().split(",").map { it.toInt() }

    println(numbers)

    val remainingText =
        bingoInput.replace(bingoInput.lines()[0] + bingoInput.lines()[1] + "\n\n", "")
//        exampleBingoInput.replace(exampleBingoInput.lines()[0] + exampleBingoInput.lines()[1] + "\n\n", "")

    val boards = remainingText.split("\n\n").map { BingoBoard(it) }
    boards.forEach { println(it) }

    var bingo = false
    var numberIndex = 0
    while (!bingo) {
        val number = numbers[numberIndex]

        boards.forEach { it.addNumber(number) }

        val bingoBoard = boards.find { it.hasBingo() }

        bingo = bingoBoard != null

        if (bingo) {
            println(numbers[numberIndex])

            val result = bingoBoard!!.calculateSumOfUnmarkedFields() * numbers[numberIndex]
            println("result: $result")
        }

        numberIndex++
    }

}

