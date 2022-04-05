package com.example.kotlinroomdbcrud.dagger2Retrofit.di.component

import com.example.kotlinroomdbcrud.dagger2Retrofit.Dagger2RetrofitActivity
import com.example.kotlinroomdbcrud.dagger2Retrofit.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    fun inject(dagger2RetrofitActivity: Dagger2RetrofitActivity)
}