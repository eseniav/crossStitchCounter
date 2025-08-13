abstract class App(private val greeting: String) {
    fun greet() {
        println(Console.format(decorate(greeting), Console.Color.Purple))
    }
}