package com.example.smart_budget.ui.util

/**
 * Academic password validation rules
 * Used ONLY in Signup screen
 */
object PasswordValidator {

    data class Result(
        val isStrong: Boolean,
        val message: String
    )

    fun validate(password: String): Result {

        val hasUpper = password.any { it.isUpperCase() }
        val hasLower = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecial = password.any { "!@#$%^&*".contains(it) }
        val hasLength = password.length >= 8

        val isStrong =
            hasUpper && hasLower && hasDigit && hasSpecial && hasLength

        return if (isStrong) {
            Result(true, "Strong password")
        } else {
            Result(false, "Weak password")
        }
    }
}
