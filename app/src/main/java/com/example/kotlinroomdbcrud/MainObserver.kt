package com.example.kotlinroomdbcrud

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MainObserver: LifecycleObserver {

    var tag = this.javaClass.simpleName

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun ON_CREATE() {

        Log.e(tag,"ON_CREATE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun ON_ANY() {

        Log.e(tag,"ON_ANY")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun ON_START() {

        Log.e(tag,"ON_START")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun ON_RESUME() {

        Log.e(tag,"ON_RESUME")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun ON_DESTROY() {

        Log.e(tag,"ON_DESTROY")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun ON_PAUSE() {

        Log.e(tag,"ON_PAUSE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun ON_STOP() {

        Log.e(tag,"ON_STOP")
    }
}