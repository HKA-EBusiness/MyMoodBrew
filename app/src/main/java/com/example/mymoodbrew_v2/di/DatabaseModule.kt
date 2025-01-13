package com.example.mymoodbrew_v2.di

import android.content.Context
import androidx.room.Room
import com.example.mymoodbrew_v2.dao.CoffeeVariationDao
import com.example.mymoodbrew_v2.database.AppDatabase
import com.example.mymoodbrew_v2.dao.RecommendationDao
import com.example.mymoodbrew_v2.dao.WeeklyRecipeDao
import com.example.mymoodbrew_v2.models.CoffeeVariation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import android.app.Application
import javax.inject.Singleton

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
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "my_mood_brew_db")
            .fallbackToDestructiveMigration()
            .build()
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

    // Provides the CoffeeVariationDao
    @Provides
    fun provideCoffeeVariationDao(appDatabase: AppDatabase): CoffeeVariationDao {
        return appDatabase.coffeeVariationDao()
    }
}
