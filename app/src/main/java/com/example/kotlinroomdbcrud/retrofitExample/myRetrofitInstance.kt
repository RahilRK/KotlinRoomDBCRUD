package com.example.kotlinroomdbcrud.retrofitExample

import com.example.kotlinroomdbcrud.util.Constants.BASE_URL_ZERO_CREDIT
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object myRetrofitInstance {

    private val retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL_ZERO_CREDIT)
            .addConverterFactory(GsonConverterFactory.create())
//            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val apiCall : myApiRequest by lazy {
        retrofit.create(myApiRequest::class.java)
    }
}