package com.example.kotlinroomdbcrud.dagger2.myclasses

import android.util.Log

interface AnalyticsService {
    fun tracking(eventName: String, eventType: String)
}

class MixPanel: AnalyticsService {
    override fun tracking(eventName: String, eventType: String) {
        Log.e("MixPanel", "eventName: $eventName, eventType: $eventType", )
    }
}

class FirebaseAnalytics: AnalyticsService {
    override fun tracking(eventName: String, eventType: String) {
        Log.e("FirebaseAnalytics", "eventName: $eventName, eventType: $eventType", )
    }
}