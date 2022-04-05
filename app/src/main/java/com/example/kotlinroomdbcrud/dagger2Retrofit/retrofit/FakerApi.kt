package com.example.kotlinroomdbcrud.dagger2Retrofit.retrofit

import com.example.kotlinroomdbcrud.dagger2Retrofit.model.ProductsItem
import retrofit2.Response
import retrofit2.http.GET

interface FakerApi {

    @GET("products")
    suspend fun getProducts() : Response<List<ProductsItem>>
}