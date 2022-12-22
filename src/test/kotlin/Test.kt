import org.junit.Test

internal class Test {
    @Test
    fun day1part1 () {
        println(Day1().maxSum())
    }

    @Test
    fun day1part2 () {
        println(Day1().top3Sum())
    }

    @Test
    fun day2part1 () {
        println(Day2().score())
    }

    @Test
    fun day2part2 () {
        println(Day2().shapes())
    }

    @Test
    fun day3part1 () {
        println(Day3().sumOfPriority())
    }

    @Test
    fun day3part2 () {
        println(Day3().sumOfBadges())
    }

    @Test
    fun day4part1 () {
        println(Day4().countFullOverlap())
    }

    @Test
    fun day4part2 () {
        println(Day4().countOverlap())
    }

}