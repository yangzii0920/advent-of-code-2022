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

    @Test
    fun day5part1 () {
        println(Day5().topCrates9000())
    }

    @Test
    fun day5part2 () {
        println(Day5().topCrates9001())
    }


    @Test
    fun day6part1 () {
        println(Day6().slidingWindowUnique(4))
    }

    @Test
    fun day6part2 () {
        println(Day6().slidingWindowUnique(14))
    }

    @Test
    fun day7part1 () {
        println(Day7().sizeOfFiles())
    }


    @Test
    fun day7part2 () {
        println(Day7().sizeOfDirToDelete())
    }

    @Test
    fun day8part1 () {
        println(Day8().visibleTrees())
    }


    @Test
    fun day8part2 () {
        println(Day8().highestScore())
    }

    @Test
    fun day9part1 () {
        println(Day9().numberOfPositionsTailVisited(2))
    }

    @Test
    fun day9part2 () {
        println(Day9().numberOfPositionsTailVisited(10))
    }

    @Test
    fun day10part1 () {
        println(Day10().sumOfSignalStrengths())
    }


    @Test
    fun day10part2 () {
        Day10().render()
    }

    @Test
    fun day11part1 () {
        println(Day11().levelOfMonkeyBusinessOfTwoMostActiveMonkey(20, 3))
    }

    @Test
    fun day11part2 () {
        println(Day11().levelOfMonkeyBusinessOfTwoMostActiveMonkey(10000, 1))
    }

}