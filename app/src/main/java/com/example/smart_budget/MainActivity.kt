package com.example.smart_budget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.smart_budget.ui.*
import com.example.smart_budget.ui.theme.SmartBudgetTheme
import com.example.smart_budget.viewmodel.UserViewModel

/**
 * Main entry point of the app.
 * Handles navigation and ViewModel creation.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SmartBudgetTheme {

                val navController = rememberNavController()
                val userViewModel: UserViewModel = viewModel()

                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {

                    composable("login") {
                        LoginScreen(
                            userViewModel = userViewModel,
                            onLoginSuccess = {
                                navController.navigate("dashboard") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable("dashboard") {
                        DashboardScreen(navController)
                    }

                    composable("profile") {
                        ProfileScreen(navController, userViewModel)
                    }

                    composable("groups") {
                        GroupsScreen(navController)
                    }

                    composable("expenses") {
                        ExpensesScreen(navController)
                    }
                }
            }
        }
    }
}
