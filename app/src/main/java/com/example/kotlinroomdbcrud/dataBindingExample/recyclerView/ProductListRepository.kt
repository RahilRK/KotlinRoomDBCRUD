package com.example.kotlinroomdbcrud.dataBindingExample.recyclerView

import com.example.kotlinroomdbcrud.dagger2Retrofit.model.ProductsItem
import com.example.kotlinroomdbcrud.dataBindingExample.model.ProductData
import com.example.kotlinroomdbcrud.dataBindingExample.network.ApiInterface
import com.example.kotlinroomdbcrud.dataBindingExample.network.BaseRepository
import com.example.kotlinroomdbcrud.dataBindingExample.network.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductListRepository(private val apiInterface: ApiInterface): BaseRepository() {

    suspend fun getProductList(): ResponseHandler<ProductData?> {
        return makeAPICall {
            apiInterface.getProducts()
        }
    }
}