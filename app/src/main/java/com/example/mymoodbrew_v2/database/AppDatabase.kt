package com.example.mymoodbrew_v2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mymoodbrew_v2.dao.*
import com.example.mymoodbrew_v2.models.*

@Database(
    entities = [
        CoffeeRecipe::class,
        CoffeeVariation::class,
        Ingredient::class,
        VariationIngredient::class,
        WeeklyRecipe::class,
        CoffeeLink::class,
        User::class,
        Favorite::class,
        Recommendation::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun coffeeRecipeDao(): CoffeeRecipeDao
    abstract fun coffeeVariationDao(): CoffeeVariationDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun variationIngredientDao(): VariationIngredientDao
    abstract fun weeklyRecipeDao(): WeeklyRecipeDao
    abstract fun coffeeLinkDao(): CoffeeLinkDao
    abstract fun userDao(): UserDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun recommendationDao(): RecommendationDao
}