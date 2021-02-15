import kotlin.math.pow
import kotlin.math.sqrt

fun readInput(): Array<DoubleArray> {
    val row = readLine()!!.split(' ').map(String::toDouble).toDoubleArray()
    val n = row.size
    val matrix: Array<DoubleArray> = Array(n) {DoubleArray(n)}
    matrix[0] = row

    var i = 1
    while(i < n) {
        val row = readLine()!!.split(' ').map(String::toDouble).toDoubleArray()
        matrix[i] = row
        i++
    }

    return matrix
}

fun printAnswer(l: Array<DoubleArray>) {
    println("L:")
    for (i in l.indices) {
        for (j in l[i].indices) {
            print(l[i][j])
            print(' ')
        }
        println()
    }
    println()

    println("L^T:")
    for (i in l.indices) {
        for (j in l[i].indices) {
            print(l[j][i])
            print(' ')
        }
        println()
    }
}

fun choleskyDecomposition(matrix: Array<DoubleArray>): Array<DoubleArray> {
    val n = matrix.size
    val l = Array(n) {(Array(n) {0.0}).toDoubleArray() }

    for (j in 0 until n) {
        for (i in 0..j) {
            if (i == j) {
                var sum = 0.0
                for (p in 0 until i) {
                    sum += l[i][p].pow(2)
                }
                l[i][i] = sqrt(matrix[i][i] - sum)
            } else {
                var sum = 0.0
                for (p in 0 until i) {
                    sum += l[i][p] * l[j][p]
                }
                l[j][i] = (matrix[j][i] - sum) / l[i][i]
            }
        }
    }

    printAnswer(l)
    return l
}

fun main() {
    val matrix = readInput()
    choleskyDecomposition(matrix)
}
