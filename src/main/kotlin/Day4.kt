import java.io.File

class Day4 {

    fun countFullOverlap(): Int {
        return File("src/main/resources/day4.input").readLines().count { pair ->
            val (firstElf, secondElf) = pair.split(",")

            val (firstElfStart, firstElfEnd) = firstElf.split("-").map { it.toInt() }
            val (secondElfStart, secondElfEnd) = secondElf.split("-").map { it.toInt() }

            (firstElfStart.coerceAtMost(secondElfStart) == firstElfStart && firstElfEnd.coerceAtLeast(secondElfEnd) == firstElfEnd)
            || (firstElfStart.coerceAtMost(secondElfStart) == secondElfStart && firstElfEnd.coerceAtLeast(secondElfEnd) == secondElfEnd)
        }
    }

    fun countOverlap(): Int {
        return File("src/main/resources/day4.input").readLines().count { pair ->
            val (firstElf, secondElf) = pair.split(",")

            val (firstElfStart, firstElfEnd) = firstElf.split("-").map { it.toInt() }
            val (secondElfStart, secondElfEnd) = secondElf.split("-").map { it.toInt() }

            (firstElfStart in secondElfStart..secondElfEnd) || (secondElfStart in firstElfStart..firstElfEnd)
        }
    }
}