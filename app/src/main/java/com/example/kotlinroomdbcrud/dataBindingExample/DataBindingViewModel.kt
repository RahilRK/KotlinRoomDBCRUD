package com.example.kotlinroomdbcrud.dataBindingExample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinroomdbcrud.dagger2Retrofit.model.ProductsItem

class DataBindingViewModel:ViewModel() {

    val _model: MutableLiveData<Person> = MutableLiveData()
    val model: LiveData<Person>
    get() = _model

    init {
        val model = Person("Rahil","28")
        _model.value = model
    }

    fun updateData() {
        val model = Person("Rushan","22")
        _model.value = model
    }
}