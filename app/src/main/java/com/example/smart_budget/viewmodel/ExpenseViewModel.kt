package com.example.smart_budget.viewmodel

import androidx.lifecycle.ViewModel
import com.example.smart_budget.data.Expense
import com.example.smart_budget.data.ExpenseRepository
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

class ExpenseViewModel : ViewModel() {

    val expenses: StateFlow<List<Expense>> =
        ExpenseRepository.expenses

    fun addExpense(
        title: String,
        amount: String,
        paidBy: String,
        splitBetween: List<String>
    ) {
        val expense = Expense(
            id = UUID.randomUUID().toString(),
            title = title,
            amount = amount.toDoubleOrNull() ?: 0.0,
            paidBy = paidBy,
            splitBetween = splitBetween
        )

        ExpenseRepository.addExpense(expense)
    }

    fun deleteExpense(expense: Expense) {
        ExpenseRepository.deleteExpense(expense)
    }
}
