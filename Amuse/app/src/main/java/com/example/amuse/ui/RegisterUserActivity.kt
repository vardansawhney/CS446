package com.example.amuse.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.amuse.R
import com.example.amuse.databinding.ActivityRegisterUserBinding

class RegisterUserActivity : AppCompatActivity(){
        private lateinit var binding: ActivityRegisterUserBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityRegisterUserBinding.inflate(layoutInflater)
            setContentView(R.layout.activity_register_user)

            val buttonClick = findViewById<Button>(R.id.register_button)
            buttonClick.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }