package com.example.smart_budget.data

import android.content.Context
import android.content.SharedPreferences

/**
 * AuthRepository
 * --------------
 * This class handles user authentication logic.
 *
 * IMPORTANT (ICA explanation):
 * - Instead of Firebase, we use SharedPreferences
 * - This allows user accounts to PERSIST even after app restarts
 * - This avoids login issues during demos and testing
 *
 * This still represents REAL authentication behaviour:
 * - User must sign up first
 * - Login only works for registered users
 */
class AuthRepository(context: Context) {

    // SharedPreferences file name
    private val prefs: SharedPreferences =
        context.getSharedPreferences("SMART_BUDGET_USERS", Context.MODE_PRIVATE)

    /**
     * SIGN UP
     * -------
     * Saves the email and password locally.
     * Returns false if user already exists.
     */
    fun signup(email: String, password: String): Boolean {

        // If user already exists, do not overwrite
        if (prefs.contains(email)) {
            return false
        }

        // Save user credentials
        prefs.edit()
            .putString(email, password)
            .apply()

        return true
    }

    /**
     * LOGIN
     * -----
     * Checks if email exists and password matches.
     */
    fun login(email: String, password: String): Boolean {

        // Get saved password for this email
        val savedPassword = prefs.getString(email, null)

        // Login successful only if password matches
        return savedPassword == password
    }

    /**
     * LOGOUT
     * ------
     * No action needed because we are not storing session tokens.
     * Logout logic is handled in ViewModel.
     */
    fun logout() {
        // Nothing to clear here
    }
}
