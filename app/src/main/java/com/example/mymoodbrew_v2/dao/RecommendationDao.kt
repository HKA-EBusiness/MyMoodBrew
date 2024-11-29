package com.example.mymoodbrew_v2.dao

import androidx.room.*
import com.example.mymoodbrew_v2.models.Recommendation

@Dao
interface RecommendationDao {
    @Query("SELECT * FROM Recommendation")
    fun getAll(): List<Recommendation>

    @Query("SELECT * FROM Recommendation ORDER BY RANDOM() LIMIT 1")
    fun getRandom(): Recommendation

    @Insert
    fun insert(recommendation: Recommendation)

    @Update
    fun update(recommendation: Recommendation)

    @Delete
    fun delete(recommendation: Recommendation)
}