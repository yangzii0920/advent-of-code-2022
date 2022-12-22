import java.io.File

class Day7 {

    class Node(
        val name: String,
        val parent: Node?,
        val children: MutableList<Node> = mutableListOf(),
        var fileSize: Int = 0,
        var totalSize: Int = 0
    )

    fun sizeOfFiles(): Int {
        val root = Node("/", null)
        lateinit var currentNode: Node

        val cdCmdRegex = Regex("^\\$ cd (.+)$")
        val fileRegex = Regex("^(\\d+) .+")
        val dirRegex = Regex("^dir (\\w+)$")

        val sizes = mutableListOf<Int>()

        File("src/main/resources/day7.input").readLines().forEach { cmd ->
            if (cdCmdRegex.matches(cmd)) {
                currentNode = when (cdCmdRegex.find(cmd)!!.destructured.toList().first()) {
                    "/" -> {
                        root
                    }
                    ".." -> {
                        currentNode.totalSize = currentNode.fileSize + currentNode.children.map { it.totalSize }.sum()
                        sizes.add(currentNode.totalSize)
                        currentNode.parent!!
                    }
                    else -> {
                        currentNode.children.find { it.name == cdCmdRegex.find(cmd)!!.destructured.toList().first() }!!
                    }
                }
            }
            if (fileRegex.matches(cmd)) {
                currentNode.fileSize += fileRegex.find(cmd)!!.destructured.toList().first().toInt()
            }
            if (dirRegex.matches(cmd)) {
                currentNode.children.add(Node(dirRegex.find(cmd)!!.destructured.toList().first(), currentNode))
            }
        }
        currentNode.totalSize = currentNode.fileSize + currentNode.children.map { it.totalSize }.sum()
        sizes.add(currentNode.totalSize)

        return sizes.filter { it <= 100000 }.sum()
    }

    fun sizeOfDirToDelete(): Int {
        val total = 70000000
        val atLeast = 30000000

        val root = Node("/", null)
        lateinit var currentNode: Node

        val cdCmdRegex = Regex("^\\$ cd (.+)$")
        val fileRegex = Regex("^(\\d+) .+")
        val dirRegex = Regex("^dir (\\w+)$")

        val sizes = mutableListOf<Int>()

        File("src/main/resources/day7.input").readLines().forEach { cmd ->
            if (cdCmdRegex.matches(cmd)) {
                currentNode = when (cdCmdRegex.find(cmd)!!.destructured.toList().first()) {
                    "/" -> {
                        root
                    }
                    ".." -> {
                        currentNode.totalSize = currentNode.fileSize + currentNode.children.map { it.totalSize }.sum()
                        sizes.add(currentNode.totalSize)
                        currentNode.parent!!
                    }
                    else -> {
                        currentNode.children.find { it.name == cdCmdRegex.find(cmd)!!.destructured.toList().first() }!!
                    }
                }
            }
            if (fileRegex.matches(cmd)) {
                currentNode.fileSize += fileRegex.find(cmd)!!.destructured.toList().first().toInt()
            }
            if (dirRegex.matches(cmd)) {
                currentNode.children.add(Node(dirRegex.find(cmd)!!.destructured.toList().first(), currentNode))
            }
        }

        while(currentNode.parent != null) {
            currentNode.totalSize = currentNode.fileSize + currentNode.children.map { it.totalSize }.sum()
            sizes.add(currentNode.totalSize)
            currentNode = currentNode.parent!!
        }
        currentNode.totalSize = currentNode.fileSize + currentNode.children.map { it.totalSize }.sum()
        sizes.add(currentNode.totalSize)

        val needToFree = atLeast - (total - root.totalSize)
        return sizes.filter { it >= needToFree }.minOrNull()!!
    }
}

