package com.example.mymoodbrew_v2.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoffeeLink(
    @PrimaryKey(autoGenerate = true) val linkId: Int,
    val variationId: Int,
    val url: String,
    val description: String
)