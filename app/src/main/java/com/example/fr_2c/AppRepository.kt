package com.example.fr_2c

import com.example.example.Articles
import retrofit2.Call

class RetroRepository (private val apiService: ExternalApiService) {

    fun GetNews() = apiService.getNews(apiToken = key);

}