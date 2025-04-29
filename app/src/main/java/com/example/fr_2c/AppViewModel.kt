package com.example.fr_2c

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fr_2c.DataClasses.Articles
import com.example.fr_2c.DataClasses.InnerAPIResponse
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
    var curNews: Articles = Articles(0,"Null", "Null");
    var state: String = "db"

    var newsDB = mutableListOf<Articles>()
    var newsAPI = mutableListOf<Articles>()
    var isGNFailure = MutableLiveData<Boolean>();

    var Answer = MutableLiveData<InnerAPIResponse>();

    fun getNews() {
        val response = repository.GetNews()
        response.enqueue(object : Callback<com.example.fr_2c.DataClasses.Response> {
            override fun onResponse(call: Call<com.example.fr_2c.DataClasses.Response>, response: Response<com.example.fr_2c.DataClasses.Response>) {
                Log.d("Response status:", response.body()?.status!!)
                state = "api"
                isGNFailure.postValue(false)
                newsList.postValue(response.body()?.articles!!.toList())
            }

            override fun onFailure(call: Call<com.example.fr_2c.DataClasses.Response>, t: Throwable) {
                isGNFailure.postValue(true)
                errorMessage.postValue(t.message)
            }
        })
    }

    //Actual code
    fun getAnswer() {

        var title = curNews.title?.replace("//"," ")
        if (title != null) {
            title = title.replace("/"," ")
            title = title.replace("?"," ")
        }

        val response = repository.GetAnswer(title!!)
        response.enqueue(object : Callback<com.example.fr_2c.DataClasses.InnerAPIResponse> {
            override fun onResponse(call: Call<com.example.fr_2c.DataClasses.InnerAPIResponse>, response: Response<com.example.fr_2c.DataClasses.InnerAPIResponse>) {
                Log.d("Inner response status:", response.body()?.status!!)
                Answer.postValue(response.body())
                //state = "api"
                //isGNFailure.postValue(false)
                //newsList.postValue(response.body()?.articles!!.toList())
            }

            override fun onFailure(call: Call<com.example.fr_2c.DataClasses.InnerAPIResponse>, t: Throwable) {
                //isGNFailure.postValue(true)
                errorMessage.postValue(t.message)
            }
        })
    }
}