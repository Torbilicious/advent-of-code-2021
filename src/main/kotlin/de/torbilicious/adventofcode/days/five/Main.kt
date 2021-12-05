package de.torbilicious.adventofcode.days.five

import de.torbilicious.adventofcode.days.five.DiagonalOrientation.*
import de.torbilicious.adventofcode.days.five.LineOrientation.*
import java.lang.Integer.max
import kotlin.math.min

data class Point(
    val x: Int,
    val y: Int
)

data class Line(
    val point1: Point,
    val point2: Point,
)

enum class LineOrientation {
    HORIZONTAL,
    VERTICAL,
    DIAGONAL
}

enum class DiagonalOrientation(val xDiff: Int, val yDiff: Int) {
    RIGHT_UP(1, 1),
    RIGHT_DOWN(1, -1),
    LEFT_DOWN(-1, -1),
    LEFT_UP(-1, 1);
}

fun main() {
//    val dataSetToUse = exampleLines
    val dataSetToUse = lines

    println(dataSetToUse)

    val lines = dataSetToUse.lines().map {
        val lineParts = it.split(" -> ")
        val point1Parts = lineParts[0].split(",")
        val point2Parts = lineParts[1].split(",")

        Line(
            Point(point1Parts[0].toInt(), point1Parts[1].toInt()),
            Point(point2Parts[0].toInt(), point2Parts[1].toInt())
        )
    }

    println(lines)

    val maxX = lines.maxOf { maxOf(it.point1.x, it.point2.x) } + 1
    val maxY = lines.maxOf { maxOf(it.point1.y, it.point2.y) } + 1

    println("maxX: $maxX")
    println("maxY: $maxY")

    val grid = Array(maxX) { IntArray(maxY) }

//    print(grid)

    lines.forEach { line ->
        val lineOrientation = if (line.point1.y == line.point2.y) {
            HORIZONTAL
        } else if (line.point1.x == line.point2.x) {
            VERTICAL
        } else {
            DIAGONAL
        }

        // for part one
//        if (lineOrientation == DIAGONAL) return@forEach


        val innerMinY = min(line.point1.y, line.point2.y)
        val innerMaxY = max(line.point1.y, line.point2.y)
        val innerMinX = min(line.point1.x, line.point2.x)
        val innerMaxX = max(line.point1.x, line.point2.x)


        when (lineOrientation) {
            VERTICAL -> {
                (innerMinY..innerMaxY).forEach {
                    grid[line.point1.x][it]++
//                print(grid)
                }
            }
            HORIZONTAL -> {
                (innerMinX..innerMaxX).forEach {
                    grid[it][line.point1.y]++
//                print(grid)
                }
            }
            DIAGONAL -> {
                val diagonalOrientation = if (line.point2.x > line.point1.x && line.point2.y > line.point1.y) {
                    RIGHT_UP
                } else if (line.point2.x > line.point1.x && line.point2.y < line.point1.y) {
                    RIGHT_DOWN
                } else if (line.point2.x < line.point1.x && line.point2.y < line.point1.y) {
                    LEFT_DOWN
                } else {
                    LEFT_UP
                }

                var currentPoint = line.point1
                do {
                    grid[currentPoint.x][currentPoint.y]++

                    currentPoint = Point(
                        currentPoint.x + diagonalOrientation.xDiff,
                        currentPoint.y + diagonalOrientation.yDiff,
                    )
                } while(currentPoint != line.point2)
            }
        }


        println(line)
//        print(grid)
    }

    print(grid)

    val fieldsWith2OrHigher = getAmountOfFieldsMatching(grid) { it >= 2 }

    println("fieldsWith2OrHigher: $fieldsWith2OrHigher")
}

fun getAmountOfFieldsMatching(grid: Array<IntArray>, matcher: (Int) -> Boolean): Int {
    var counter = 0
    grid.forEach {
        it.forEach {
            if (matcher(it)) counter++
        }
    }

    return counter
}

fun print(grid: Array<IntArray>) {
    (0 until grid[0].size).forEach { y ->
        (0 until grid.size).forEach { x ->
            val number = grid[x][y]
            if (number == 0) {
                print(".")
            } else {
                print(number)
            }
        }
        println()
    }

    println()
}
