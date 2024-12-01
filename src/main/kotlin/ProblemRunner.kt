import java.io.File

interface ProblemRunner {
    val filePath: String
    fun readFile(): File {
        return File(filePath)
    }
    fun answerPartOne(input: File) : String
    fun answerPartTwo(input: File) : String
}