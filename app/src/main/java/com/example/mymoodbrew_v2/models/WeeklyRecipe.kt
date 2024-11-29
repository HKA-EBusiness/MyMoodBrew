package com.example.mymoodbrew_v2.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeeklyRecipe(
    @PrimaryKey(autoGenerate = true) val weeklyRecipeId: Int,
    val coffeeRecipeId: Int,
    val startDate: String,
    val endDate: String
)