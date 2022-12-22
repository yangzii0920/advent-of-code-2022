import java.io.File
import java.util.ArrayDeque

class Day8 {

    fun visibleTrees(): Int {
        val rows = mutableListOf<String>()
        var columns = mutableListOf<String>()
        File("src/main/resources/day8.input").readLines().forEach { row ->
            rows.add(row)
            if (columns.isEmpty())
                columns = row.toList().map { it.toString() }.toMutableList()
            else
                columns.forEachIndexed { index, c ->
                    columns[index] = "$c${row[index]}"
                }
        }
        val visible = MutableList(rows.size) { MutableList(columns.size) { 0 } }

        rows.forEachIndexed { index, row ->
            if (index == 0 || index == rows.size - 1)
                visible[index] = MutableList(columns.size) { 1 }
            else {
                val stack = ArrayDeque<Char>()
                for (i in row.indices) {
                    if (i == 0 || row[i] > stack.peek()) {
                        stack.push(row[i])
                        visible[index][i] = 1
                    }
                }
                stack.clear()
                for (i in row.length - 1 downTo 0) {
                    if (i == row.length - 1 || row[i] > stack.peek()) {
                        stack.push(row[i])
                        visible[index][i] = 1
                    }
                }
            }
        }

        columns.forEachIndexed { index, column ->
            if (index > 0 && index < columns.size - 1) {
                val stack = ArrayDeque<Char>()
                for (i in column.indices) {
                    if (i == 0 || column[i] > stack.peek()) {
                        stack.push(column[i])
                        visible[i][index] = 1
                    }
                }
                stack.clear()
                for (i in column.length - 1 downTo 0) {
                    if (i == column.length - 1 || column[i] > stack.peek()) {
                        stack.push(column[i])
                        visible[i][index] = 1
                    }
                }
            }
        }
        return visible.flatten().sum()
    }

    fun highestScore(): Int {
        val rows = mutableListOf<String>()
        var columns = mutableListOf<String>()
        File("src/main/resources/day8.input").readLines().forEach { row ->
            rows.add(row)
            if (columns.isEmpty())
                columns = row.toList().map { it.toString() }.toMutableList()
            else
                columns.forEachIndexed { index, c ->
                    columns[index] = "$c${row[index]}"
                }
        }
        val scores =
            MutableList(rows.size) { MutableList(columns.size) { mutableListOf(0, 0, 0, 0) } } // left, right, top, down

        rows.forEachIndexed { rowIndex, row ->
            val indexStack = ArrayDeque<Int>()
            for (columnIndex in row.indices) {
                when {
                    columnIndex == 0 -> {}
                    row[columnIndex] < row[columnIndex - 1] -> {
                        scores[rowIndex][columnIndex][0] = 1
                        indexStack.push(columnIndex - 1)
                    }
                    row[columnIndex] == row[columnIndex - 1] -> {
                        scores[rowIndex][columnIndex][0] = 1
                    }
                    row[columnIndex] > row[columnIndex - 1] -> {
                        while (indexStack.isNotEmpty() && row[columnIndex] > row[indexStack.peek()])
                            indexStack.pop()
                        scores[rowIndex][columnIndex][0] = columnIndex - (indexStack.peek() ?: 0)
                    }
                }
            }
            indexStack.clear()
            for (columnIndex in row.length - 1 downTo 0) {
                when {
                    columnIndex == row.length - 1 -> {}
                    row[columnIndex] < row[columnIndex + 1] -> {
                        scores[rowIndex][columnIndex][1] = 1
                        indexStack.push(columnIndex + 1)
                    }
                    row[columnIndex] == row[columnIndex + 1] -> {
                        scores[rowIndex][columnIndex][1] = 1
                    }
                    row[columnIndex] > row[columnIndex + 1] -> {
                        while (indexStack.isNotEmpty() && row[columnIndex] > row[indexStack.peek()])
                            indexStack.pop()
                        scores[rowIndex][columnIndex][1] = (indexStack.peek() ?: (row.length - 1)) - columnIndex
                    }
                }
            }
        }

        columns.forEachIndexed { columnIndex, column ->
            val indexStack = ArrayDeque<Int>()
            for (rowIndex in column.indices) {
                when {
                    rowIndex == 0 -> {}
                    column[rowIndex] < column[rowIndex - 1] -> {
                        scores[rowIndex][columnIndex][2] = 1
                        indexStack.push(rowIndex - 1)
                    }
                    column[rowIndex] == column[rowIndex - 1] -> {
                        scores[rowIndex][columnIndex][2] = 1
                    }
                    column[rowIndex] > column[rowIndex - 1] -> {
                        while (indexStack.isNotEmpty() && column[rowIndex] > column[indexStack.peek()])
                            indexStack.pop()
                        scores[rowIndex][columnIndex][2] = rowIndex - (indexStack.peek() ?: 0)
                    }
                }
            }
            indexStack.clear()
            for (rowIndex in column.length - 1 downTo 0) {
                when {
                    rowIndex == column.length - 1 -> {}
                    column[rowIndex] < column[rowIndex + 1] -> {
                        scores[rowIndex][columnIndex][3] = 1
                        indexStack.push(rowIndex + 1)
                    }
                    column[rowIndex] == column[rowIndex + 1] -> {
                        scores[rowIndex][columnIndex][3] = 1
                    }
                    column[rowIndex] > column[rowIndex + 1] -> {
                        while (indexStack.isNotEmpty() && column[rowIndex] > column[indexStack.peek()])
                            indexStack.pop()
                        scores[rowIndex][columnIndex][3] = (indexStack.peek() ?: (column.length - 1)) - rowIndex
                    }
                }
            }
        }

        var max = 0
        for (i in rows.indices) {
            for (j in columns.indices) {
                val temp = scores[i][j].fold(1) { acc, num -> acc * num }
                if (temp > max) max = temp
            }
        }
        return max
    }
}