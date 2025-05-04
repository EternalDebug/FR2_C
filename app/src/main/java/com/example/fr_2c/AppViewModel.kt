package com.example.fr_2c

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fr_2c.DataClasses.Articles
import com.example.fr_2c.DataClasses.InnerAPIResponse
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
    var curAnswer = InnerAPIResponse()

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
        curAnswer = InnerAPIResponse() // обнуляем переменную
        var title = killSpecSymbols(curNews.title)
        Log.d("TITLE", title)
        var desc = killSpecSymbols(curNews.description)
        Log.d("DESC", desc)
        if (desc != "непонятка")
            title = "$title $desc" //учет тела новости, если оно есть

        val response = repository.GetAnswer(title)
        response.enqueue(object : Callback<com.example.fr_2c.DataClasses.InnerAPIResponse> {
            override fun onResponse(call: Call<com.example.fr_2c.DataClasses.InnerAPIResponse>, response: Response<com.example.fr_2c.DataClasses.InnerAPIResponse>) {
                Log.d("Inner response status:", response.body()?.status!!)
                Answer.postValue(response.body())

            }

            override fun onFailure(call: Call<com.example.fr_2c.DataClasses.InnerAPIResponse>, t: Throwable) {
                Answer.postValue(InnerAPIResponse("Fail"))
                errorMessage.postValue(t.message)
            }
        })
    }

    fun killSpecSymbols(txt: String?): String {
        var res = "непонятка"
        if (txt != null && txt.isNotEmpty()) {
            res = txt
                .replace("//", " ")
                .replace("/", " ")
                .replace("?", " ")
                .replace(":", " ")
                .replace(";", " ")
                .replace("@", " ")
                .replace("&", " ")
                .replace("=", " ")
                .replace("+", " ")
                .replace("$", " ")
                .replace(",", " ")
                .replace("#", " ")
        }
        return res
    }
}