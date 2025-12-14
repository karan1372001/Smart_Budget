package com.example.smart_budget.util

// Very simple password checker to keep the code readable.
object PasswordValidator {

    data class Result(
        val isValid: Boolean,
        val message: String? = null
    )

    fun validate(password: String): Result {
        if (password.length < 8) {
            return Result(false, "Password must be at least 8 characters.")
        }
        if (!password.any { it.isUpperCase() }) {
            return Result(false, "Password must contain an uppercase letter.")
        }
        if (!password.any { it.isLowerCase() }) {
            return Result(false, "Password must contain a lowercase letter.")
        }
        if (!password.any { it.isDigit() }) {
            return Result(false, "Password must contain a number.")
        }
        if (!password.any { "!@#$%^&*()-_=+[]{};:'\",.<>/?".contains(it) }) {
            return Result(false, "Password must contain a special character.")
        }
        return Result(true, null)
    }
}
