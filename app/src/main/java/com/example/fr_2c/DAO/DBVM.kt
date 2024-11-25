package com.example.fr_2c.DAO

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.example.Articles
import kotlinx.coroutines.launch

class DBViewModel(application: Application): AndroidViewModel(application) {

    val articles: LiveData<List<Articles>>

    private val repository: DAORepository

    init {
        val artDao = AppDatabase.getDataBase(application).actionDao()
        repository = DAORepository(artDao)
        articles = repository.getAll()
    }

    fun insert(art: Articles) = viewModelScope.launch {
        repository.insert(art)
    }

    fun update(art: Articles) = viewModelScope.launch {
        repository.update(art)
    }

    fun delete(art: Articles) = viewModelScope.launch {
        repository.delete(art)
    }
}