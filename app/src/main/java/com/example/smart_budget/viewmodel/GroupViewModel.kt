package com.example.smart_budget.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.smart_budget.data.Group
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GroupViewModel : ViewModel() {

    private val _group = MutableStateFlow(Group())
    val group: StateFlow<Group> = _group

    fun load(context: Context) {
        val prefs = context.getSharedPreferences("group_store", Context.MODE_PRIVATE)
        val members = prefs.getStringSet("members", emptySet())?.toList() ?: emptyList()

        _group.value = _group.value.copy(members = members)
    }

    fun addMember(context: Context, name: String) {
        if (name.isBlank()) return

        val updatedMembers = _group.value.members + name
        _group.value = _group.value.copy(members = updatedMembers)
        save(context)
    }

    fun deleteMembers(context: Context, names: List<String>) {
        val updatedMembers = _group.value.members.filterNot { it in names }
        _group.value = _group.value.copy(members = updatedMembers)
        save(context)
    }

    private fun save(context: Context) {
        val prefs = context.getSharedPreferences("group_store", Context.MODE_PRIVATE)
        prefs.edit()
            .putStringSet("members", _group.value.members.toSet())
            .apply()
    }
}
