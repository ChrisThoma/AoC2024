import java.io.File

interface ProblemRunner {
    fun read(path: String) : File
    fun answerPartOne(input: File) : String
    fun answerPartTwo(input: File) : String
}