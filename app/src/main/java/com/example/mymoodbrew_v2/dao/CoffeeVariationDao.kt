package com.example.mymoodbrew_v2.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mymoodbrew_v2.models.CoffeeVariation

@Dao
interface CoffeeVariationDao {
    @Query("SELECT * FROM CoffeeVariation")
    fun getAll(): List<CoffeeVariation>

    @Query("SELECT * FROM CoffeeVariation WHERE variationId = :id")
    fun getById(id: Int): CoffeeVariation

    @Query("SELECT * FROM CoffeeVariation WHERE name LIKE :name")
    fun getByName(name: String): List<CoffeeVariation>

    @Query("SELECT * FROM CoffeeVariation WHERE caffeineContent >= :minCaffeine AND caffeineContent <= :maxCaffeine")
    fun getByCaffeineContentRange(minCaffeine: Double, maxCaffeine: Double): List<CoffeeVariation>

    @Query("SELECT * FROM CoffeeVariation WHERE calories <= :maxCalories")
    fun getByMaxCalories(maxCalories: Int): List<CoffeeVariation>

    @Query("SELECT * FROM CoffeeVariation WHERE recipeId = :recipeId")
    fun getByRecipeId(recipeId: Int): List<CoffeeVariation>

    @Insert
    fun insert(coffeeVariation: CoffeeVariation)

    @Update
    fun update(coffeeVariation: CoffeeVariation)

    @Delete
    fun delete(coffeeVariation: CoffeeVariation)
}