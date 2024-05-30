package com.example.fr_2c

import com.example.example.Articles
import com.example.example.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//похоже, я однажды сильно затупил... Но кому нужен мой репозиторий в принципе...?
const val apiToken: String = "";
interface ExternalApiService {
    @GET("top-headlines?country=ru&category=business&apiKey=${apiToken}")
    fun getNews(): Call<Response>

    companion object {

        var retrofitService: ExternalApiService? = null

        //Create the Retrofit service instance using the retrofit.
        fun getInstance(): ExternalApiService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ExternalApiService::class.java)
            }
            return retrofitService!!
        }
    }
}