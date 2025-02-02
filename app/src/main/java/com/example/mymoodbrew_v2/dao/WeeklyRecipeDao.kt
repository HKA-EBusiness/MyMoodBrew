package com.example.mymoodbrew_v2.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mymoodbrew_v2.models.CoffeeRecipe
import com.example.mymoodbrew_v2.models.WeeklyRecipe

@Dao
interface WeeklyRecipeDao {
    @Query("SELECT * FROM WeeklyRecipe")
    fun getAll(): List<WeeklyRecipe>

    @Query("SELECT * FROM WeeklyRecipe ORDER BY RANDOM() LIMIT 1")
    fun getRandom(): WeeklyRecipe

    @Query("""
        SELECT CoffeeRecipe.* 
        FROM CoffeeRecipe 
        INNER JOIN WeeklyRecipe 
        ON WeeklyRecipe.coffeeRecipeId = CoffeeRecipe.recipeId 
        ORDER BY RANDOM() 
        LIMIT 1
    """)
    fun getRandomRecommendedCoffee(): CoffeeRecipe?

    @Insert
    fun insert(weeklyRecipe: WeeklyRecipe)

    @Update
    fun update(weeklyRecipe: WeeklyRecipe)

    @Delete
    fun delete(weeklyRecipe: WeeklyRecipe)
}