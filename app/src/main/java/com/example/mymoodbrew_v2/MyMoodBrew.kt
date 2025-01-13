package com.example.mymoodbrew_v2

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.mymoodbrew_v2.database.AppDatabase
import com.example.mymoodbrew_v2.database.DataSeeder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltAndroidApp
class MyMoodBrew : Application() {

    @Inject
    lateinit var appDatabase: AppDatabase

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Seed the database using the injected database instance
                DataSeeder.seedDatabase(appDatabase)

                Log.d("MyMoodBrew", "Database initialized successfully")
            } catch (e: Exception) {
                Log.e("MyMoodBrew", "Database initialization failed", e)
            }
        }
    }
}