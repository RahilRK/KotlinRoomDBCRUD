package com.example.kotlinroomdbcrud.util

import android.app.Application
import com.example.kotlinroomdbcrud.dagger2.component.AppComponent
import com.example.kotlinroomdbcrud.dagger2.component.DaggerAppComponent
import com.example.kotlinroomdbcrud.dagger2Retrofit.di.component.ApplicationComponent
import com.example.kotlinroomdbcrud.dagger2Retrofit.di.component.DaggerApplicationComponent
import com.example.kotlinroomdbcrud.dataBindingExample.network.ApiClient

class Application: Application() {

    lateinit var appComponent: AppComponent
    lateinit var applicationComponent: ApplicationComponent

    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
        applicationComponent = DaggerApplicationComponent.builder().build()

        init()
        ApiClient.initRetrofit()
    }

    fun init() {
        repository = Repository()
    }
}