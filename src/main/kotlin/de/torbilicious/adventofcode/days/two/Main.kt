package de.torbilicious.adventofcode.days.two

fun main() {
    main2()
}

fun main1() {
    var x = 0
    var depth = 0

    diveInstructions.lines().forEach { line ->
        val parts = line.split(" ")
//        println(parts)

        when (parts[0]) {
            "forward" -> x += parts[1].toInt()
            "down" -> depth += parts[1].toInt()
            "up" -> depth -= parts[1].toInt()
        }
    }

    println(x)
    println(depth)

    println(x * depth)
}

fun main2() {
    var x = 0
    var depth = 0
    var aim = 0

    diveInstructions.lines().forEach { line ->
        val parts = line.split(" ")
//        println(parts)

        when (parts[0]) {
            "forward" -> {
                x += parts[1].toInt()
                depth += (aim * parts[1].toInt())
            }
            "down" -> aim += parts[1].toInt()
            "up" -> aim -= parts[1].toInt()
        }
    }

    println(x)
    println(depth)

    println(x * depth)
}
