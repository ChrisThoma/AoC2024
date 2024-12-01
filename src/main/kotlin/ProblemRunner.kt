import java.io.File

interface ProblemRunner {
    val filePath: String
    fun read(path: String) : File
    fun answerPartOne(input: File) : String
    fun answerPartTwo(input: File) : String
}