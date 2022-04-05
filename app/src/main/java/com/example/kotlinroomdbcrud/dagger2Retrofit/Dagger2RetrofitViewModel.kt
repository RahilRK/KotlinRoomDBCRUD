package com.example.kotlinroomdbcrud.dagger2Retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinroomdbcrud.dagger2Retrofit.model.ProductsItem
import kotlinx.coroutines.launch

class Dagger2RetrofitViewModel(private val repository: dagger2RetrofitRepository): ViewModel() {

    val productsLiveData: LiveData<List<ProductsItem>>
    get() = repository.products

    init {
        viewModelScope.launch {
            repository.getProducts()
        }
    }
}