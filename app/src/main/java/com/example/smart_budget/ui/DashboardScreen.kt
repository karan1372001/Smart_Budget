package com.example.smart_budget.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.smart_budget.viewmodel.ExpenseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavController,
    expenseViewModel: ExpenseViewModel = viewModel()
) {
    val expenses by expenseViewModel.expenses.collectAsState()

    val currentUser = "Karan"

    var totalPaid = 0.0
    var totalOwe = 0.0
    var totalOwed = 0.0

    expenses.forEach { expense ->
        val splitCount = expense.splitBetween.size
        if (splitCount == 0) return@forEach

        val share = expense.amount / splitCount

        if (expense.paidBy == currentUser) {
            totalPaid += expense.amount
            totalOwed += share * (splitCount - 1)
        } else {
            if (expense.splitBetween.contains(currentUser)) {
                totalOwe += share
            }
        }
    }

    val netBalance = totalOwed - totalOwe

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SmartBudget Dashboard") },
                actions = {
                    TextButton(
                        onClick = {
                            navController.navigate("login") {
                                popUpTo("dashboard") { inclusive = true }
                            }
                        }
                    ) {
                        Text("Logout")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Text(
                        text = "Overall Summary",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("You paid: £${"%.2f".format(totalPaid)}")
                    Text("You owe: £${"%.2f".format(totalOwe)}")
                    Text("You are owed: £${"%.2f".format(totalOwed)}")

                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    Text(
                        text = "Net balance: £${"%.2f".format(netBalance)}",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            Button(
                onClick = { navController.navigate("groups") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Groups")
            }

            Button(
                onClick = { navController.navigate("expenses") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Expenses")
            }

            Button(
                onClick = { navController.navigate("profile") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Profile")
            }
        }
    }
}
