import java.io.File
import kotlin.math.absoluteValue

class Day9 {

    private val move = mapOf(
        "U" to Pair(0, 1),
        "D" to Pair(0, -1),
        "L" to Pair(-1, 0),
        "R" to Pair(1, 0),
    )

    fun numberOfPositionsTailVisited(numberOfKnot: Int): Int {
        val positions = MutableList(numberOfKnot) { Pair(0, 0) }
        val visited = mutableSetOf(Pair(0, 0))

        File("src/main/resources/day9.input").readLines().map { action ->
            val (direction, steps) = action.split(" ")
            for (step in 1..steps.toInt()) {
                var hx = positions[0].first
                var hy = positions[0].second
                hx += move[direction]!!.first
                hy += move[direction]!!.second
                positions[0] = Pair(hx, hy)
                for (knotIndex in 1 until positions.size) {
                    val (tx, ty) = updateAdjacent(positions[knotIndex - 1], positions[knotIndex])
                    if (tx == positions[knotIndex].first && ty == positions[knotIndex].second) {
                        break
                    }
                    else
                        positions[knotIndex] = Pair(tx, ty)
                }
                visited.add(positions.last())
            }
        }
        return visited.size
    }

    private fun updateAdjacent(headPosition: Pair<Int, Int>, tailPosition: Pair<Int, Int>): Pair<Int, Int> {
        var tx = tailPosition.first
        var ty = tailPosition.second
        if ((tailPosition.first - headPosition.first).absoluteValue * (tailPosition.second - headPosition.second).absoluteValue == 0) {
            if ((tailPosition.first - headPosition.first).absoluteValue > 1)
                tx += 0 - (tailPosition.first - headPosition.first) / (tailPosition.first - headPosition.first).absoluteValue
            if ((tailPosition.second - headPosition.second).absoluteValue > 1)
                ty += 0 - (tailPosition.second - headPosition.second) / (tailPosition.second - headPosition.second).absoluteValue
        } else if ((tailPosition.first - headPosition.first).absoluteValue > 1 || (tailPosition.second - headPosition.second).absoluteValue > 1) {
            tx += 0 - (tailPosition.first - headPosition.first) / (tailPosition.first - headPosition.first).absoluteValue
            ty += 0 - (tailPosition.second - headPosition.second) / (tailPosition.second - headPosition.second).absoluteValue
        }
        return Pair(tx, ty)
    }

}