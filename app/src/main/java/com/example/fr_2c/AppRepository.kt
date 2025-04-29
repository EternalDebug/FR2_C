package com.example.fr_2c

class RetroRepository (private val apiService: ExternalApiService,
                       private  val innerApi: InternalApiService) {

    fun GetNews() = apiService.getNews(apiToken = key);

    fun GetAnswer(text: String) = innerApi.getAnswer(text)

}