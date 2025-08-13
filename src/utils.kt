fun printSeparateLine(str: String = "_", quan: Int = 24) {
    println(str.repeat(quan))
}
fun decorate(str: String): String {
    val decor = "*".repeat(str.length)
    return decor + "\n" + str + "\n" + decor
}