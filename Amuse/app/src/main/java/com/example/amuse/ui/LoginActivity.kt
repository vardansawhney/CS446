package com.example.amuse.ui

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.amuse.FirebaseUtils
import com.example.amuse.MainActivity
import com.example.amuse.MainActivity.Companion.myProfileImage
import com.example.amuse.MainActivity.Companion.myUser
import com.example.amuse.R
import com.example.amuse.User
import com.example.amuse.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import java.io.File


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)

        val buttonClick = findViewById<Button>(R.id.login_button)
        buttonClick.setOnClickListener {
            val email = findViewById<EditText>(R.id.login_username)
            val password = findViewById<EditText>(R.id.login_password)
            var storedPassword = ""
            val userDocument = FirebaseUtils().fireStoreDatabase.collection("Users").document(email.text.toString())
            val intent = Intent(this, MainActivity::class.java)
            runBlocking{
                val user = userDocument.get().await()
                if (user.exists()) {
                    storedPassword = user.data?.get("password").toString()
                    if(password.text.toString() == storedPassword){
                        Log.d("TEST", "signed in with $storedPassword")
                        myUser = User(user.data?.get("email").toString()
                            ,user.data?.get("name").toString()
                            ,user.data?.get("password").toString()
                            ,user.data?.get("picture").toString()
                            ,user.data?.get("friends") as ArrayList<String>
                            ,user.data?.get("groups") as ArrayList<String>
                            ,user.data?.get("pendingFriends") as ArrayList<String>)

                        runBlocking {
                            val storageRef = FirebaseUtils().fireStoreStorage.reference.child("${myUser.picture}")
                            val localFile = File.createTempFile("tempfile", "jpg")
                            storageRef.getFile(localFile).addOnSuccessListener {
                                val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                                myProfileImage = bitmap
                            }
                        }

                        startActivity(intent)
                    }
                    else{
                        password.error = "Incorrect password"
                        password.requestFocusFromTouch()
                    }
                }
                else{
                    email.error = "User does not exist"
                    email.requestFocusFromTouch()
                }
            }
        }

        val registerButtonClick = findViewById<Button>(R.id.register_button)
        registerButtonClick.setOnClickListener {
            val intent = Intent(this, RegisterUserActivity::class.java)
            startActivity(intent)
        }
    }
}