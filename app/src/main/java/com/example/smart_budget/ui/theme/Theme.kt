package com.example.smart_budget.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 👇 Light colour scheme for our app
private val LightColorScheme = lightColorScheme(
    primary = SmartPrimary,
    onPrimary = Color.White,

    secondary = SmartSecondary,
    onSecondary = Color.White,

    tertiary = SmartAccent,

    background = Color(0xFFF7F7F7),
    onBackground = Color(0xFF121212),

    surface = Color.White,
    onSurface = Color(0xFF121212)
)

/**
 * Global app theme wrapper.
 * We call this in MainActivity so every screen shares the same colours & text styles.
 */
@Composable
fun SmartBudgetTheme(
    darkTheme: Boolean = false, // we only use light theme for now
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
