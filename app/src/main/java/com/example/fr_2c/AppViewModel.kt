package com.example.fr_2c

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.Articles
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class AppViewModel (private val repository: RetroRepository) : ViewModel(){
    val newsList = MutableLiveData<List<Articles>>()
    val errorMessage = MutableLiveData<String>()
    var curNews:Articles = Articles("Null", "Null");
    var cnSentiment = MutableLiveData<String>();

    fun getNews() {
        val response = repository.GetNews()
        response.enqueue(object : Callback<com.example.example.Response> {
            override fun onResponse(call: Call<com.example.example.Response>, response: Response<com.example.example.Response>) {
                Log.d("Response status:", response.body()?.status!!)
                newsList.postValue(response.body()?.articles!!.toList())
            }

            override fun onFailure(call: Call<com.example.example.Response>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    //Primitive code
    @OptIn(DelicateCoroutinesApi::class)
    fun GetSentiment(){
        try {
            val client = HttpClient()
            GlobalScope.launch(Dispatchers.IO) {
                val data = client.get<String>("http://10.0.2.2:8000/str/" + java.net.URLEncoder.encode(curNews.title, "utf-8"))
                Log.i("Simple case ", curNews.title!!)
                cnSentiment.postValue(data);
            }
        }
        catch (e: Exception) {
            print(e)
            //cnSentiment = e.toString();
        }
    }
}