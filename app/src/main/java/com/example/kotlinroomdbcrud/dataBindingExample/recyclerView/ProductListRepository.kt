package com.example.kotlinroomdbcrud.dataBindingExample.recyclerView

import com.example.kotlinroomdbcrud.dataBindingExample.model.ProductData
import com.example.kotlinroomdbcrud.dataBindingExample.network.ApiInterface
import com.example.kotlinroomdbcrud.util.BaseRepository
import com.example.kotlinroomdbcrud.dataBindingExample.network.ResponseHandler

class ProductListRepository(private val apiInterface: ApiInterface): BaseRepository() {

    suspend fun getProductList(): ResponseHandler<ProductData?> {
        return makeAPICall {
            apiInterface.getProducts()
        }
    }
}