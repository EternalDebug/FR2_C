package com.example.fr_2c.DAO

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fr_2c.DataClasses.Articles

@Database(entities = [Articles::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun actionDao(): ArticlesDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDataBase(context: Context): AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null)
                return tempInstance
            kotlin.synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "actions"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}