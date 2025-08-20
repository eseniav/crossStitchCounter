import java.time.LocalDate

abstract class App(private val greeting: Greeting) {
    fun greet() {
        println(greeting)
    }
    fun login() {
        println("Логин")
    }
    fun checkCancel(str: String) = str.lowercase() == "отмена"
    fun handleCancel(str: String) {
        if(checkCancel(str)) {
            throw Exception("cancel")
        }
    }
    fun regester() {
        println(Console.format("Регистрация нового пользователя", Console.Color.Blue))
        println("Для отмены регистрации и возврата в главное меню напишите слово \"отмена\"")
        try {
            print("Имя: ")
            val userName = readln()
            handleCancel(userName)
            print("Фамилия: ")
            val lName = readln()
            handleCancel(lName)
            print("Логин: ")
            val login = readln()
            handleCancel(login)
            print("Пароль: ")
            val password = readln()
            handleCancel(password)
            print("Повторите пароль: ")
            val passRep = readln()
            handleCancel(passRep)
            print("Дата рождения(дд.мм.гггг): ")
            val bDate = readln()
            handleCancel(bDate)
            print("Телефон: ")
            val phone = readln()
            handleCancel(phone)
            print("Email: ")
            val email = readln()
            handleCancel(email)
        }
        catch (ex: Exception) {
            if(ex.message == "cancel") {
                println(Console.format("\t Отмена регистрации пользователя. Переход в главное меню", Console.Color.Grey) + "\n")
                mainMenu()
            } else {
                throw ex
            }
        }
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
