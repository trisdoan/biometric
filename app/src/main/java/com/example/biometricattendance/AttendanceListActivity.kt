package com.example.biometricattendance

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.biometricattendance.databinding.ActivityAttendanceListBinding
import com.example.biometricattendance.db.com.example.biometricattendance.AppDatabase
import kotlinx.coroutines.launch

class AttendanceListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAttendanceListBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAttendanceListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "biometric-db").build()

        val email = intent.getStringExtra("email") ?: return

        lifecycleScope.launch {
            val records = db.attendanceDao().getRecordsForUser(email)
            val displayText = records.joinToString("\n") { "${it.type}: ${it.dateTime}" }
            runOnUiThread {
                binding.attendanceListText.text = displayText.ifEmpty { "No records found." }
            }
        }
    }
}
