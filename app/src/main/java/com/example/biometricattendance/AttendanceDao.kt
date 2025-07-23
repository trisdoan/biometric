package com.example.biometricattendance.db.com.example.biometricattendance

import androidx.room.*

@Dao
interface AttendanceDao {
    @Insert
    suspend fun insertAttendance(record: AttendanceRecord)

    @Query("SELECT * FROM AttendanceRecord WHERE email = :email")
    suspend fun getRecordsForUser(email: String): List<AttendanceRecord>
}
