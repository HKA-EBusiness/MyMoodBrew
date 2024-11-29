package com.example.mymoodbrew_v2.dao

import androidx.room.*
import com.example.mymoodbrew_v2.models.VariationIngredient

@Dao
interface VariationIngredientDao {
    @Query("SELECT * FROM VariationIngredient")
    fun getAll(): List<VariationIngredient>

    @Insert
    fun insert(variationIngredient: VariationIngredient)

    @Update
    fun update(variationIngredient: VariationIngredient)

    @Delete
    fun delete(variationIngredient: VariationIngredient)
}