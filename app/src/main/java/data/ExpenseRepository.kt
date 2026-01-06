package com.example.smart_budget.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object ExpenseRepository {

    private val _expenses = MutableStateFlow<List<Expense>>(emptyList())
    val expenses: StateFlow<List<Expense>> = _expenses

    fun addExpense(expense: Expense) {
        _expenses.value = _expenses.value + expense
    }

    fun deleteExpense(expense: Expense) {
        _expenses.value = _expenses.value - expense
    }
}
