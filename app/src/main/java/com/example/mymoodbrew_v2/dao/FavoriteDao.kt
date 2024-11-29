package com.example.mymoodbrew_v2.dao

import androidx.room.*
import com.example.mymoodbrew_v2.models.Favorite

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM Favorite")
    fun getAll(): List<Favorite>

    @Insert
    fun insert(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)
}