package com.example.kotlinroomdbcrud.util

import com.example.kotlinroomdbcrud.flow.model.ProductsItem
import com.example.kotlinroomdbcrud.flow.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Repository {

    suspend fun getProducts(): Flow<List<ProductsItem>> {

        return flow {
            val response = RetrofitClient.apiCall.getProducts()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}