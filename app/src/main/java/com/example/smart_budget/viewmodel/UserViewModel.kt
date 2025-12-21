package com.example.smart_budget.viewmodel

import androidx.lifecycle.ViewModel

/**
 * UserViewModel
 *
 * PURPOSE:
 * - Handles user login, signup, and profile data
 * - Firebase is SIMULATED for ICA stability
 * - MVVM compliant
 * - Easy to explain in presentation
 */
class UserViewModel : ViewModel() {

    // -----------------------------
    // AUTHENTICATION (SIMULATED)
    // -----------------------------

    fun login(email: String, password: String): Boolean {
        // ICA-level validation
        return email.isNotBlank() && password.isNotBlank()
    }

    fun signup(email: String, password: String): Boolean {
        return email.isNotBlank() && password.isNotBlank()
    }

    fun logout() {
        // No backend needed for ICA
    }

    // -----------------------------
    // PROFILE DATA (STATIC FOR DEMO)
    // -----------------------------

    fun getUserEmail(): String {
        return "desaikaran584@gmail.com"
    }

    fun getUserName(): String {
        return "Karan Desai"
    }

    fun getUserPhone(): String {
        return "07553319891"
    }

    fun saveProfile(name: String, phone: String): Boolean {
        return name.isNotBlank() && phone.isNotBlank()
    }
}
