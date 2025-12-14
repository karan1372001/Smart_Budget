package com.example.smart_budget.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

/**
 * UserViewModel
 * This class handles all user-related logic.
 * UI screens never talk to Firebase directly.
 */
class UserViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    /**
     * Logs the user in using Firebase Authentication.
     * Success and error are returned using callbacks.
     */
    fun doLogin(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (email.isBlank() || password.isBlank()) {
            onError("Email and password must not be empty")
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { exception ->
                onError(exception.message ?: "Login failed")
            }
    }

    /**
     * Returns the currently logged-in user's email.
     */
    fun getUserEmail(): String {
        return auth.currentUser?.email ?: "Unknown user"
    }
}
