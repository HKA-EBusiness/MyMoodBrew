package com.example.mymoodbrew_v2.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoffeeVariation(
    @PrimaryKey(autoGenerate = true) val variationId: Int,
    val name: String,
    val description: String,
    val caffeineContent: Double,
    val calories: Int,
    val fatContent: Double,
    val sugar: Double,
    val imageUrl: String,
    val recipeId: Int
)