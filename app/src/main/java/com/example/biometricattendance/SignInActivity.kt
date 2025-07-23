package com.example.biometricattendance

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.biometricattendance.databinding.ActivitySignInBinding
import com.example.biometricattendance.db.com.example.biometricattendance.AppDatabase
import kotlinx.coroutines.launch

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "biometric-db").build()

        binding.signinButton.setOnClickListener {
            val email = binding.emailField.text.toString()
            val password = binding.passwordField.text.toString()

            lifecycleScope.launch {
                val user = db.userDao().getUser(email)
                runOnUiThread {
                    if (user != null && user.password == password) {
                        val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                        intent.putExtra("email", email)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@SignInActivity, "Invalid credentials", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding.signupRedirectButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
