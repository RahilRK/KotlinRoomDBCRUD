package com.example.kotlinroomdbcrud.flow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinroomdbcrud.flow.model.ProductsItem
import com.example.kotlinroomdbcrud.util.Repository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FlowViewModel(private val repository: Repository) :ViewModel() {

    var tag = "FlowViewModel"

    private val _list: MutableLiveData<List<ProductsItem>> = MutableLiveData()
    val list: LiveData<List<ProductsItem>>
        get() = _list

    init {
        getProducts()
    }

    private fun getProducts() {

        viewModelScope.launch{
            repository.getProducts()
                .catch { e->
                    Log.d(tag, "getProducts: ${Log.getStackTraceString(e)}")
                }
                .collect { list->
                    _list.value = list
                }
        }
    }
}