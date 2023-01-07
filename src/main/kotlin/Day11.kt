import java.io.File

class Day11 {

    fun levelOfMonkeyBusinessOfTwoMostActiveMonkey(round: Int, relief: Int): Long {
        val monkeys = readFile()
        repeat(round) { playRounds(monkeys, relief) }
        return monkeys.asSequence().map(Monkey::count).map(Int::toLong).sortedDescending().take(2).reduce(Long::times)
    }

    private fun readFile(): List<Monkey> {
        val lines = File("src/main/resources/day11.input").readLines().filter(String::isNotEmpty)
        return lines.chunked(6).map { config ->
            val id = Regex("^Monkey ([0-9]+):$").find(config[0])!!.destructured.toList().first().toInt()
            val items = config[1].trim().split("items:")[1].split(",").map { it.trim().toLong() }.toMutableList()
            val divisibleBy = Regex("\\s+Test: divisible by ([0-9]+)").find(config[3])!!.destructured.toList().first().toInt()
            val test = { item: Long -> (if (item % divisibleBy == 0L) config[4] else config[5]).trim().split(" ")[5].toInt() }
            val operate = { old: Long ->
                when (config[2].split("new = old")[1].trim()[0]) {
                    '+' -> old + (config[2].split("new = old")[1].split(" ").last().toLongOrNull() ?: old)
                    '*' -> old * (config[2].split("new = old")[1].split(" ").last().toLongOrNull() ?: old)
                    else -> throw IllegalArgumentException()
                }
            }
            Monkey(id, items, divisibleBy, test, operate)
        }
    }

    private fun playRounds(monkeys: List<Monkey>, relief: Int) {
        val fullRoundProduction = monkeys.map(Monkey::divisibleBy).reduce(Int::times)
        monkeys.forEach { monkey ->
            monkey.count += monkey.items.size
            while (monkey.items.isNotEmpty()) {
                val item = monkey.items.removeFirst()
                val new = monkey.operate(item) / relief % fullRoundProduction
                monkeys.find { it.id == monkey.test(new) }!!.items.add(new)
            }
        }
    }

    data class Monkey(
        val id: Int,
        val items: MutableList<Long>,
        val divisibleBy: Int,
        val test: (Long) -> Int,
        val operate: (Long) -> Long,
        var count: Int = 0
    )
}