import java.time.LocalDate
import java.time.format.DateTimeFormatter

abstract class App(private val greeting: Greeting) {
    val data: Storage = Storage()
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
    fun handleDateInput(): LocalDate {
        print("Дата рождения(дд.мм.гггг): ")
        val input = readln().trim()
        handleCancel(input)
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return try {
            val date = LocalDate.parse(input, formatter)
            if(!User.checkDate(date)) {
                throw Exception("Дата находится за пределами диапазона")
            }
            date
        } catch (ex: Exception) {
            printMessage("Проверьте правильность ввода: ${ex.message}", MessageType.Warning)
            handleDateInput()
        }
    }
    fun handlePassInput(): String {
        print("Пароль(8-30 символов, буквы разного регистра, цифра, спецсимвол): ")
        val input = readln().trim()
        handleCancel(input)
        if(!Validation.checkPassword(input)) {
            printMessage("Проверьте правильность ввода!", MessageType.Warning)
            handlePassInput()
        }
        return input
    }
    fun handleRepPassInput(pass: String): String {
        print("Повторите пароль: ")
        val input = readln().trim()
        handleCancel(input)
        if(!Validation.checkMatch(input, pass)) {
            printMessage("Проверьте правильность ввода!", MessageType.Warning)
            handleRepPassInput(pass)
        }
        return input
    }
    fun handlePhoneInput(): String {
        print("Телефон: ")
        val input = readln().trim()
        handleCancel(input)
        if(!Validation.checkPhone(input)) {
            printMessage("Проверьте правильность ввода!", MessageType.Warning)
            handlePhoneInput()
        }
        return input
    }
    fun handleEmailInput(): String {
        print("Email: ")
        val input = readln().trim()
        handleCancel(input)
        if(!Validation.checkEmail(input)) {
            printMessage("Проверьте правильность ввода!", MessageType.Warning)
            handleEmailInput()
        }
        return input
    }
    fun register() {
        println(Console.format("Регистрация нового пользователя", Console.Color.Blue))
        println("Для отмены регистрации и возврата в главное меню напишите слово \"отмена\"")
        var inputUserName: String? = null
        var lName: String? = null
        var inputLogin: String? = null
        var inputPassword: String? = null
        var passRep: String? = null
        var bDate: LocalDate? = null
        var phone: String? = null
        var inputEmail: String? = null
        try {
            inputUserName = handleNameInput("Имя")
            lName = handleNameInput("Фамилия")
            inputLogin = handleLoginInput()
            inputPassword = handlePassInput()
            passRep = handleRepPassInput(inputPassword)
            bDate = handleDateInput()
            phone = handlePhoneInput()
            inputEmail = handleEmailInput()
        }
        catch (ex: Exception) {
            if(ex.message == "cancel") {
                println(Console.format("\t Отмена регистрации пользователя. Переход в главное меню", Console.Color.Grey) + "\n")
                mainMenu()
            } else {
                throw ex
            }
        }
        println("Зарегистрироваться? 1.Да, 2.Отменить и выйти в главное меню, 3.Начать регистрацию заново, 0. Завершить работу без сохранения")
        when(readln()) {
            "1" -> {
                val user = User().apply {
                    login = inputLogin ?: ""
                    password = inputPassword ?: ""
                    userName = inputUserName ?: ""
                    userLastName = lName ?: ""
                    phoneNumber = phone.toString()
                    email = inputEmail ?: ""
                    birthDate = bDate
                }
                data.users[user.userID] = user
                printMessage("Регистрация прошла успешно!", MessageType.Success)
                println("Добавлен пользователь: ${data.users[user.userID]}")
            }
            "2" -> handleCancel("Отмена")
            "3" -> register()
            "0" -> exit()
            else -> {
                println("Неверный ввод. Выберите один из трех вариантов")
                mainMenu()
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
            "2" -> register()
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
