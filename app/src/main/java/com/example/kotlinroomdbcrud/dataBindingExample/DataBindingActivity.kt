package com.example.kotlinroomdbcrud.dataBindingExample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinroomdbcrud.R
import com.example.kotlinroomdbcrud.databinding.ActivityDataBindingBinding

class DataBindingActivity : AppCompatActivity() {

    lateinit var binding : ActivityDataBindingBinding
    lateinit var dataBindingViewModel: DataBindingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_data_binding)
        dataBindingViewModel = ViewModelProvider(this).get(DataBindingViewModel::class.java)

        binding.viewModel = dataBindingViewModel

        dataBindingViewModel.model.observe(this) { model ->
            binding.name.text = model.name
            binding.age.text = model.age
        }

    }
}