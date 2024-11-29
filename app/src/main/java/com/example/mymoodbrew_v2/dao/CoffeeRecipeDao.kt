package com.example.mymoodbrew_v2.dao

import androidx.room.*
import com.example.mymoodbrew_v2.models.CoffeeRecipe

@Dao
interface CoffeeRecipeDao {
    @Query("SELECT * FROM CoffeeRecipe ORDER BY recipeId")
    fun getAllSorted(): List<CoffeeRecipe>

    @Query("SELECT * FROM CoffeeRecipe WHERE recipeId = :id")
    fun getById(id: Int): CoffeeRecipe

    @Insert
    fun insert(coffeeRecipe: CoffeeRecipe)

    @Update
    fun update(coffeeRecipe: CoffeeRecipe)

    @Delete
    fun delete(coffeeRecipe: CoffeeRecipe)
}