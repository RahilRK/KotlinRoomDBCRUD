package com.example.kotlinroomdbcrud.navigationComponent.bottomNavFrag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinroomdbcrud.retrofitExample.retrofitRepository

public class HomeFragViewModelFact(private val repository: retrofitRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeFragViewModel(repository) as T
    }
}