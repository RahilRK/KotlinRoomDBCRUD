package com.example.kotlinroomdbcrud.dagger2

import android.util.Log
import com.example.kotlinroomdbcrud.dagger2.myannotations.ActivityScope
import javax.inject.Inject
import javax.inject.Singleton

interface NotifyServiceInterface {
    fun sendMail(to: String, from: String, body: String)
}

@ActivityScope
class EmailService @Inject constructor(): NotifyServiceInterface{
    override fun sendMail(to: String, from: String, body: String) {
        Log.e("EmailService", "Email sent: " )
    }
}

class NotificationService(private val retryCount: Int): NotifyServiceInterface{
    override fun sendMail(to: String, from: String, body: String) {
        Log.e("NotificationService", "Notification sent retryCount: $retryCount" )
    }
}