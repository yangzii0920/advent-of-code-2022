import java.io.File

class Day1 {

    fun maxSum(): Int {
        var max = 0
        var temp = 0
        File("src/main/resources/day1.input").forEachLine {
            if (it == "") {
                if (temp > max) {
                    max = temp
                }
                temp = 0
            } else {
                temp += it.toInt()
            }
        }
        if (temp > max) {
            max = temp
        }
        return max
    }

    fun top3Sum(): Int {
        val sums = mutableListOf<Int>()
        var temp = 0
        File("src/main/resources/day1.input").forEachLine {
            if (it == "") {
                sums.add(temp)
                temp = 0
            } else {
                temp += it.toInt()
            }
        }
        sums.add(temp)
        sums.sortDescending()
        return sums.subList(0, 3).sum()
    }
}