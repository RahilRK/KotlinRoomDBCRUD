package com.example.kotlinroomdbcrud.navigationComponent.bottomNavFrag

import androidx.lifecycle.ViewModel
import com.example.kotlinroomdbcrud.retrofitExample.retrofitRepository
import retrofit2.Call

public class HomeFragViewModel(private val repository: retrofitRepository) : ViewModel() {

    suspend fun updateProfile(userid:String,name:String,emailid:String): Call<String> {
        return repository.updateProfile(userid,name,emailid)
    }

}