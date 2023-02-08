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
//        collectUsingOnStart()
//        collectUsingFlowOn()
        collectStateFlow()
//        collectorOne()
//        collectorTwo()

//        flowOfEg()
//        flowMapEg()
//        flowFilterEg()
//        listToFlowEg()
//        observeProductList()
    }

    private fun init() {
        repository = (application as Application).repository
        viewModel = ViewModelProvider(this, FlowViewModelFactory(repository))
            .get(FlowViewModel::class.java)
    }

    /*todo collect using onStart()*/
    private fun collectUsingOnStart() {
        lifecycleScope.launch {
            getData()
                .catch { e ->
                    Log.e(TAG, "getDataError: ${Log.getStackTraceString(e)}")
                }
                .onStart {
                    emit(0)
                    Log.d(TAG, "getDataCollector: onStart")
                }.onCompletion {
                    emit(4)
                    Log.d(TAG, "getDataCollector: onCompletion")
                }
                .onEach { value->
                    Log.d(TAG, "getDataCollector: onEach - $value")
                }
                .collect { i ->
                    Log.d(TAG, "getDataCollector: $i")
                }
        }
    }

    /*todo collect using flowOn()*/
    private fun collectUsingFlowOn() {
        lifecycleScope.launch(Dispatchers.Main) {
            getData()
                .catch { e ->
                    Log.e(TAG, "getDataError: ${Log.getStackTraceString(e)}")
                }
                .flowOn(Dispatchers.IO)
                .map { v->
                    Log.d(TAG, "getDataCollector: v- ${v*v}")
                }
                .flowOn(Dispatchers.Main)
                .collect { i ->
                    Log.d(TAG, "getDataCollector: ${Thread.currentThread().name}")
                }
        }
    }

    private fun collectorOne() {
        lifecycleScope.launch {
            getData()
                .catch { e ->
                    Log.e(TAG, "getDataError: ${Log.getStackTraceString(e)}")
                }
                .collect { i ->
                    Log.d(TAG, "getDataCollectorOne: $i")
                }
        }
    }

    private fun collectorTwo() {
        lifecycleScope.launch {
            getData()
                .catch { e ->
                    Log.e(TAG, "getDataError: ${Log.getStackTraceString(e)}")
                }
                .collect { i ->
                    delay(2500)
                    Log.d(TAG, "getDataCollectorTwo: $i")
                }
        }
    }

    //todo collect Stateflow
    private fun collectStateFlow() {
        lifecycleScope.launch {
            getStateFlowData()
                .catch { e ->
                    Log.e(TAG, "getDataError: ${Log.getStackTraceString(e)}")
                }
                .collect { i ->
                    Log.d(TAG, "getDataStateFlow: $i")
                }
        }
    }

    //todo flow emit & collect
    private fun getData(): Flow<Int> {
        return flow {
            for (i in 1..3) {
                delay(1000L)
                emit(i)
                Log.d(TAG, "getDataEmit: $i")
                Log.d(TAG, "getDataEmit: ${Thread.currentThread().name}")
            }
        }.flowOn(Dispatchers.Default)
    }

    //todo emit Stateflow
    private fun getStateFlowData(): Flow<Int> {
        val mutableSharedFlow =  MutableSharedFlow<Int>()

        lifecycleScope.launch {
            for (i in 1..3) {
                delay(1000L)
                mutableSharedFlow.emit(i)
                Log.d(TAG, "getStateFlowDataEmit: $i")
            }
        }

        return mutableSharedFlow
    }

    //todo flowOf `
    fun flowOfEg() {
        val data = flowOf(1, "Raj", 3, true, 5).flowOn(Dispatchers.IO)

        lifecycleScope.launch {
            data
                .catch { e ->
                    Log.e(TAG, "flowOfEgError: ${Log.getStackTraceString(e)}")
                }
                .collect { i ->
                    Log.d(TAG, "flowOfEgCollect: $i")
                }
        }
    }

    //todo flow of map/update value before collect
    fun flowMapEg() {
        val data = flowOf(1, 2, 3, 4, 5).flowOn(Dispatchers.IO)

        lifecycleScope.launch {

            data.map { i ->
                i * i
            }.collect { newi ->
                Log.d(TAG, "flowMapEg: $newi")
            }
        }
    }

    //todo filter value before collect
    fun flowFilterEg() {
        val data = flowOf(1, 2, 3, 4, 5, 6).flowOn(Dispatchers.IO)

        lifecycleScope.launch {

            data.filter { value ->
                value % 2 == 0
            }.collect { newi ->
                Log.d(TAG, "flowFilterEg: $newi")
            }
        }
    }

    //todo List To Flow
    fun listToFlowEg() {
        val data =
            listOf("Rahil", "Rushan", "Nehal", "Asif", "Sahil").asFlow().flowOn(Dispatchers.IO)

        lifecycleScope.launch {
            data.filter { v ->
                v.contains("a")
            }.collect { value ->
                Log.d(TAG, "listToFlowEg: $value")
            }
        }
    }

    private fun observeProductList() {
        viewModel.list.observe(this) { list ->
            Log.d(TAG, "observeProductList: $list")
        }
    }
}