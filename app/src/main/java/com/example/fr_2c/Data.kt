package com.example.fr_2c

import com.example.example.Articles
import com.example.example.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//Теперь здесь нет моего ApiKey! Старый тупо не работает. Ха-ха
interface ExternalApiService {

    @GET("latest")
    fun getNews(
        @Query("country") country: String = "ru",
        @Query("category") category: String = "business",
        @Query("apikey") apiToken: String
    ): Call<Response>

    companion object {

        var retrofitService: ExternalApiService? = null

        //Create the Retrofit service instance using the retrofit.
        fun getInstance(): ExternalApiService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://newsdata.io/api/1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ExternalApiService::class.java)
            }
            return retrofitService!!
        }
    }
}