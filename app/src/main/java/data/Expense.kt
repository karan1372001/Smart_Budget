package com.example.smart_budget.data

data class Expense(
    val id: String = "",
    val title: String = "",
    val amount: Double = 0.0,
    val paidBy: String = "",
    val splitBetween: List<String> = emptyList()
)
