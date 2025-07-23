package com.example.biometricattendance.db.com.example.biometricattendance

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class, AttendanceRecord::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun attendanceDao(): AttendanceDao
}
