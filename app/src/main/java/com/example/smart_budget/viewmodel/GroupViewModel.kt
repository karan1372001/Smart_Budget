package com.example.smart_budget.viewmodel

import androidx.lifecycle.ViewModel
import com.example.smart_budget.data.Group
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * GroupViewModel
 * --------------
 * Handles group-related data.
 *
 * IMPORTANT FOR ICA:
 * - Groups are pre-defined (sample data)
 * - This demonstrates how groups would work in a real app
 * - No Firebase Firestore is used to keep the app simple
 */
class GroupViewModel : ViewModel() {

    private val _groups = MutableStateFlow<List<Group>>(emptyList())
    val groups: StateFlow<List<Group>> = _groups

    init {
        /**
         * Sample group with your friends
         * This is enough for ICA demonstration
         */
        _groups.value = listOf(
            Group(
                id = "group_flatmates",
                name = "Flatmates",
                members = listOf(
                    "Pershottam",
                    "Rafy",
                    "Ranjit",
                    "Mahesh"
                ),
                totalExpense = 0.0
            )
        )
    }

    fun getGroupById(groupId: String): Group? {
        return _groups.value.find { it.id == groupId }
    }
}
