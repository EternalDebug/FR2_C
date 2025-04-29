package com.example.fr_2c.DAO

import androidx.lifecycle.LiveData
import com.example.fr_2c.DataClasses.Articles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class  DAORepository(private val articlesDao: ArticlesDao) {

    fun getAll(): LiveData<List<Articles>> {
        return articlesDao.getAll()
    }
    suspend fun insert(act: Articles) {
        withContext(Dispatchers.IO) {
            articlesDao.insert(act)
        }
    }

    suspend fun update(act: Articles) {
        withContext(Dispatchers.IO) {
            articlesDao.update(act)
        }
    }
    suspend fun delete(act: Articles) {
        withContext(Dispatchers.IO) {
            articlesDao.delete(act)
        }
    }


}