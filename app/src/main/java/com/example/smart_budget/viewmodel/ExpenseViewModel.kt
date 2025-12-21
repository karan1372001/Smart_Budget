package com.example.smart_budget.viewmodel

import androidx.lifecycle.ViewModel
import com.example.smart_budget.data.Expense
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * ExpenseViewModel
 * ----------------
 * Stores expenses for groups.
 *
 * IMPORTANT FOR ICA:
 * - Expenses are sample data
 * - Used to demonstrate Splitwise-style logic
 */
class ExpenseViewModel : ViewModel() {

    private val _expenses = MutableStateFlow<List<Expense>>(emptyList())
    val expenses: StateFlow<List<Expense>> = _expenses

    init {
        /**
         * Sample expenses for Flatmates group
         */
        _expenses.value = listOf(
            Expense(
                id = "e1",
                title = "Groceries",
                amount = 120.0,
                paidBy = "Pershottam",
                groupId = "group_flatmates"
            ),
            Expense(
                id = "e2",
                title = "Electricity Bill",
                amount = 80.0,
                paidBy = "Rafy",
                groupId = "group_flatmates"
            ),
            Expense(
                id = "e3",
                title = "Internet",
                amount = 60.0,
                paidBy = "Ranjit",
                groupId = "group_flatmates"
            )
        )
    }

    fun getExpensesForGroup(groupId: String): List<Expense> {
        return _expenses.value.filter { it.groupId == groupId }
    }
}
