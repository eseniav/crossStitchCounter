import java.time.LocalDate

class Validation {
    companion object {
        val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,30}$"
        fun checkLength(value: Int, min: Int, max: Int) = value > min && value < max
        fun checkPassword(password: String) = password.matches(passwordPattern.toRegex())
        fun checkMatch(s1: String, s2: String) = s1 == s2
        fun checkWhiteSpace(str: String) = !str.any { it.isWhitespace() }
        fun checkDateRange(input: LocalDate, min: LocalDate, max: LocalDate) = input.isBefore(max) && input.isAfter(min)
    }
}