package com.example.kotlinroomdbcrud

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(counter: Int) : ViewModel() {

//    var count : Int = 0;
    var count : Int = counter;
    var myName = MutableLiveData<String>("My name is Rahil")

    fun increment() {

        count++
    }

    fun updateLiveData() {
        myName.value = "My name is Rahil RK"
    }
}