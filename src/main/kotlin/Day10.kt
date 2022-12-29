import java.io.File

class Day10 {

    fun sumOfSignalStrengths(): Int {
        var strengthSum = 0
        var x = 1
        var cycle = 1
        File("src/main/resources/day10.input").readLines().forEach { instruction ->
            if (instruction == "noop") {
                if (cycle == 20 || (cycle - 20).mod(40) == 0)
                    strengthSum += x * cycle
                cycle ++
            } else {
                if (cycle == 20 || (cycle - 20).mod(40) == 0)
                    strengthSum += x * cycle
                cycle ++
                if (cycle == 20 || (cycle - 20).mod(40) == 0)
                    strengthSum += x * cycle
                cycle ++

                x += instruction.split(" ").last().toInt()
            }
        }
        return strengthSum
    }

    fun render() {
        var drawing = ""
        var x = 1
        var cycle = 1
        File("src/main/resources/day10.input").readLines().forEach { instruction ->
            drawing += if (listOf(x.mod(40), (x + 1).mod(40), (x + 2).mod(40)).contains(cycle.mod(40))) "#" else "."
            if (drawing.length == 40) {
                println(drawing)
                drawing = ""
            }
            cycle ++

            if (instruction != "noop") {
                drawing += if (listOf(x.mod(40), (x + 1).mod(40), (x + 2).mod(40)).contains(cycle.mod(40))) "#" else "."
                if (drawing.length == 40) {
                    println(drawing)
                    drawing = ""
                }
                cycle ++

                x += instruction.split(" ").last().toInt()
            }
        }
    }
}