package day2

import ProblemRunner
import java.io.File
import kotlin.math.abs

class Day2 : ProblemRunner {
    override val filePath: String
        get() = "src/main/kotlin/day2/test_input.txt"

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
        input.forEachLine { line ->
            val report = line.split(' ')
                .map { num ->
                    num.toInt()
                }

            var lastDiff = 0
            var canIgnore = true
            var isReportSafe = true
            println(report.toString())
            for (i in 0..<report.size-1) {
                println("lastDiff = $lastDiff")
                val absDiff = abs(report[i+1] - report[i])
                val diff = report[i+1] - report[i]
                val isSeqContinued = (lastDiff == 0) || (lastDiff > 0 && diff > 0) || (lastDiff < 0 && diff < 0)
                val isSafeStep = absDiff in 1..3 && isSeqContinued
                if (canIgnore && !isSafeStep) {
                    canIgnore = false
                } else if (!canIgnore && !isSafeStep) {
                    isReportSafe = false
                } else {
                    lastDiff = diff
                }
            }
            if (isReportSafe) { answerCount++ }
        }
        return "$answerCount safe with tolerance"
    }
}

fun main(args: Array<String>) {
    val day2 = Day2()
    println(day2.answerPartOne(day2.readFile()))
    println(day2.answerPartTwo(day2.readFile()))
}