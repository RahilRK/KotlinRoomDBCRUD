package com.example.kotlinroomdbcrud.dataBindingExample.network

import com.example.kotlinroomdbcrud.dataBindingExample.model.ProductData
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("products")
    suspend fun getProducts() : Response<ProductData>
}