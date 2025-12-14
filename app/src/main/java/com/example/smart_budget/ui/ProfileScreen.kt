package com.example.smart_budget.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smart_budget.viewmodel.UserViewModel

/**
 * ProfileScreen
 *
 * This screen shows basic information about the logged-in user.
 * The email is retrieved from UserViewModel.
 * No Firebase logic is used directly in this UI.
 */
@Composable
fun ProfileScreen(
    navController: NavController,
    userViewModel: UserViewModel
) {
    val userEmail = userViewModel.getUserEmail()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        // Screen title
        Text(
            text = "User Overview",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // User email information
        Text(text = "Logged in as:")
        Text(
            text = userEmail,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Back button
        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Back")
        }
    }
}
