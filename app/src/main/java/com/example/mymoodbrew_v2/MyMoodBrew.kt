package com.example.mymoodbrew_v2

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.mymoodbrew_v2.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyMoodBrew : Application() {

    companion object {
        lateinit var database: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        try {
            database = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).build()
            Log.d("MyMoodBrew", "Database initialized successfully")
        } catch (e: Exception) {
            Log.e("MyMoodBrew", "Database initialization failed", e)
        }
    }
}