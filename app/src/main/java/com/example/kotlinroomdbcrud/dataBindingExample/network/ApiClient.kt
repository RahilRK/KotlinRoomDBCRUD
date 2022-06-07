package com.example.kotlinroomdbcrud.dataBindingExample.network

import com.example.kotlinroomdbcrud.util.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        var retrofits: Retrofit? = null
        var myapiInterface: ApiInterface? = null

        /**
         * This is the generic method which will create retrofit object as singleton.
         */
        fun initRetrofit() {
            if (retrofits == null) {
                retrofits = getRetrofit()
                myapiInterface = retrofits?.create(ApiInterface::class.java)!!
            }
        }

        /**
         * Generate Retrofit Client
         */
        private fun getRetrofit(): Retrofit {
            /* val gson1 = GsonBuilder()
                 .registerTypeAdapter(ResponseWrapper::class.java, MyResponseDeserializer())
                 .create()*/

            val builder = Retrofit.Builder()
            builder.baseUrl(Constants.BASE_URL_FAKE_STORE)
            builder.addConverterFactory(GsonConverterFactory.create())
            builder.addCallAdapterFactory(CoroutineCallAdapterFactory())
//            builder.client(getOkHttpClient())
            return builder.build()
        }

        /**
         * Return API interface
         *
         */
        fun getApiInterface(): ApiInterface {
            if (myapiInterface != null) {
                return myapiInterface!!
            }
            myapiInterface = retrofits?.create(ApiInterface::class.java)!!
            return myapiInterface as ApiInterface
        }
    }
}