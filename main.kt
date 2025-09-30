import kotlin.math.abs

class PhanSo(var tu: Int, var mau: Int) {

    // Nhập phân số từ bàn phím
    fun nhap() {
        while (true) {
            print("Nhập tử số: ")
            tu = readln().toInt()
            print("Nhập mẫu số: ")
            mau = readln().toInt()
            if (mau != 0) break
            println("Mẫu số không được bằng 0, vui lòng nhập lại!")
        }
    }

    // In phân số
    fun xuat() {
        println("$tu/$mau")
    }

    // Tối giản phân số
    fun toiGian() {
        val ucln = gcd(abs(tu), abs(mau))
        tu /= ucln
        mau /= ucln
        // chuẩn hóa mẫu dương
        if (mau < 0) {
            tu = -tu
            mau = -mau
        }
    }

    // So sánh với một phân số khác
    fun soSanh(ps: PhanSo): Int {
        val left = this.tu * ps.mau
        val right = ps.tu * this.mau
        return when {
            left < right -> -1
            left == right -> 0
            else -> 1
        }
    }

    // Cộng với một phân số khác
    fun cong(ps: PhanSo): PhanSo {
        val tuMoi = this.tu * ps.mau + ps.tu * this.mau
        val mauMoi = this.mau * ps.mau
        val kq = PhanSo(tuMoi, mauMoi)
        kq.toiGian()
        return kq
    }

    companion object {
        fun gcd(a: Int, b: Int): Int {
            return if (b == 0) a else gcd(b, a % b)
        }
    }
}

fun main() {
    print("Nhập số lượng phân số: ")
    val n = readln().toInt()
    val arr = Array(n) { PhanSo(0, 1) }

    // Nhập mảng phân số
    for (i in arr.indices) {
        println("Nhập phân số thứ ${i + 1}:")
        arr[i].nhap()
    }

    println("\n📌 Mảng phân số vừa nhập:")
    arr.forEach { it.xuat() }

    // Tối giản và in
    println("\n📌 Mảng phân số sau khi tối giản:")
    arr.forEach { 
        it.toiGian()
        it.xuat()
    }

    // Tính tổng
    var tong = PhanSo(0, 1)
    for (ps in arr) tong = tong.cong(ps)
    println("\n📌 Tổng các phân số:")
    tong.xuat()

    // Tìm max
    var max = arr[0]
    for (ps in arr) {
        if (ps.soSanh(max) > 0) max = ps
    }
    println("\n📌 Phân số lớn nhất:")
    max.xuat()

    // Sắp xếp giảm dần
    arr.sortWith { a, b -> b.soSanh(a) }
    println("\n📌 Mảng phân số sau khi sắp xếp giảm dần:")
    arr.forEach { it.xuat() }
}
