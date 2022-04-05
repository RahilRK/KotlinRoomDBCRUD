package com.example.kotlinroomdbcrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinroomdbcrud.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var tag = this.javaClass.simpleName
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //todo Add/Call lifeCycle Observer
//        lifecycle.addObserver(MainObserver())

        mainViewModel = ViewModelProvider(this,MainViewModelFactory(5))
            .get(MainViewModel::class.java)

        binding.donebt.setOnClickListener {

            //todo demo viewModel counter
//            mainViewModel.increment()
//            setText()

            //todo update liveData
            mainViewModel.updateLiveData()
        }

        setText()
        observeLiveData()
    }

    fun setText() {
        binding.counter.text = mainViewModel.count.toString()
    }

    fun observeLiveData() {
        mainViewModel.myName.observe(this,{

            binding.liveDate.text = it
        })
    }
}