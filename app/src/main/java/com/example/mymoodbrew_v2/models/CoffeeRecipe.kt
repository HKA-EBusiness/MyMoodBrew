package com.example.mymoodbrew_v2.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoffeeRecipe(
    @PrimaryKey(autoGenerate = true) val recipeId: Int,
    val title: String,
    val description: String,
    val ingredients: String,
    val preparationSteps: String,
    val recommendedDuration: Int,
    val imageUrl: String
)