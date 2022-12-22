import java.io.File

class Day2 {

    private val resultLookup = mapOf(
        Pair("A", "X") to 3,
        Pair("A", "Y") to 6,
        Pair("A", "Z") to 0,
        Pair("B", "X") to 0,
        Pair("B", "Y") to 3,
        Pair("B", "Z") to 6,
        Pair("C", "X") to 6,
        Pair("C", "Y") to 0,
        Pair("C", "Z") to 3,
    )
    private val scoreLookup = mapOf("X" to 1, "Y" to 2, "Z" to 3)

    fun score(): Int {
        return File("src/main/resources/day2.input").readLines().map { round ->
            val (elf, me) = round.split(" ")
            resultLookup[Pair(elf, me)]!! + scoreLookup[me]!!
        }.sum()
    }

    fun shapes(): Int {
        val scoreConverter = mapOf("X" to 0, "Y" to 3, "Z" to 6)
        return File("src/main/resources/day2.input").readLines().map { round ->
            val (elf, result) = round.split(" ")
            val action = resultLookup.filter { it.value == scoreConverter[result]!! && it.key.first == elf }.map { it.key.second }.first()
            scoreLookup[action]!! + scoreConverter[result]!!
        }.sum()
    }
}