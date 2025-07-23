package com.example.biometricattendance.db.com.example.biometricattendance

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User WHERE email = :email")
    suspend fun getUser(email: String): User?
}
