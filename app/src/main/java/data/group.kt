package com.example.smart_budget.data

/**
 * Represents a Splitwise-style group.
 */
data class Group(
    val id: String = "",
    val name: String = "",
    val members: List<String> = emptyList(), // user emails
    val totalExpense: Double = 0.0
)
