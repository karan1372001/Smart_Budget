package com.example.smart_budget.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

/**
 * ExpensesScreen
 *
 * PURPOSE:
 * - Displays monthly expenses
 * - Clearly shows who paid and who owes
 * - Uses static data for ICA demo (NO backend dependency)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesScreen(navController: NavController) {

    // -------------------------------
    // NOVEMBER 2025 EXPENSES
    // -------------------------------
    val novemberExpenses = listOf(
        ExpenseItem(
            title = "Groceries",
            amount = 120,
            paidBy = "Karan",
            split = listOf(
                "Pershottam owes £30",
                "Rafay owes £30",
                "Ranjit owes £30"
            )
        ),
        ExpenseItem(
            title = "Electricity Bill",
            amount = 80,
            paidBy = "Rafay",
            split = listOf(
                "Karan owes £20",
                "Pershottam owes £20",
                "Ranjit owes £20"
            )
        ),
        ExpenseItem(
            title = "Internet Bill",
            amount = 65,
            paidBy = "Ranjit",
            split = listOf(
                "Karan owes £16.25",
                "Pershottam owes £16.25",
                "Rafay owes £16.25"
            )
        )
    )

    // -------------------------------
    // DECEMBER 2025 EXPENSES
    // -------------------------------
    val decemberExpenses = listOf(
        ExpenseItem(
            title = "Groceries",
            amount = 150,
            paidBy = "Pershottam",
            split = listOf(
                "Karan owes £37.50",
                "Rafay owes £37.50",
                "Ranjit owes £37.50"
            )
        ),
        ExpenseItem(
            title = "Electricity Bill",
            amount = 95,
            paidBy = "Karan",
            split = listOf(
                "Pershottam owes £23.75",
                "Rafay owes £23.75",
                "Ranjit owes £23.75"
            )
        ),
        ExpenseItem(
            title = "Internet Bill",
            amount = 65,
            paidBy = "Rafay",
            split = listOf(
                "Karan owes £16.25",
                "Pershottam owes £16.25",
                "Ranjit owes £16.25"
            )
        )
    )

    // -------------------------------
    // UI LAYOUT
    // -------------------------------
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Expenses") },
                navigationIcon = {
                    TextButton(onClick = { navController.popBackStack() }) {
                        Text("Back")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MonthSection("November 2025", novemberExpenses)
            MonthSection("December 2025", decemberExpenses)
        }
    }
}

// --------------------------------------------------
// DATA MODEL
// --------------------------------------------------
data class ExpenseItem(
    val title: String,
    val amount: Int,
    val paidBy: String,
    val split: List<String>
)

// --------------------------------------------------
// MONTH SECTION UI
// --------------------------------------------------
@Composable
fun MonthSection(
    month: String,
    expenses: List<ExpenseItem>
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

        Text(
            text = month,
            style = MaterialTheme.typography.titleMedium
        )

        expenses.forEach { expense ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(expense.title, style = MaterialTheme.typography.bodyLarge)
                    Text("Amount: £${expense.amount}")
                    Text("Paid by: ${expense.paidBy}")
                    expense.split.forEach {
                        Text("• $it")
                    }
                }
            }
        }
    }
}
