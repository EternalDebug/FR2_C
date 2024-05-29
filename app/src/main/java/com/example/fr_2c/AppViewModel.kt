package com.example.fr_2c

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.Articles
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppViewModel (private val repository: RetroRepository) : ViewModel(){
    val newsList = MutableLiveData<List<Articles>>()
    val errorMessage = MutableLiveData<String>()


    fun getNews() {
        val response = repository.GetNews()
        response.enqueue(object : Callback<com.example.example.Response> {
            override fun onResponse(call: Call<com.example.example.Response>, response: Response<com.example.example.Response>) {
                newsList.postValue(response.body()?.articles!!.toList())
            }

            override fun onFailure(call: Call<com.example.example.Response>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}