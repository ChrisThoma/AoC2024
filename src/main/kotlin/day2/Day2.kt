package day2

import ProblemRunner
import java.io.File
import kotlin.math.abs

class Day2 : ProblemRunner {
    override val filePath: String
        get() = "src/main/kotlin/day2/input.txt"

    override fun answerPartOne(input: File): String {
        var answerCount = 0
        input.forEachLine { line ->
            var prevDifference = 0
            line.split(' ')
                .map { num ->
                    num.toInt()
                }
                .zipWithNext { first, second ->
                    val absDiff = abs(first - second)
                    val diff = first - second
                    val isSeqContinued = (prevDifference == 0) || (prevDifference > 0 && diff > 0) || (prevDifference < 0 && diff < 0)
                    prevDifference = diff
                    absDiff in 1..3 && isSeqContinued
                }
                .all {
                    it
                }.also {
                    if (it) {
                        answerCount++
                    }
                }
        }
        return "$answerCount safe"
    }

    override fun answerPartTwo(input: File): String {
        var answerCount = 0
        var dampened = 0
        input.forEachLine { line ->
            val report = line.split(' ')
                .map { num ->
                    num.toInt()
                }

            if (isGradual(report) && isSequential(report)) {
                answerCount++
            } else {
                for (i in report.indices) {
                    val sliced = report.toMutableList()
                    val removed = sliced.removeAt(i)
                    if (isGradual(sliced) && isSequential(sliced)) {
                        dampened++
                        break
                    }
                }
            }
        }
        return "$answerCount safe with $dampened dampened"
    }

    private fun isGradual(levels: List<Int>): Boolean {
        for (i in 1..levels.lastIndex) {
            val diff = abs(levels[i] - levels[i-1])
            if (diff !in 1..3) {
                return false
            }
        }
        return true
    }

    private fun isSequential(levels: List<Int>): Boolean {
        var didIncrease = false
        var didDecrease = false
        for (i in 1..levels.lastIndex) {
            if (levels[i] > levels[i-1]) {
                didIncrease = true
            }
            if (levels[i] < levels[i-1]) {
                didDecrease = true
            }
            if (didDecrease && didIncrease) {
                return false
            }
        }
        return didIncrease || didDecrease
    }
}

fun main(args: Array<String>) {
    val day2 = Day2()
    println(day2.answerPartOne(day2.readFile()))
    println(day2.answerPartTwo(day2.readFile()))
}