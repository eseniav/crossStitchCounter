abstract class App(private val greeting: Greeting) {
    fun greet() {
        println(Console.format(decorate(greeting.message), greeting.color))
    }
}
class Greeting(val message: String, val color: Console.Color) {

}
