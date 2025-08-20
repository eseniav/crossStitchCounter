import java.time.LocalDate
import java.time.format.DateTimeFormatter

abstract class App(private val greeting: Greeting) {
    enum class MessageType(val color: Console.Color) {
        Warning(Console.Color.Yellow),
        Error(Console.Color.Red),
        Success(Console.Color.Green)
    }

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
    fun printMessage(message:String, type: MessageType) {
        println(Console.format(message, type.color))
    }
    fun handleNameInput(nameType: String): String {
        print("$nameType (${User.minName}-${User.maxName} символов): ")
        val input = readln().trim()
        handleCancel(input)
        if(!User.checkName(input)) {
            printMessage("Проверьте правильность ввода!", MessageType.Warning)
            handleNameInput(nameType)
        }
        return input
    }
    fun handleLoginInput(): String {
        print("Логин (${User.minLogin}-${User.maxLogin} символов без пробелов): ")
        val input = readln().trim()
        handleCancel(input)
        if(!User.checkLogin(input)) {
            printMessage("Проверьте правильность ввода!", MessageType.Warning)
            handleLoginInput()
        }
        return input
    }
    fun handleDateInput(): String {
        print("Дата рождения(дд.мм.гггг): ")
        val input = readln().trim()
        handleCancel(input)
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        try {
            val date = LocalDate.parse(input, formatter)
            if(!User.checkDate(date)) {
                throw Exception("Дата находится за пределами диапазона")
            }
        } catch (ex: Exception) {
            printMessage("Проверьте правильность ввода: ${ex.message}", MessageType.Warning)
            handleDateInput()
        }
        return input
    }
    fun regester() {
        println(Console.format("Регистрация нового пользователя", Console.Color.Blue))
        println("Для отмены регистрации и возврата в главное меню напишите слово \"отмена\"")
        try {
            val userName = handleNameInput("Имя")
            val lName = handleNameInput("Фамилия")
            val login = handleLoginInput()
            print("Пароль: ")
            val password = readln()
            handleCancel(password)
            print("Повторите пароль: ")
            val passRep = readln()
            handleCancel(passRep)
            val bDate = handleDateInput()
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
