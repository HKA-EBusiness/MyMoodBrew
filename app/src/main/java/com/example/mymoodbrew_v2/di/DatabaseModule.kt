package com.example.mymoodbrew_v2.di

import android.content.Context
import androidx.room.Room
import com.example.mymoodbrew_v2.database.AppDatabase
import com.example.mymoodbrew_v2.dao.RecommendationDao
import com.example.mymoodbrew_v2.dao.WeeklyRecipeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    // Provide the application context
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    // Provides the AppDatabase instance
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    // Provides the RecommendationDao
    @Provides
    fun provideRecommendationDao(appDatabase: AppDatabase): RecommendationDao {
        return appDatabase.recommendationDao()
    }

    // Provides the WeeklyRecipeDao
    @Provides
    fun provideWeeklyRecipeDao(appDatabase: AppDatabase): WeeklyRecipeDao {
        return appDatabase.weeklyRecipeDao()
    }
}
