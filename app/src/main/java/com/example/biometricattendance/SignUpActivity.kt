package com.example.biometricattendance

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.biometricattendance.databinding.ActivitySignUpBinding
import com.example.biometricattendance.db.com.example.biometricattendance.AppDatabase
import com.example.biometricattendance.db.com.example.biometricattendance.User

import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "biometric-db").build()

        binding.signupButton.setOnClickListener {
            Toast.makeText(this, "Button clicked", Toast.LENGTH_SHORT).show()

            val name = binding.nameField.text.toString()
            val email = binding.emailField.text.toString()
            val password = binding.passwordField.text.toString()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailField.error = "Invalid email"
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    db.userDao().insertUser(User(email, name, password))
                    runOnUiThread {
                        Toast.makeText(this@SignUpActivity, "Sign up successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
                        finish()
                    }
                } catch (e: Exception) {
                    runOnUiThread {
                        Toast.makeText(this@SignUpActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                    e.printStackTrace()
                }
            }

        }
    }
}
