package com.example.smart_budget.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

/**
 * DashboardScreen
 *
 * This screen is shown after a successful login.
 * It acts as a central hub where the user can access
 * Profile, Groups, and Expense Summary.
 */
@Composable
fun DashboardScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        // App title
        Text(
            text = "Smart Budget",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        // -------- DASHBOARD OPTIONS --------

        DashboardItem(
            title = "User Overview",
            description = "View your account details",
            onClick = {
                navController.navigate("profile")
            }
        )

        DashboardItem(
            title = "Groups",
            description = "Manage shared expense groups",
            onClick = {
                navController.navigate("groups")
            }
        )

        DashboardItem(
            title = "Expense Summary",
            description = "View and manage expenses",
            onClick = {
                navController.navigate("expenses")
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // -------- BACK BUTTON --------
        Button(
            onClick = {
                navController.navigate("home")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Home")
        }
    }
}

/**
 * DashboardItem
 *
 * Reusable card used for each dashboard option.
 * Keeps the UI clean and consistent.
 */
@Composable
private fun DashboardItem(
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Text(text = description)
        }
    }
}
