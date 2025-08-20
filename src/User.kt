import java.time.LocalDate

class User(): Identifiable {
    var login: String = ""
    var password: String = ""
    var userName: String? = null
    var userLastName: String? = null
    val phoneNumber: String = ""
    val email: String = ""
    val birthDate: LocalDate? = null
    val userID: String = getID()
    val regDate: LocalDate = LocalDate.now()
    companion object {
        val minName = 2
        val maxName = 30
        val minLogin = 6
        val maxLogin = 15
        val minAge = 10L
        val maxAge = 100L
        fun checkName(input: String) = input.isNotBlank() && Validation.checkLength(input.length, minName, maxName)
        fun checkLogin(input: String) = Validation.checkWhiteSpace(input) && Validation.checkLength(input.length, minLogin, maxLogin)
        fun checkDate(input: LocalDate): Boolean {
            val today = LocalDate.now()
            return Validation.checkDateRange(input, today.minusYears(maxAge), today.minusYears(minAge))
        }
    }
}
