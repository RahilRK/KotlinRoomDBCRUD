package com.example.kotlinroomdbcrud.flow.network

import com.example.kotlinroomdbcrud.util.Constants.BASE_URL_FAKE_STORE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private val retrofit by lazy {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL_FAKE_STORE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val apiCall by lazy {
            retrofit.create(ApiCall::class.java)
        }
    }
}