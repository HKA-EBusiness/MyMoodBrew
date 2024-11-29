package com.example.mymoodbrew_v2.dao

import androidx.room.*
import com.example.mymoodbrew_v2.models.Ingredient

@Dao
interface IngredientDao {
    @Query("SELECT * FROM Ingredient")
    fun getAll(): List<Ingredient>

    @Insert
    fun insert(ingredient: Ingredient)

    @Update
    fun update(ingredient: Ingredient)

    @Delete
    fun delete(ingredient: Ingredient)
}