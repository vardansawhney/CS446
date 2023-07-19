package com.example.amuse.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.amuse.R
import com.example.amuse.databinding.ActivityRegisterUserBinding

class RegisterUserActivity : AppCompatActivity(){
        private lateinit var binding: ActivityRegisterUserBinding

//    private fun validPassword(): String? {
//        val passwordText = ActivityRegisterUserBinding.loginPassword.text.toString()
//        if(passwordText.length < 8) {
//            return "Minimum 8 Character Password"
//        }
//        if(!passwordText.matches(".*[A-Z].*".toRegex())) {
//            return "Must Contain 1 Upper-case Character"
//        }
//        if(!passwordText.matches(".*[a-z].*".toRegex())) {
//            return "Must Contain 1 Lower-case Character"
//        }
//        if(!passwordText.matches(".*[@#\$%^&+=].*".toRegex())) {
//            return "Must Contain 1 Special Character (@#\$%^&+=)"
//        }
//        return null
//    }

//    private fun checkIfEmailIsValid(): String? {
//
//        val emailInputText = ActivityRegisterUserBinding.loginEmail.text.toString()
//        ActivityRegisterUserBinding.loginEmail.doOnTextChanged { text, start, before, count ->
//            if(!Patterns.EMAIL_ADDRESS.matcher(emailInputText).matches()){
//                ActivityRegisterUserBinding.loginEmail.error = "Invalid Email Address"
//            }
//            else{
//                ActivityRegisterUserBinding.loginEmail.error = null
//            }
//        }
//    }

//    private fun login() {
//        ActivityRegisterUserBinding.loginEmailContainer.helperText = checkIfEmailIsValid()
//        ActivityRegisterUserBinding.loginPasswordContainer.helperText = validPassword()
//
//        val checkIfEmailIsValid = ActivityRegisterUserBinding.loginEmailContainer.helperText == null
//        val validPassword = ActivityRegisterUserBinding.loginPasswordContainer.helperText == null
//
//        if (checkIfEmailIsValid && validPassword) {
//            Toast.makeText(this,"Valid Form", Toast.LENGTH_SHORT).show()
//            resetForm()
//
//        }
//
//        else
//            Toast.makeText(this, "Invalid Form", Toast.LENGTH_SHORT).show()
//    }

//    private fun resetForm() {
//
//        ActivityRegisterUserBinding.loginEmail.text = null
//        ActivityRegisterUserBinding.loginPassword.text = null
//
//        ActivityRegisterUserBinding.loginPasswordContainer.helperText = "Required"
//        ActivityRegisterUserBinding.loginEmailContainer.helperText = "Required"
//    }

//    private fun passwordInputTextOnFocusListener() {
//        ActivityRegisterUserBinding.loginPassword.setOnFocusChangeListener { _, focused ->
//            if(!focused){
//                ActivityRegisterUserBinding.loginPasswordContainer.helperText = validPassword()
//            }
//        }
//    }
//
//    private fun emailInputTextOnFocusListener() {
//        ActivityRegisterUserBinding.loginEmail.setOnFocusChangeListener { _, focused ->
//            if(!focused){
//                ActivityRegisterUserBinding.loginEmailContainer.helperText = checkIfEmailIsValid()
//            }
//        }
//    }
//
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityRegisterUserBinding.inflate(layoutInflater)
            setContentView(R.layout.activity_register_user)
//    emailInputTextOnFocusListener()
//    passwordInputTextOnFocusListener()
//    phoneInputTextOnFocusListener()

            val buttonClick = findViewById<Button>(R.id.register_button)
            buttonClick.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }