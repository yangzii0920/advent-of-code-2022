import java.io.File

class Day3 {

    fun sumOfPriority(): Int {
        return File("src/main/resources/day3.input").readLines().map { items ->
            val compartmentOne = items.substring(0, items.length / 2).toList()
            val compartmentTwo = items.substring(items.length / 2, items.length).toList()

            val common = compartmentOne.intersect(compartmentTwo).first()

            if (common.code >= 97)
                common.code - 96
            else
                common.code - 38
        }.sum()
    }

    fun sumOfBadges(): Int {
        val bags = File("src/main/resources/day3.input").readLines()
        var sum = 0
        for (i in 0..bags.size / 3 - 1) {
            val common = bags[i * 3].toList().intersect(bags[i * 3 + 1].toList()).intersect(bags[i * 3 + 2].toList()).first()
            sum += if (common.code >= 97)
                common.code - 96
            else
                common.code - 38
        }
        return sum
    }

}