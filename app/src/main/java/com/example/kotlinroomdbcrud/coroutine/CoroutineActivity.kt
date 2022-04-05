package com.example.kotlinroomdbcrud.coroutine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.kotlinroomdbcrud.R
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class CoroutineActivity : AppCompatActivity(R.layout.activity_coroutine) {

    val TAG: String = "CoroutineActivity"

    private val scope = CoroutineScope(CoroutineName("New job"))

    var shouldCount = false
    var counter = 0
    lateinit var timeJob: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        Log.e(TAG, "onCreate thread: ${Thread.currentThread().name}")

//        GlobalScope.launch {
//
//            exampleJob()
//        }

        nextScreenBt.setOnClickListener {

            /*GlobalScope.launch {

                scopeTest()
            }*/

            handleTimer()
        }
    }

    suspend fun getApiResponse() {

        GlobalScope.launch(Dispatchers.IO) {

            val response = apiCall()
            Log.e(TAG, "GlobalScope response: $response")
            Log.e(TAG, "GlobalScope thread: ${Thread.currentThread().name}")

            withContext(Dispatchers.Main) {
                textView.text = response
                Log.e(TAG, "withContext thread: ${Thread.currentThread().name}")
            }
        }
    }

    suspend fun checkAsyncAwait() {

        GlobalScope.launch {

            val measureTime = measureTimeMillis {
                val response =  async{
                    apiCall()
                }
                val response2 = async {
                    apiCallTwo()
                }

                Log.e(TAG, "checkAsyncAwait response: ${response.await()}")
                Log.e(TAG, "checkAsyncAwait response2: ${response2.await()}")
            }

            Log.e(TAG, "checkAsyncAwait measureTime: ${measureTime}")
        }
    }

    suspend fun scopeTest() {

        lifecycleScope.launch {

            while (true) {

                delay(1000)
                Log.e(TAG, "Still running...")
            }
        }

        GlobalScope.launch {
            delay(3000L)
            startActivity(Intent(this@CoroutineActivity,SecondActivity::class.java))
            finish()
        }
    }

    suspend fun apiCall(): String {
        delay(1500L)
        return "Api response"
    }

    suspend fun apiCallTwo(): String {
        delay(1500L)
        return "Api Two response"
    }

    suspend fun runBlockingCreate() {

        runBlocking(Dispatchers.IO) {

            delay(3000)
            Log.e(TAG, "runBlocking thread: ${Thread.currentThread().name}")

//            launch(Dispatchers.Main) {
//                Log.e(TAG, "launch thread: ${Thread.currentThread().name}")
//            }
        }

    }

    suspend fun createJob() {

        val newJob = GlobalScope.launch {

            Log.e(TAG, "newJob started")

            repeat(5) {

                Log.e(TAG, "Job is running: ${it}")
                delay(1000L)
            }
        }

//        newJob.join()//todo wait for job to finish
        delay(2000L)
        newJob.cancel()
//        Log.e(TAG, "newJob finish")
        Log.e(TAG, "newJob cancel")
    }

    suspend fun exampleJob() {

        val job1 = scope.launch {
            while (true) {
                delay(500)
                Log.e(TAG, "Job1 is running...")
//                delay(1000L)
            }
        }
        delay(1000L)
        Log.e(TAG, "Job1 is cancel...")
        job1.cancel()
        job1.join()
        Log.e(TAG, "Job1 is has been canceled...")
    }

    fun timerCounterJob(): Job {
        return lifecycleScope.launchWhenStarted {
            while (true) {
                textView.text = counter.toString()
                delay(1000)
                counter++
            }
        }
    }

    fun handleTimer() {
        shouldCount = !shouldCount
        if(shouldCount) {
            timeJob = timerCounterJob()
            nextScreenBt.text = "Stop"
        }
        else {
            timeJob.cancel()
            nextScreenBt.text = "Resume"
        }
    }
}