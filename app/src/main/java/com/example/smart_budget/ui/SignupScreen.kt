package com.example.smart_budget.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smart_budget.ui.util.PasswordValidator

// ---------------------------------------------------------
// CREATE ACCOUNT SCREEN
// Password strength validation happens ONLY here
// Weak password  -> RED + button disabled
// Strong password -> GREEN + button enabled
// ---------------------------------------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(navController: NavController) {

    // -------------------------
    // User input states
    // -------------------------
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    // -------------------------
    // Password validation result
    // -------------------------
    val validation = PasswordValidator.validate(password)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Account") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp)
                .fillMaxWidth()
        ) {

            // -------------------------
            // Email field
            // -------------------------
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // -------------------------
            // Password field
            // -------------------------
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (passwordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                trailingIcon = {
                    Text(
                        text = if (passwordVisible) "Hide" else "Show",
                        modifier = Modifier.clickable {
                            passwordVisible = !passwordVisible
                        }
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // -------------------------
            // Password strength message
            // -------------------------
            Text(
                text = if (password.isEmpty()) {
                    "Password must be at least 8 characters with upper, lower, number & special character"
                } else if (validation.isStrong) {
                    "Strong password"
                } else {
                    "Weak password"
                },
                color = if (validation.isStrong) Color(0xFF2E7D32) else Color.Red
            )

            Spacer(modifier = Modifier.height(24.dp))

            // -------------------------
            // Create account button
            // Enabled ONLY if password is strong
            // -------------------------
            Button(
                onClick = {
                    // Firebase create user will be added later
                    navController.navigate("dashboard")
                },
                enabled = validation.isStrong,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Create Account")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // -------------------------
            // Back to login
            // -------------------------
            Text(
                text = "Already have an account? Login",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
        }
    }
}
