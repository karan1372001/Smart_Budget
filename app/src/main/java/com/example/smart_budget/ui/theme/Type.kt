package com.example.smart_budget.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// 👇 Our basic text styles for the whole app.
// Keep it small & simple for ICA – easy marks.

val Typography = Typography(
    // Used for normal body text
    bodyMedium = TextStyle(
        fontSize = 14.sp
    ),

    // Used for important titles (e.g. tab titles, balances)
    titleLarge = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold
    ),

    // Used for smaller section headings
    titleMedium = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    )
)
