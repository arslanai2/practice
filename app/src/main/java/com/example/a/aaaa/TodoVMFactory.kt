package com.example.a.aaaa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TodoVMFactory(private val repo: TodoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TodoViewModel(repo) as T
    }
}
