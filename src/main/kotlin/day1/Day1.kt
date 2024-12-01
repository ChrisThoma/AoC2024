package day1

import ProblemRunner
import java.io.File
import java.util.Scanner
import kotlin.math.abs

class Day1 : ProblemRunner {
    override val filePath: String
        get() = "src/main/kotlin/day1/input.txt"

    override fun answerPartOne(input: File): String {
        val scanner = Scanner(input)

        val firstList = mutableListOf<Int>()
        val secondList = mutableListOf<Int>()

        var i = 0
        while (scanner.hasNextInt()) {
            if (i % 2 == 0) firstList.add(scanner.nextInt()) else secondList.add(scanner.nextInt())
            i++
        }

        firstList.sort()
        secondList.sort()

        val distanceList = mutableListOf<Int>()
        firstList.forEachIndexed { index, num ->
            val secondNum = secondList[index]
            println("$num - $secondNum = ${num - secondNum}")
            distanceList.add(abs(num - secondNum))
        }
        val sum = distanceList.sum()
        return "sum = $sum"
    }

    override fun answerPartTwo(input: File): String {
        val scanner = Scanner(input)
        val firstList = mutableSetOf<Int>()
        val secondList = mutableMapOf<Int, Int>()

        var i = 0
        while (scanner.hasNextInt()) {
            if (i % 2 == 0) {
                firstList.add(scanner.nextInt())
            } else {
                val num = scanner.nextInt()
                val count = secondList.getOrDefault(num, 0)
                secondList[num] = count+1
            }
            i++
        }

        var sum = 0
        firstList.forEach { num ->
            val count = secondList.getOrDefault(num, 0)
            sum += num * count
        }
        return "Score = $sum"
    }
}

fun main(args: Array<String>) {
    val day1 = Day1()
    val input = day1.readFile()
    val solution1 = day1.answerPartOne(input)
    println(solution1)
    val solution2 = day1.answerPartTwo(input)
    println(solution2)
}