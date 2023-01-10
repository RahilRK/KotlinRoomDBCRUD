package com.example.kotlinroomdbcrud.util

import android.app.Application
import com.example.kotlinroomdbcrud.dataBindingExample.network.ApiClient

class Application: Application() {

    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()

        init()
        ApiClient.initRetrofit()
    }

    fun init() {
        repository = Repository()
    }
}