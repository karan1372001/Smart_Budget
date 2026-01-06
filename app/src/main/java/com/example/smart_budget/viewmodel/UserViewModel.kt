package com.example.smart_budget.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.smart_budget.data.AuthRepository

/**
 * UserViewModel
 *
 * PURPOSE:
 * - Handles user login, signup, and logout
 * - Uses AuthRepository for REAL credential checking
 * - Prevents login with wrong password
 */
class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val authRepository = AuthRepository(application)

    /**
     * LOGIN
     * Returns true only if email exists AND password matches
     */
    fun login(email: String, password: String): Boolean {
        return authRepository.login(email.trim(), password)
    }

    /**
     * SIGNUP
     * Creates a new user if email does not already exist
     */
    fun signup(email: String, password: String): Boolean {
        return authRepository.signup(email.trim(), password)
    }

    /**
     * LOGOUT
     */
    fun logout() {
        authRepository.logout()
    }
}
