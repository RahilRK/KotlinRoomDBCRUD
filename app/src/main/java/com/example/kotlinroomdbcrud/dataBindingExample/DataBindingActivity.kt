package com.example.kotlinroomdbcrud.dataBindingExample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.kotlinroomdbcrud.R
import com.example.kotlinroomdbcrud.databinding.ActivityDataBindingBinding

class DataBindingActivity : AppCompatActivity() {

    lateinit var binding : ActivityDataBindingBinding
    lateinit var dataBindingViewModel: DataBindingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_data_binding)

        binding.lifecycleOwner = this

        dataBindingViewModel = ViewModelProvider(this).get(DataBindingViewModel::class.java)
        binding.viewModel = dataBindingViewModel
    }
}

@BindingAdapter("setImageUrl")
fun ImageView.setImageUrl(url: String) {

    Glide.with(this.context).load(url).into(this)
}