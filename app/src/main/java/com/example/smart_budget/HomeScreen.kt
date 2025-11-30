package com.example.smart_budget.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Tabs for the bottom navigation bar.
 * For Sprint 3 all of them just show placeholder content.
 */
enum class HomeTab {
    Dashboard,
    Groups,
    Profile
}

// We opt-in to Material3 experimental APIs so the compiler stops shouting.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onLogout: () -> Unit = {}   // MainActivity passes this; default is empty for preview/tests
) {
    // 👉 Remember which tab is currently selected
    var selectedTab by remember { mutableStateOf(HomeTab.Dashboard) }

    Scaffold(
        // ---------- TOP BAR ----------
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "SmartBudget") },
                actions = {
                    // Simple logout button in the top-right
                    TextButton(onClick = onLogout) {
                        Text(text = "Logout")
                    }
                }
            )
        },

        // ---------- BOTTOM BAR ----------
        bottomBar = {
            NavigationBar {
                // Dashboard tab
                NavigationBarItem(
                    selected = selectedTab == HomeTab.Dashboard,
                    onClick = { selectedTab = HomeTab.Dashboard },
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Dashboard") },
                    label = { Text("Dashboard") }
                )

                // Groups tab (Splitwise-style groups)
                NavigationBarItem(
                    selected = selectedTab == HomeTab.Groups,
                    onClick = { selectedTab = HomeTab.Groups },
                    icon = { Icon(Icons.Filled.Group, contentDescription = "Groups") },
                    label = { Text("Groups") }
                )

                // Profile tab
                NavigationBarItem(
                    selected = selectedTab == HomeTab.Profile,
                    onClick = { selectedTab = HomeTab.Profile },
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
                    label = { Text("Profile") }
                )
            }
        }
    ) { paddingValues ->
        // ---------- MAIN PAGE CONTENT ----------
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter
        ) {
            when (selectedTab) {
                HomeTab.Dashboard -> DashboardTab()
                HomeTab.Groups -> GroupsTab()
                HomeTab.Profile -> ProfileTab()
            }
        }
    }
}

/**
 * DASHBOARD TAB CONTENT
 * – shows total balance and recent transactions (placeholder text for Sprint 3).
 */
@Composable
private fun DashboardTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Total Balance: £0.00 (placeholder)",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Recent Transactions",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "No data yet – this will show your latest expenses in future sprints.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

/**
 * GROUPS TAB CONTENT
 * – placeholder explanation of how groups will look (Splitwise idea).
 */
@Composable
private fun GroupsTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Groups",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Here we will show Splitwise-style groups.\n" +
                    "For Sprint 3 this is just a static design so the marker can see our plan.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

/**
 * PROFILE TAB CONTENT
 * – very simple placeholder for now.
 */
@Composable
private fun ProfileTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Profile",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "In later sprints this will show user details and settings.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}
