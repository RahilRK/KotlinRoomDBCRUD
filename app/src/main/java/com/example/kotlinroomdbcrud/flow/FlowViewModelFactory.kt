package com.example.kotlinroomdbcrud.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinroomdbcrud.util.Repository

class FlowViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FlowViewModel(repository) as T
    }
}