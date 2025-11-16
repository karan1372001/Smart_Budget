package com.example.smart_budget.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNext: () -> Unit) {

    // 👇 Student Comment:
    // This runs only once and waits 2 seconds before moving to the next screen.
    LaunchedEffect(Unit) {
        delay(2000)
        onNext()
    }

    // 👇 Center text on screen
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "SmartBudget",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
