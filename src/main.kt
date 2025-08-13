import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.UUID

var totalQuantity: Int? = null
var crossQuantity: Int? = null
var currentQuantity: Int = 0
var finishDate: LocalDate? = null
val progressMap = mutableMapOf(LocalDate.now() to 345, LocalDate.of(2025,7, 22) to 380)
val printInfo = "Данные успешно добавлены"

fun tryParseDate(str: String): LocalDate? {
    return try {
            LocalDate.parse(str)
        }
        catch(e: Exception) {
            null
        }
}
fun crossInDay(): Int {
    val dayDiff = ChronoUnit.DAYS.between(LocalDate.now(), finishDate) + 1
    return currentQuantity / dayDiff.toInt()
}
fun printProjInfo() {
    println("Общее количество крестиков: $totalQuantity")
    println("Планируемая дата завершения: $finishDate")
    println("Крестики в день: ${crossInDay()}")
    println("Всего вышито: ${crossQuantity!! * 100 / totalQuantity!!}%")
}
fun printProgressDiary() {
    for((k, v) in progressMap.toSortedMap().entries) {
        println("$k - $v")
    }
}
fun fillProgress() {
    print("Введите дату: ")
    var date: LocalDate = tryParseDate(readln()) ?: LocalDate.now()
    print("Введите количество крестиков: ")
    var crQuan = readln().toIntOrNull() ?: 0
    if(progressMap.containsKey(date)) {
        print("Значения за указанную дату уже существуют. Добавить количество к существующему(1), переписать значение(2) или ничего не делать(любое значение)? ")
        when(readln().toIntOrNull()) {
            1 -> progressMap[date] = progressMap[date]!!.plus(crQuan)
            2 -> progressMap[date] = crQuan
        }
        print("$printInfo")
    } else {
        progressMap[date] = crQuan
        print("$printInfo")
    }
}
fun addProj() {
    print("Введите общее количество крестиков: ")
    while(totalQuantity == null) {
        totalQuantity = readln().toIntOrNull()
        if (totalQuantity == null || totalQuantity!! <= 0) {
            totalQuantity = null
            print("Введены неверные данные. Введите число: ")
        }
    }
    print("Введите вышитое количество крестиков: ")
    crossQuantity = readln().toIntOrNull() ?: 0
    crossQuantity = if (crossQuantity!! < 0 || crossQuantity!! >= totalQuantity!!) 0 else crossQuantity
    currentQuantity = totalQuantity!! - crossQuantity!!
    print("Введите планируемую дату завершения работы (ГГГГ-ММ-ДД): ")
    while(finishDate == null) {
        finishDate = tryParseDate(readln())
        if (finishDate == null || finishDate!!.isBefore(LocalDate.now())) {
            finishDate = null
            print("Введены неверные данные. Введите дату: ")
        }
    }
}
fun fillProjInfo() {
    totalQuantity = 10000
    crossQuantity = 2000
    currentQuantity = totalQuantity!! - crossQuantity!!
    finishDate = LocalDate.of(2025,12, 22)
}
fun fillProgressDiary() {
    progressMap.put(LocalDate.of(2025,7, 23), 888)
    progressMap[LocalDate.of(2025,7, 23)] = 555
    progressMap[LocalDate.of(2025,7, 25)] = 428
}
enum class ProjectStatus(val title: String) {
    future("Будущий"),
    current("Текущий"),
    finished("Завершенный")
}
interface Identifiable {
    fun getID() = UUID.randomUUID().toString()
}
class Project(_user: User): Identifiable {
    var totalQuantity: Int? = null
    var crossQuantity: Int = 0
    var finishDate: LocalDate? = null
    var endDate: LocalDate? = null
    var startDate: LocalDate? = null
    var createDate: LocalDate = LocalDate.now()
    var projName: String = ""
    var setManufacturer: String = ""
    var currentQuantity: Int? = null
        get() = if(totalQuantity == null) null else totalQuantity!! - crossQuantity
    val progressMap: MutableMap<LocalDate, Int> = mutableMapOf()
    var projStatus: ProjectStatus = ProjectStatus.future
    val projID: String = getID()
    val user: User = _user
    var projSpeed: Int = progressMap.values.sum() / progressMap.size

    fun getSpeed(withIdle: Boolean = false): Int {
        return  if(withIdle)
            crossQuantity / (ChronoUnit.DAYS.between(LocalDate.now(),startDate).toInt() + 1)
                else
            progressMap.values.sum() / progressMap.size
    }
}
class User(): Identifiable {
    var login: String = ""
    var password: String = ""
    var userName: String? = null
    var userLastName: String? = null
    val phoneNumber: String = ""
    val userID: String = getID()
    val regDate: LocalDate = LocalDate.now()
}
class Stats(user: User) {
    var currentQuanProj = 0
    var crossQuan = 0
    var finishedQuanProj = 0
    var speed = 0
}

fun main(args: Array<String>) {
    val app = CrossStitchCounter()
    app.greet()
//    fillProjInfo()
//    fillProgressDiary()
//    printSeparateLine()
//    printProjInfo()
//    printSeparateLine()
//    printProgressDiary()
}