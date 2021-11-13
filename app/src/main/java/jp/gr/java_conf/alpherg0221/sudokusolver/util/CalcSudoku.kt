package jp.gr.java_conf.alpherg0221.sudokusolver.util

class NoAnswerException(msg: String = "No Answer in this case") : RuntimeException(msg)


class CalcSudoku(private val field: MutableList<MutableList<Int>>) {
    private fun initialCheck() {
        // 同じ行に同じ数字がないかチェック
        field.transpose().forEach { column ->
            for (i in 1..9) {
                if (column.filter { it == i }.size > 1) throw NoAnswerException()
            }
        }
        // 同じ列に同じ数字がないかチェック
        field.forEach { row ->
            for (i in 1..9) {
                if (row.filter { it == i }.size > 1) throw NoAnswerException()
            }
        }
        // 3x3に同じ数字がないかチェック
        field.massToFlat().forEach { mass ->
            for (i in 1..9) {
                if (mass.filter { it == i }.size > 1) throw NoAnswerException()
            }
        }
    }

    private fun checkVertical(c: Int, n: Int): Boolean = !field.map { it[c] }.contains(n)

    private fun checkHorizontal(r: Int, n: Int): Boolean = !field[r].contains(n)

    private fun checkMass(r: Int, c: Int, n: Int): Boolean {
        val sr = (r / 3) * 3
        val sc = (c / 3) * 3

        for (i in sr until sr + 3) {
            for (j in sc until sc + 3) {
                if (field[i][j] == n) return false
            }
        }
        return true
    }

    private fun change(r: Int, c: Int, n: Int): Boolean {
        if (checkVertical(c, n) && checkHorizontal(r, n) && checkMass(r, c, n)) {
            field[r][c] = n
            return true
        }
        return false
    }

    private fun dfs(r: Int, c: Int): Boolean {
        if (r > 8) {
            return true
        } else if (c > 8) {
            if (dfs(r + 1, 0)) return true
        } else if (field[r][c] != 0) {
            if (dfs(r, c + 1)) return true
        } else {
            for (i in 1..9) {
                if (change(r, c, i)) {
                    if (dfs(r, c + 1)) return true
                }
            }
            field[r][c] = 0
        }
        return false
    }

    fun calculate(): List<List<Int>> {
        initialCheck()
        return if (dfs(0, 0)) {
            field
        } else {
            emptyList()
        }
    }
}

fun <T> List<List<T>>.transpose(): List<List<T>> =
    this.first().mapIndexed { index, _ ->
        this.map { row -> row[index] }
    }

fun <T> List<List<T>>.massToFlat(): List<List<T>> {
    val list = this.map { it.toMutableList() }.toMutableList()
    for (i in 0 until 9) for (j in 0 until 9) list[i][j] = this[(i / 3) * 3 + j / 3][j % 3 + ((i % 3) * 3)]
    return list
}