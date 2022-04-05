package com.example.kotlinroomdbcrud.dagger2Retrofit.di.module

import com.example.kotlinroomdbcrud.dagger2Retrofit.retrofit.FakerApi
import com.example.kotlinroomdbcrud.util.Constants.BASE_URL_FAKE_STORE
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_FAKE_STORE).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideFakeApi(retrofit: Retrofit): FakerApi {
        return retrofit.create(FakerApi::class.java)
    }
}