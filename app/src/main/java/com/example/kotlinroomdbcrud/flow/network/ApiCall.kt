package com.example.kotlinroomdbcrud.flow.network

import com.example.kotlinroomdbcrud.dagger2Retrofit.model.ProductsItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiCall {

    @GET("products")
    suspend fun getProducts() : List<ProductsItem>
}