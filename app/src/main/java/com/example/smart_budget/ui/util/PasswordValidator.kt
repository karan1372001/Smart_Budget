package com.example.smart_budget.ui.util

// Simple data class to hold the result of password checking
data class PasswordValidationResult(
    val isValid: Boolean,
    val message: String = ""
)

/**
 * This function checks our password rules.
 * We return the FIRST rule that fails so it is easy to show to the user.
 *
 * Rules:
 * 1. At least 8 characters
 * 2. Has lowercase letter
 * 3. Has uppercase letter
 * 4. Has a digit (0-9)
 * 5. Has a special character (!@#$%^&* etc.)
 */
fun validatePassword(password: String): PasswordValidationResult {

    if (password.length < 8) {
        return PasswordValidationResult(
            isValid = false,
            message = "Password must be at least 8 characters."
        )
    }

    if (!password.any { it.isLowerCase() }) {
        return PasswordValidationResult(
            isValid = false,
            message = "Password must contain a lowercase letter."
        )
    }

    if (!password.any { it.isUpperCase() }) {
        return PasswordValidationResult(
            isValid = false,
            message = "Password must contain an uppercase letter."
        )
    }

    if (!password.any { it.isDigit() }) {
        return PasswordValidationResult(
            isValid = false,
            message = "Password must contain a number."
        )
    }

    val specialChars = "!@#\$%^&*()-_=+[]{};:'\",.<>?/\\|`~"
    if (!password.any { it in specialChars }) {
        return PasswordValidationResult(
            isValid = false,
            message = "Password must contain a special character."
        )
    }

    // If we reach here, all rules passed
    return PasswordValidationResult(isValid = true)
}
