abstract class App(private val greeting: String) {
    fun greet() {
        decorate(greeting)
    }
}