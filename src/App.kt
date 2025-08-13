abstract class App(private val greeting: Greeting) {
    fun greet() {
        println(greeting)
    }
}
data class Greeting(val message: String, val color: Console.Color) {
    override fun toString() = Console.format(decorate(message), color)
}
