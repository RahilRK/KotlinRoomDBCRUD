package com.example.kotlinroomdbcrud.dagger2Retrofit.model

data class ProductsItem(
    val category: String?="",
    val description: String?="",
    val id: Int?=0,
    val image: String?="",
    val price: Double?=0.0,
    val rating: Rating?=null,
    val title: String?=""
)