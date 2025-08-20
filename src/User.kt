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
}
