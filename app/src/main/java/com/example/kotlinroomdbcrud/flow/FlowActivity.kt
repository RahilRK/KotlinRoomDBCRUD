package com.example.kotlinroomdbcrud.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.kotlinroomdbcrud.R
import com.example.kotlinroomdbcrud.util.Application
import com.example.kotlinroomdbcrud.util.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FlowActivity : AppCompatActivity() {

    val TAG = "FlowActivity"

    lateinit var repository: Repository
    lateinit var viewModel: FlowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)

        init()

/*
        lifecycleScope.launch {
            getData()
                .catch { e->
                    Log.e(TAG, "getDataError: ${Log.getStackTraceString(e)}")
                }
                .collect { i->
                    Log.d(TAG, "getDataCollect: $i")
                }
        }
*/
//        flowOfEg()
//        flowMapEg()
//        flowFilterEg()
//        listToFlowEg()
        observeProductList()
    }

    private fun init() {
        repository = (application as Application).repository
        viewModel = ViewModelProvider(this, FlowViewModelFactory(repository))
            .get(FlowViewModel::class.java)
    }

    //todo flow emit & collect
    fun getData(): Flow<Int> {
        return flow {
            for(i in 1..5) {
                delay(1000L)
                emit(i)
            }
        }.flowOn(Dispatchers.Default)
    }

    //todo flowOf
    fun flowOfEg() {
        val data = flowOf(1,"Raj",3,true,5).flowOn(Dispatchers.IO)

        lifecycleScope.launch {
            data
                .catch { e->
                    Log.e(TAG, "flowOfEgError: ${Log.getStackTraceString(e)}")
                }
                .collect { i->
                    Log.d(TAG, "flowOfEgCollect: $i")
                }
        }
    }

    //todo flow of map/update value before collect
    fun flowMapEg() {
        val data = flowOf(1,2,3,4,5).flowOn(Dispatchers.IO)

        lifecycleScope.launch {

            data.map { i->
                i * i
            }.collect { newi->
                Log.d(TAG, "flowMapEg: $newi")
            }
        }
    }

    //todo filter value before collect
    fun flowFilterEg() {
        val data = flowOf(1,2,3,4,5,6).flowOn(Dispatchers.IO)

        lifecycleScope.launch {

            data.filter { value->
                value % 2 == 0
            }.collect { newi->
                Log.d(TAG, "flowFilterEg: $newi")
            }
        }
    }

    //todo List To Flow
    fun listToFlowEg() {
        val data = listOf("Rahil","Rushan","Nehal","Asif","Sahil").asFlow().flowOn(Dispatchers.IO)

        lifecycleScope.launch {
            data.filter { v->
                v.contains("a")
            }.collect { value->
                Log.d(TAG, "listToFlowEg: $value")
            }
        }
    }

    fun observeProductList() {
        viewModel.list.observe(this) { list ->
            Log.d(TAG, "observeProductList: $list")
        }
    }
}