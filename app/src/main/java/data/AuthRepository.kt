package com.example.smart_budget.data

import android.content.Context
import android.content.SharedPreferences

/**
 * AuthRepository
 * --------------
 * Uses SharedPreferences to persist users.
 * ICA-friendly (offline, demo-safe).
 */
class AuthRepository(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("SMART_BUDGET_USERS", Context.MODE_PRIVATE)

    fun signup(email: String, password: String): Boolean {
        if (prefs.contains(email)) return false
        prefs.edit().putString(email, password).apply()
        return true
    }

    fun login(email: String, password: String): Boolean {
        val savedPassword = prefs.getString(email, null)
        return savedPassword == password
    }

    fun logout() {
        // No session stored
    }
}
