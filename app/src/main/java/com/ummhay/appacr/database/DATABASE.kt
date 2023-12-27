package com.ummhay.appacr.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Akrilik::class, Pesanan::class], version = 2)
abstract class DATABASE : RoomDatabase(){

    abstract fun dao() : DAO

    companion object{

        @Volatile
        private var instance : DATABASE?=null
        @Synchronized

        fun getInstance(context: Context) : DATABASE{

            if (instance==null){
                instance= Room.databaseBuilder(context, DATABASE::class.java, "Database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}