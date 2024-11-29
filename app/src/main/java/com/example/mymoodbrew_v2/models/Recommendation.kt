package com.example.mymoodbrew_v2.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recommendation(
    @PrimaryKey(autoGenerate = true) val recommendationId: Int,
    val userId: Int,
    val coffeeRecipeId: Int,
    val createdAt: String,
    val expiresAt: String
)