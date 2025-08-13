class Console {
    enum class Color(val code: String) {
        Red("\u001b[31m"),
        Green("\u001b[32m"),
        Blue("\u001b[34m"),
        Purple("\u001b[35m"),
        Yellow("\u001b[33m"),
        Grey("\u001b[90m")
    }
    companion object {
        fun format(str: String, color: Color) = color.code + str
    }
}