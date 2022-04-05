package com.example.kotlinroomdbcrud.dagger2Retrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class Dagger2RetrofitViewModelFactory @Inject constructor(private val repository: dagger2RetrofitRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Dagger2RetrofitViewModel(repository) as T
    }
}