package com.example.kotlinroomdbcrud.dagger2

import android.util.Log
import com.example.kotlinroomdbcrud.dagger2.myannotations.ActivityScope
import com.example.kotlinroomdbcrud.dagger2.myclasses.AnalyticsService
import javax.inject.Inject

interface UserRepositoryInterface {
    fun saveUser(email: String, password: String)
}

@ActivityScope
class RoomRepository @Inject constructor(val analyticsService: AnalyticsService): UserRepositoryInterface{
    override fun saveUser(email: String, password: String) {
        Log.e("UserRepository", "User saved to Room DB: ", )
        analyticsService.tracking("Tracking: Save user to ROOM DB","CREATE")
    }
}

class FirebaseRepository(val analyticsService: AnalyticsService): UserRepositoryInterface{
    override fun saveUser(email: String, password: String) {
        Log.e("UserRepository", "User saved to Firebase DB: ", )
        analyticsService.tracking("Tracking: Save user to Firebase DB","CREATE")
    }
}