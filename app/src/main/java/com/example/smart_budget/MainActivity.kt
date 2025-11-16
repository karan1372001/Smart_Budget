package com.example.smart_budget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.smart_budget.splash.SplashScreen
import com.example.smart_budget.onboarding.OnboardingScreen
import com.example.smart_budget.login.LoginScreen
import com.example.smart_budget.signup.SignupScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            // 👇 Student Comment: This state controls which screen is visible
            var currentScreen by remember { mutableStateOf("splash") }

            when (currentScreen) {

                // SPLASH SCREEN → waits 2 sec → go to Onboarding
                "splash" -> SplashScreen {
                    currentScreen = "onboarding"
                }

                // ONBOARDING → user clicks Continue → go to Login
                "onboarding" -> OnboardingScreen {
                    currentScreen = "login"
                }

                // LOGIN → user clicks Next → go to Signup
                "login" -> LoginScreen {
                    currentScreen = "signup"
                }

                // SIGNUP → user clicks Back → go to Login
                "signup" -> SignupScreen {
                    currentScreen = "login"
                }
            }
        }
    }
}
