package com.example.amuse.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.amuse.FirebaseUtils
import com.example.amuse.MainActivity
import com.example.amuse.R
import com.example.amuse.User
import com.example.amuse.createUser
import com.example.amuse.databinding.ActivityRegisterUserBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream


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

        val button = findViewById<Button>(R.id.register_button)
        button.setOnClickListener {
            val name = findViewById<EditText>(R.id.full_name)
            val email = findViewById<EditText>(R.id.register_email)
            val confirmEmail = findViewById<EditText>(R.id.register_confirm_email)
            val password = findViewById<EditText>(R.id.register_password)
            var block = true
            if(email.text.toString() == confirmEmail.text.toString()){
                // TODO: Perform email validation
                // TODO: Validate password security
                runBlocking {
                    val userDocument = FirebaseUtils().fireStoreDatabase.collection("Users").document(email.text.toString())
                    val user = userDocument.get().await()
                    if (user.exists()){
                        //update the UI
                        email.error = "User ${user.data?.get("email").toString()} already exists"
                        email.requestFocusFromTouch();
                    }
                    else{
                        block = false
                    }
                }
                if(!block){
                    GlobalScope.launch(Dispatchers.IO) {
                        val newUser = User(email.text.toString(), name.text.toString(), password.text.toString(), R.drawable.amuse_profile.toString(), ArrayList<String>(), ArrayList<String>(), ArrayList<String>())

                        // add new user to database
                        createUser(newUser)

                        // add profile pic to storage
                        val defaultProfile = BitmapFactory.decodeResource(
                            resources, R.drawable.amuse_profile
                        )
                        val storageRef = FirebaseUtils().fireStoreStorage.reference
                        val baos = ByteArrayOutputStream()
                        defaultProfile!!.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                        val data = baos.toByteArray()
                        val profileImage = storageRef.child("${email.text}.jpg")
                        profileImage.putBytes(data)
                    }
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
            }
            else {
                confirmEmail.error = "Different confirmation email"
                email.requestFocusFromTouch();
            }
        }
    }
}