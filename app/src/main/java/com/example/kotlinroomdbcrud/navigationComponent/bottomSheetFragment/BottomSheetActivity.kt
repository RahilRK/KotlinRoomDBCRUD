package com.example.kotlinroomdbcrud.navigationComponent.bottomSheetFragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinroomdbcrud.R

class BottomSheetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)

        supportActionBar?.hide()
    }
}