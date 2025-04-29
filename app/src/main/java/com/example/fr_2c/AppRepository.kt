package com.example.fr_2c

class RetroRepository (private val apiService: ExternalApiService) {

    fun GetNews() = apiService.getNews(apiToken = key);

}