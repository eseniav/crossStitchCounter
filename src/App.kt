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
    fun logout() {
        println("Завершить сеанс")
    }
    fun mainMenu() {
        println("Выберите действие: 1.Войти, 2.Зарегистрироваться, 3.Выйти, 0.Завершить работу")
        when(readln()) {
            "1" -> login()
            "2" -> regester()
            "3" -> logout()
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
