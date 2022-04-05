package com.example.kotlinroomdbcrud.dagger2Retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinroomdbcrud.R
import com.example.kotlinroomdbcrud.dagger2Retrofit.di.component.ApplicationComponent
import com.example.kotlinroomdbcrud.dagger2Retrofit.di.component.DaggerApplicationComponent
import com.example.kotlinroomdbcrud.util.Application
import javax.inject.Inject

class Dagger2RetrofitActivity : AppCompatActivity() {

    var tag = this.javaClass.simpleName

    lateinit var dagger2RetrofitViewModel : Dagger2RetrofitViewModel
    @Inject
    lateinit var dagger2RetrofitViewModelFactory: Dagger2RetrofitViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger2_retrofit)

        init()
        dagger2RetrofitViewModel = ViewModelProvider(this, dagger2RetrofitViewModelFactory).get(Dagger2RetrofitViewModel::class.java)
        getProducts()
    }

    fun init() {
        (application as Application).applicationComponent.inject(this)
    }

    fun getProducts() {
        dagger2RetrofitViewModel.productsLiveData.observe(this) { list ->
            Log.e(tag, "getProductsList: ${list.size}",)
        }
    }
}