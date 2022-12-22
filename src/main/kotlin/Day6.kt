import java.io.File

class Day6 {

    fun slidingWindowUnique(numberOfConsecutiveUnique: Int): Int {
        val chars = File("src/main/resources/day6.input").readLines().first().toCharArray()
        var uniques = mutableListOf<Char>()
        var p = 0
        while (uniques.size < numberOfConsecutiveUnique && p < chars.size) {
            if (uniques.isEmpty()) {
                uniques.add(chars[p])
                p++
            } else {
                var t = 0
                while (t < uniques.size) {
                    if (uniques[t] == chars[p]) {
                        break
                    }
                    t++
                }
                if (t < uniques.size) {
                    uniques = uniques.subList(t + 1, uniques.size)
                }
                uniques.add(chars[p])
                p ++
            }
        }
        return p
    }
}