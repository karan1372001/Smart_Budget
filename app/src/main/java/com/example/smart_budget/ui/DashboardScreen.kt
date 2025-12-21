package com.example.smart_budget.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// ✅ REQUIRED for Material3 TopAppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "SmartBudget")
                },
                actions = {
                    // Logout → return to Login screen
                    TextButton(
                        onClick = {
                            navController.navigate("login") {
                                popUpTo("dashboard") { inclusive = true }
                            }
                        }
                    ) {
                        Text("Logout")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // ===== SUMMARY CARD =====
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Overall Summary",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("You owe: £45")
                    Text("You are owed: £30")
                    Text("Net balance: -£15")
                }
            }

            // ===== NAVIGATION BUTTONS =====
            Button(
                onClick = { navController.navigate("groups") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Groups")
            }

            Button(
                onClick = { navController.navigate("expenses") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Expenses")
            }

            Button(
                onClick = { navController.navigate("profile") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Profile")
            }
        }
    }
}
