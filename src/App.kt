abstract class App(private val greeting: Greeting) {
    fun greet() {
        println(greeting)
    }
    fun login() {
        println("Логин")
    }
    fun regester() {
        println("Регистрация")
    }
    fun exit() {
        println("Выход")
    }
    fun mainMenu() {
        println("Выберите действие: 1.Войти, 2.Зарегистрироваться, 0.Выйти")
        when(readln()) {
            "1" -> login()
            "2" -> regester()
            "0" -> exit()
            else -> {
                println("Неверный ввод. Выберите один из трех вариантов")
                mainMenu()
            }
        }

    }
}
data class Greeting(val message: String, val color: Console.Color) {
    override fun toString() = Console.format(decorate(message), color)
}
