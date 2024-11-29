package com.example.mymoodbrew_v2.dao

import androidx.room.*
import com.example.mymoodbrew_v2.models.CoffeeLink

@Dao
interface CoffeeLinkDao {
    @Query("SELECT * FROM CoffeeLink")
    fun getAll(): List<CoffeeLink>

    @Insert
    fun insert(coffeeLink: CoffeeLink)

    @Update
    fun update(coffeeLink: CoffeeLink)

    @Delete
    fun delete(coffeeLink: CoffeeLink)
}