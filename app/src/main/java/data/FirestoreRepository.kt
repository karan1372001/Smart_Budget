package com.example.smart_budget.data

import android.content.Context

/**
 * FirestoreRepository (ICA Version)
 * --------------------------------
 * IMPORTANT:
 * - We are NOT using Firestore here.
 * - We use SharedPreferences so users persist on the device.
 *
 * WHY THIS FIXES YOUR LOGIN:
 * - Signup and Login use the SAME storage file and SAME keys.
 * - So if you create an account, you can always log in again.
 */
class FirestoreRepository(context: Context) {

    private val prefs = context.getSharedPreferences("smart_budget_auth", Context.MODE_PRIVATE)

    fun signup(email: String, password: String): Boolean {
        if (email.isBlank() || password.isBlank()) return false
        if (prefs.contains(email)) return false // account already exists

        prefs.edit().putString(email.trim(), password).apply()
        return true
    }

    fun login(email: String, password: String): Boolean {
        if (email.isBlank() || password.isBlank()) return false

        val savedPassword = prefs.getString(email.trim(), null)
        return savedPassword != null && savedPassword == password
    }
}
