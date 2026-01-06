package com.example.smart_budget.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.smart_budget.data.AuthRepository

class UserViewModel(context: Context) : ViewModel() {

    private val authRepository = AuthRepository(context)

    fun login(email: String, password: String): Boolean {
        return authRepository.login(email, password)
    }

    fun signup(email: String, password: String): Boolean {
        return authRepository.signup(email, password)
    }

    fun logout() {
        authRepository.logout()
    }
}
