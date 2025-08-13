fun printSeparateLine(str: String = "_", quan: Int = 24) {
    println(str.repeat(quan))
}
fun decorate(str: String) {
    val decor = "*".repeat(str.length)
    println(decor + "\n" + str + "\n" + decor)
}