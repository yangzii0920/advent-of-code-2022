import java.io.File
import java.util.ArrayDeque

class Day5 {

    fun topCrates9000(): String {
        val lines = File("src/main/resources/day5.input").readLines()
        var breakLine = 0
        for (i in lines.indices) {
            if (lines[i] == "") {
                breakLine = i
                break
            }
        }

        val numberOfCrate = lines[breakLine - 1].replace(" ", "").length
        val crates = mutableMapOf<Int, ArrayDeque<Char>>()
        for (i in 1..numberOfCrate) {
            crates[i] = ArrayDeque()
        }

        for (lineNumber in breakLine - 2 downTo 0) {
            for (crateIndex in 1..numberOfCrate) {
                val letterIndex = (crateIndex - 1) * 4 + 1
                if (letterIndex < lines[lineNumber].length && lines[lineNumber][letterIndex] != ' ') {
                    crates[crateIndex]!!.push(lines[lineNumber][letterIndex])
                }
            }
        }

        for (i in breakLine + 1 until lines.size) {
            val (countToMove, moveFrom, moveTo) = Regex("move (\\d+) from (\\d+) to (\\d+)").find(lines[i])!!.destructured.toList()
                .map { it.toInt() }
            for (times in 1..countToMove) {
                crates[moveTo]!!.push(crates[moveFrom]!!.pop())
            }
        }

        return crates.map { (index, stack) -> Pair(index, stack.pop()) }.sortedBy { it.first }.map { it.second }
            .joinToString("")
    }


    fun topCrates9001(): String {
        val lines = File("src/main/resources/day5.input").readLines()
        var breakLine = 0
        for (i in lines.indices) {
            if (lines[i] == "") {
                breakLine = i
                break
            }
        }

        val numberOfCrate = lines[breakLine - 1].replace(" ", "").length
        val crates = mutableMapOf<Int, ArrayDeque<Char>>()
        for (i in 1..numberOfCrate) {
            crates[i] = ArrayDeque()
        }

        for (lineNumber in breakLine - 2 downTo 0) {
            for (crateIndex in 1..numberOfCrate) {
                val letterIndex = (crateIndex - 1) * 4 + 1
                if (letterIndex < lines[lineNumber].length && lines[lineNumber][letterIndex] != ' ') {
                    crates[crateIndex]!!.push(lines[lineNumber][letterIndex])
                }
            }
        }

        for (i in breakLine + 1 until lines.size) {
            val (countToMove, moveFrom, moveTo) = Regex("move (\\d+) from (\\d+) to (\\d+)").find(lines[i])!!.destructured.toList().map { it.toInt() }
            val cratesToMove = ArrayDeque<Char>()
            for (times in 1..countToMove) {
                cratesToMove.push(crates[moveFrom]!!.pop())
            }
            for (times in 1..countToMove) {
                crates[moveTo]!!.push(cratesToMove.pop())
            }
        }

        return crates.map { (index, stack) -> Pair(index, stack.pop()) }.sortedBy { it.first }.map { it.second }.joinToString("")
    }

}