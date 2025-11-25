package com.example.a.aaaa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel(private val repo: TodoRepository) : ViewModel() {

    val data = repo.getAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun add(title: String) {
        viewModelScope.launch {
            repo.insert(TodoEntity(title = title))
        }
    }
}
