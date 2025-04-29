package com.example.fr_2c

import com.example.fr_2c.DataClasses.InnerAPIResponse
import com.example.fr_2c.DataClasses.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface InternalApiService {

    @GET("str/{text}")
    fun getAnswer(
        @Path("text") text: String): Call<InnerAPIResponse>


    companion object {

        var retrofitService: InternalApiService? = null

        //Create the Retrofit service instance using the retrofit.
        fun getInstance(): InternalApiService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(innerAPIURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(InternalApiService::class.java)
            }
            return retrofitService!!
        }
    }
}