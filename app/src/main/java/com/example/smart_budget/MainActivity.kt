package com.example.smart_budget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.*
import com.example.smart_budget.ui.*
import com.example.smart_budget.ui.theme.SmartBudgetTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SmartBudgetTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {

                    composable("login") {
                        LoginScreen(navController)
                    }

                    composable("signup") {
                        SignupScreen(navController)
                    }

                    composable("dashboard") {
                        DashboardScreen(navController)
                    }

                    composable("profile") {
                        ProfileScreen(navController)
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
