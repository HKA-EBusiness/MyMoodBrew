package com.example.mymoodbrew_v2.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @PrimaryKey(autoGenerate = true) val favoriteId: Int,
    val userId: Int,
    val coffeeRecipeId: Int,
    val createdAt: String
)