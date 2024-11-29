package com.example.mymoodbrew_v2.dao

import androidx.room.*
import com.example.mymoodbrew_v2.models.CoffeeVariation

@Dao
interface CoffeeVariationDao {
    @Query("SELECT * FROM CoffeeVariation")
    fun getAll(): List<CoffeeVariation>

    @Query("SELECT * FROM CoffeeVariation WHERE variationId = :id")
    fun getById(id: Int): CoffeeVariation

    @Insert
    fun insert(coffeeVariation: CoffeeVariation)

    @Update
    fun update(coffeeVariation: CoffeeVariation)

    @Delete
    fun delete(coffeeVariation: CoffeeVariation)
}