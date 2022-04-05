package com.example.kotlinroomdbcrud.navigationComponent.CustomObjectSafeArgs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinroomdbcrud.R

class CustomObjectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_object)
        supportActionBar?.hide()
    }
}