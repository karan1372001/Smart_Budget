package com.example.smart_budget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smart_budget.ui.*
import com.example.smart_budget.ui.theme.SmartBudgetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartBudgetTheme {
                Surface(color = MaterialTheme.colorScheme.background) {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "splash"
                    ) {
                        composable("splash") {
                            SplashScreen(navController)
                        }
                        composable("login") {
                            LoginScreen(navController)
                        }
                        composable("dashboard") {
                            DashboardScreen(navController)
                        }
                        composable("groups") {
                            GroupsScreen(navController)
                        }
                        composable("expenses") {
                            ExpensesScreen(navController)
                        }
                        composable("profile") {
                            ProfileScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
