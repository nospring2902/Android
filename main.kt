import kotlin.math.abs

class Fraction(var numerator: Int, var denominator: Int) {

    // Nhập phân số từ bàn phím
    fun input() {
        while (true) {
            print("Enter numerator: ")
            numerator = readln().toInt()
            print("Enter denominator: ")
            denominator = readln().toInt()
            if (denominator != 0) break
            println("Denominator cannot be zero, please enter again!")
        }
    }

    // In phân số
    fun display() {
        println("$numerator/$denominator")
    }

    // Tối giản phân số
    fun simplify() {
        val gcdValue = gcd(abs(numerator), abs(denominator))
        numerator /= gcdValue
        denominator /= gcdValue
        // chuẩn hóa mẫu dương
        if (denominator < 0) {
            numerator = -numerator
            denominator = -denominator
        }
    }

    // So sánh với một phân số khác
    fun compare(fraction: Fraction): Int {
        val left = this.numerator * fraction.denominator
        val right = fraction.numerator * this.denominator
        return when {
            left < right -> -1
            left == right -> 0
            else -> 1
        }
    }

    // Cộng với một phân số khác
    fun add(fraction: Fraction): Fraction {
        val newNumerator = this.numerator * fraction.denominator + fraction.numerator * this.denominator
        val newDenominator = this.denominator * fraction.denominator
        val result = Fraction(newNumerator, newDenominator)
        result.simplify()
        return result
    }

    companion object {
        fun gcd(a: Int, b: Int): Int {
            return if (b == 0) a else gcd(b, a % b)
        }
    }
}

fun main() {
    print("Enter number of fractions: ")
    val n = readln().toInt()
    val arr = Array(n) { Fraction(0, 1) }

    // Nhập mảng phân số
    for (i in arr.indices) {
        println("Enter fraction ${i + 1}:")
        arr[i].input()
    }

    println("\n Array of entered fractions:")
    arr.forEach { it.display() }

    // Tối giản và in
    println("\n Array of fractions after simplification:")
    arr.forEach { 
        it.simplify()
        it.display()
    }

    // Tính tổng
    var sum = Fraction(0, 1)
    for (fraction in arr) sum = sum.add(fraction)
    println("\n Sum of fractions:")
    sum.display()

    // Tìm max
    var max = arr[0]
    for (fraction in arr) {
        if (fraction.compare(max) > 0) max = fraction
    }
    println("\n Largest fraction:")
    max.display()

    // Sắp xếp giảm dần
    arr.sortWith { a, b -> b.compare(a) }
    println("\n Array of fractions after sorting in descending order:")
    arr.forEach { it.display() }
}
