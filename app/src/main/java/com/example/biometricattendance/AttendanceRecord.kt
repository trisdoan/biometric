package com.example.biometricattendance.db.com.example.biometricattendance

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AttendanceRecord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email: String,
    val dateTime: String,
    val type: String // "check-in" or "check-out"
)
