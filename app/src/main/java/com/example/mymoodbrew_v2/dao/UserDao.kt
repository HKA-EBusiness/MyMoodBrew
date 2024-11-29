package com.example.mymoodbrew_v2.dao

import androidx.room.*
import com.example.mymoodbrew_v2.models.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAll(): List<User>

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}