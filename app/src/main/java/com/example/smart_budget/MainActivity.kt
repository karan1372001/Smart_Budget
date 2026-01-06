package com.example.smart_budget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.*
import com.example.smart_budget.ui.*
import com.example.smart_budget.ui.theme.SmartBudgetTheme
import com.example.smart_budget.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userViewModel = UserViewModel(applicationContext)

        setContent {
            SmartBudgetTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "splash"
                ) {
                    composable("splash") {
                        SplashScreen(navController)
                    }
                    composable("login") {
                        LoginScreen(navController, userViewModel)
                    }
                    composable("signup") {
                        SignupScreen(navController, userViewModel)
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
