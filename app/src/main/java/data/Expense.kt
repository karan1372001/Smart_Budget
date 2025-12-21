package com.example.smart_budget.data

/**
 * Expense data model
 */
data class Expense(
    val id: String = "",
    val title: String = "",
    val amount: Double = 0.0,
    val paidBy: String = "",
    val groupId: String = ""
)
