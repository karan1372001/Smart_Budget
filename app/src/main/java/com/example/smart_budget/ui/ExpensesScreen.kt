package com.example.smart_budget.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.smart_budget.viewmodel.ExpenseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesScreen(
    navController: NavController,
    viewModel: ExpenseViewModel = viewModel()
) {
    val expenses by viewModel.expenses.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var paidBy by remember { mutableStateOf("Karan") }

    val flatmates = listOf("Karan", "Ranjit", "Pavan", "Rafay")
    val selected = remember { mutableStateListOf<String>() }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Expenses") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                showDialog = true
                selected.clear()
                selected.add(paidBy)
            }) {
                Text("+")
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            if (expenses.isEmpty()) {
                Text("Add expenses to start tracking spending")
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(expenses) { expense ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(expense.title, style = MaterialTheme.typography.titleMedium)
                                    Text("£${expense.amount}")
                                    Text("Paid by: ${expense.paidBy}")
                                }
                                IconButton(onClick = {
                                    viewModel.deleteExpense(expense)
                                }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back")
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Add Expense") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Expense Title") }
                    )

                    OutlinedTextField(
                        value = amount,
                        onValueChange = { amount = it },
                        label = { Text("Amount (£)") }
                    )

                    Text("Paid by", style = MaterialTheme.typography.titleSmall)

                    flatmates.forEach { name ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = paidBy == name,
                                onClick = {
                                    paidBy = name
                                    selected.clear()
                                    selected.add(name)
                                }
                            )
                            Text(name)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Split between", style = MaterialTheme.typography.titleSmall)

                    flatmates.forEach { name ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = selected.contains(name),
                                onCheckedChange = { checked ->
                                    if (checked) selected.add(name)
                                    else if (name != paidBy) selected.remove(name)
                                },
                                enabled = name != paidBy
                            )
                            Text(name)
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val finalSplitBetween =
                            if (paidBy !in selected)
                                (selected + paidBy).toList()
                            else
                                selected.toList()

                        viewModel.addExpense(
                            title = title,
                            amount = amount,
                            paidBy = paidBy,
                            splitBetween = finalSplitBetween
                        )

                        title = ""
                        amount = ""
                        selected.clear()
                        showDialog = false
                    }
                ) {
                    Text("Add")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}
