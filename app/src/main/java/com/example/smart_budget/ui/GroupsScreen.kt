package com.example.smart_budget.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.smart_budget.viewmodel.GroupViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupsScreen(
    navController: NavController,
    viewModel: GroupViewModel = viewModel()
) {
    val context = LocalContext.current
    val groupState = viewModel.group.collectAsState()
    val group = groupState.value

    val selected = remember { mutableStateListOf<String>() }
    var showDialog by remember { mutableStateOf(false) }
    var newName by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.load(context)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Add Friends") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Text("+")
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            if (group.members.isEmpty()) {
                Text(
                    text = "Add friends to start splitting expenses",
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                LazyColumn {
                    items(group.members) { member ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = member)

                            Checkbox(
                                checked = selected.contains(member),
                                onCheckedChange = { checked ->
                                    if (checked) {
                                        selected.add(member)
                                    } else {
                                        selected.remove(member)
                                    }
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (selected.isNotEmpty()) {
                Button(
                    onClick = {
                        viewModel.deleteMembers(context, selected.toList())
                        selected.clear()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Delete Selected")
                }
            }

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
            title = { Text("Add Friend") },
            text = {
                OutlinedTextField(
                    value = newName,
                    onValueChange = { newName = it },
                    label = { Text("Friend Name") }
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.addMember(context, newName)
                        newName = ""
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
