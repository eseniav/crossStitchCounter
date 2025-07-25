import java.time.LocalDate
import java.time.temporal.ChronoUnit

fun tryParseDate(str: String): LocalDate? {
    return try {
            LocalDate.parse(str)
        }
        catch(e: Exception) {
            null
        }
}
fun crossInDay(cross: Int, date: LocalDate): Int {
    val dayDiff = ChronoUnit.DAYS.between(LocalDate.now(), date) + 1
    return cross / dayDiff.toInt()
}
fun printProjInfo(totalQuantity: Int, finishDate: LocalDate, currentQuantity: Int, crossQuantity: Int) {
    println("Общее количество крестиков: $totalQuantity")
    println("Планируемая дата завершения: $finishDate")
    println("Крестики в день: ${crossInDay(currentQuantity, finishDate)}")
    println("Всего вышито: ${crossQuantity * 100 / totalQuantity}%")
}
fun main(args: Array<String>) {
    print("Введите общее количество крестиков: ")
    var totalQuantity: Int? = null
    while(totalQuantity == null) {
        totalQuantity = readln().toIntOrNull()
        if (totalQuantity == null || totalQuantity <= 0) {
            totalQuantity = null
            print("Введены неверные данные. Введите число: ")
        }
    }
    print("Введите вышитое количество крестиков: ")
    var crossQuantity = readln().toIntOrNull() ?: 0
    crossQuantity = if (crossQuantity < 0 || crossQuantity >= totalQuantity) 0 else crossQuantity
    var currentQuantity = totalQuantity - crossQuantity
    print("Введите планируемую дату завершения работы (ГГГГ-ММ-ДД): ")
    var finishDate: LocalDate? = null
    val today = LocalDate.now()
    while(finishDate == null) {
        finishDate = tryParseDate(readln())
        if (finishDate == null || finishDate.isBefore(today)) {
            finishDate = null
            print("Введены неверные данные. Введите дату: ")
        }
    }
    println("_________________________")
    printProjInfo(totalQuantity, finishDate, currentQuantity, crossQuantity)
}