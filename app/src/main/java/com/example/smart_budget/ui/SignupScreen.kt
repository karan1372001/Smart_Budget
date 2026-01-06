package com.example.smart_budget.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smart_budget.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    navController: NavController,
    userViewModel: UserViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    val passwordStrength = when {
        password.length >= 8 -> "Strong password"
        password.isNotEmpty() -> "Weak password"
        else -> ""
    }

    val strengthColor = when (passwordStrength) {
        "Strong password" -> MaterialTheme.colorScheme.primary
        "Weak password" -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.onSurface
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Create Account") }) }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation =
                    if (passwordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector =
                                if (passwordVisible) Icons.Filled.Visibility
                                else Icons.Filled.VisibilityOff,
                            contentDescription = null
                        )
                    }
                }
            )

            if (passwordStrength.isNotEmpty()) {
                Text(
                    text = passwordStrength,
                    color = strengthColor,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            error?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }

            Button(
                onClick = {
                    if (password.length < 6) {
                        error = "Password must be at least 6 characters"
                        return@Button
                    }

                    if (userViewModel.signup(email, password)) {
                        navController.popBackStack()
                    } else {
                        error = "User already exists"
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Create Account")
            }

            Text(
                text = "Already have an account? Log in",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.popBackStack() },
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
