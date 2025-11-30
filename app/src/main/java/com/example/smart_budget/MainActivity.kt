package com.example.smart_budget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.smart_budget.ui.*
import com.example.smart_budget.ui.theme.SmartBudgetTheme

// Enum to control which screen is visible
enum class AppScreen {
    SPLASH,
    ONBOARDING,
    LOGIN,
    SIGNUP,
    HOME
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SmartBudgetTheme {
                // Single source of truth for navigation
                var currentScreen by remember { mutableStateOf(AppScreen.SPLASH) }

                when (currentScreen) {
                    AppScreen.SPLASH -> SplashScreen(
                        onTimeout = { currentScreen = AppScreen.ONBOARDING }
                    )

                    AppScreen.ONBOARDING -> OnboardingScreen(
                        onGetStarted = { currentScreen = AppScreen.LOGIN }
                    )

                    AppScreen.LOGIN -> LoginScreen(
                        onLoginSuccess = { currentScreen = AppScreen.HOME },
                        onNavigateToSignup = { currentScreen = AppScreen.SIGNUP }
                    )

                    AppScreen.SIGNUP -> SignupScreen(
                        onNavigateToLogin = { currentScreen = AppScreen.LOGIN },
                        onSignupSuccess = { currentScreen = AppScreen.HOME }
                    )

                    AppScreen.HOME -> HomeScreen(
                        onLogout = { currentScreen = AppScreen.LOGIN }
                    )
                }
            }
        }
    }
}
