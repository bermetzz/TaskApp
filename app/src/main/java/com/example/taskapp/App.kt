package com.example.taskapp

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskapp.data.db.AppDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries()
            .build()
    }

    companion object{
        lateinit var db: AppDatabase
    }
}