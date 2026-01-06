package com.example.smart_budget.data

data class Group(
    val id: String = "flatmates",
    val name: String = "Flatmates",
    val members: List<String> = emptyList(),
    val totalExpense: Double = 0.0
)
