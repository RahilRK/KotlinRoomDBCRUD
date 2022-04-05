package com.example.kotlinroomdbcrud.dagger2Retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinroomdbcrud.dagger2Retrofit.model.ProductsItem
import com.example.kotlinroomdbcrud.dagger2Retrofit.retrofit.FakerApi
import javax.inject.Inject

class dagger2RetrofitRepository @Inject constructor(private val fakerApi: FakerApi) {

    val TAG = "d2RetrofitRepository"

    private val _products = MutableLiveData<List<ProductsItem>>()
    val products: LiveData<List<ProductsItem>>
    get() = _products

    suspend fun getProducts() {
        val result = fakerApi.getProducts()
        if(result.isSuccessful && result.body() != null) {
            _products.postValue(result.body())
        }
        else {
            Log.e(TAG, "getProducts: ${result.errorBody().toString()}")
        }
    }
}