package com.example.mymoodbrew_v2.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VariationIngredient(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val variationId: Int,
    val ingredientId: Int,
    val quantity: Double,
    val unit: String
)