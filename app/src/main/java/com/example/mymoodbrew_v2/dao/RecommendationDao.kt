package com.example.mymoodbrew_v2.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mymoodbrew_v2.models.CoffeeRecipe
import com.example.mymoodbrew_v2.models.Recommendation

@Dao
interface RecommendationDao {
    @Query("SELECT * FROM Recommendation")
    fun getAll(): List<Recommendation>

    @Query("SELECT * FROM Recommendation ORDER BY RANDOM() LIMIT 1")
    fun getRandom(): Recommendation

    @Query("""
        SELECT CoffeeRecipe.*
        FROM Recommendation
        INNER JOIN CoffeeRecipe ON Recommendation.coffeeRecipeId = CoffeeRecipe.recipeId
        WHERE Recommendation.userId = :userId
        ORDER BY RANDOM()
        LIMIT 1
    """)
    fun getRandomRecommendedCoffee(userId: Int): CoffeeRecipe

    @Insert
    fun insert(recommendation: Recommendation)

    @Update
    fun update(recommendation: Recommendation)

    @Delete
    fun delete(recommendation: Recommendation)
}