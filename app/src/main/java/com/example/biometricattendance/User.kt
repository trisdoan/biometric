package com.example.biometricattendance.db.com.example.biometricattendance

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val email: String,
    val name: String,
    val password: String,
    var biometricRegistered: Boolean = false
)
