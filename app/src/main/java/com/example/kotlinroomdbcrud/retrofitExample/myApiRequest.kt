package com.example.kotlinroomdbcrud.retrofitExample

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface myApiRequest {

    @FormUrlEncoded
    @POST("updateProfile")
    suspend fun updateProfile(
        @Field("userid") userid: String,
        @Field("name") name: String,
        @Field("emailid") emailid: String
    ) : Call<String>
}