package com.example.kotlinroomdbcrud.dataBindingExample.network

import com.example.kotlinroomdbcrud.dagger2Retrofit.model.ProductsItem
import com.kotlinusermodule.network.model.HttpErrorCode
import okhttp3.Response
import okhttp3.ResponseBody

open class BaseRepository {

    suspend fun <T : Any> makeAPICall(call: suspend () -> retrofit2.Response<T>): ResponseHandler<T?> {
        return try {
            val response = call.invoke()
            return when {
                response.code() == 200 ->
                    ResponseHandler.OnSuccessResponse(response.body())
                else ->
                    ResponseHandler.OnFailed(
                    HttpErrorCode.UNAUTHORIZED.code,
                    response.message(),
                    "Error message code"
                )
            }

        } catch (e: Throwable) {
            ResponseHandler.OnFailed(HttpErrorCode.NO_CONNECTION.code,
                "Error message",
                "Error message code")
        }
    }
}