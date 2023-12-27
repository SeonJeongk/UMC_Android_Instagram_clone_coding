package com.example.instagram.data.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Like::class], version = 1)
abstract class InstaDatabase: RoomDatabase() {
    abstract fun likeDao(): LikeDao

    companion object{
        private var instance: InstaDatabase? = null

        @Synchronized
        fun getInstance(context: Context): InstaDatabase?{
            if (instance == null){
                synchronized(InstaDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        InstaDatabase::class.java,
                        "insta-database"
                    ).allowMainThreadQueries().build()
                }
            }
            return instance
        }
    }
}