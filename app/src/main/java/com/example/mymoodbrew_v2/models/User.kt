package com.example.mymoodbrew_v2.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int,
    val name: String,
    val email: String,
    val passwordHash: String
)