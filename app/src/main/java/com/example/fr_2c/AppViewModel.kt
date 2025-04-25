package com.example.fr_2c

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
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
    var curNews:Articles = Articles(0,"Null", "Null");
    var cnSentiment = MutableLiveData<String>();
    var state: String = "db"

    var newsDB = mutableListOf<Articles>()
    var newsAPI = mutableListOf<Articles>()
    var isGNFailure = MutableLiveData<Boolean>();

    fun getNews() {
        val response = repository.GetNews()
        response.enqueue(object : Callback<com.example.example.Response> {
            override fun onResponse(call: Call<com.example.example.Response>, response: Response<com.example.example.Response>) {
                Log.d("Response status:", response.body()?.status!!)
                state = "api"
                isGNFailure.postValue(false)
                newsList.postValue(response.body()?.articles!!.toList())
            }

            override fun onFailure(call: Call<com.example.example.Response>, t: Throwable) {
                isGNFailure.postValue(true)
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
                val title = curNews.title?.replace("//","")
                val data = client.get<String>("http://10.0.2.2:8000/str/" + java.net.URLEncoder.encode(title, "utf-8"))
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