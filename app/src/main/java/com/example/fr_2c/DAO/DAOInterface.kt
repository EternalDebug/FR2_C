package com.example.fr_2c.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.fr_2c.DataClasses.Articles

@Dao
interface ArticlesDao {
    @Query("SELECT * FROM Article ORDER BY id ASC")
    fun getAll(): LiveData<List<Articles>>

    @Insert(entity = Articles::class)
    suspend fun insert(art: Articles)

    @Update
    suspend fun update(art: Articles)

    @Delete
    suspend fun delete(art: Articles)
}