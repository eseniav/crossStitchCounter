import java.time.LocalDate

data class User(
    var login: String = "",
    var password: String = "",
    var userName: String? = null,
    var userLastName: String? = null,
    var phoneNumber: String = "",
    var email: String = "",
    var birthDate: LocalDate? = null,
    val regDate: LocalDate = LocalDate.now()
): Identifiable {
    val userID: String = getID()

     fun print() {
         println(this)
     }

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
