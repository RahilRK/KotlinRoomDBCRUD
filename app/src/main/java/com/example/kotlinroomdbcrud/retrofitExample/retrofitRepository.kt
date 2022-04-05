package com.example.kotlinroomdbcrud.retrofitExample

import retrofit2.Call

public class retrofitRepository {

    suspend fun updateProfile(userid:String,name:String,emailid:String): Call<String> {
        return myRetrofitInstance.apiCall.updateProfile(userid,name,emailid)
    }
}