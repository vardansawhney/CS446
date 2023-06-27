package com.example.amuse

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.amuse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("TAG", "hello")
        super.onCreate(savedInstanceState)
//        val button = findViewById<Button>(R.id.login)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_groups, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        login.setOnClickListener {
//            Log.d("TAG", "message")
//                loading.visibility = View.VISIBLE
//                loginViewModel.login(username.text.toString(), password.text.toString())
//            setContentView(R.layout.fragment_notifications)
//            if(username.text.toString() === "test" && password.text.toString() === "1234"){
//                val buttonClick = findViewById<Button>(R.id.login)
//                buttonClick.setOnClickListener {
//                    val intent = Intent(this.context, ActivityMainBinding::class.java)
//                    startActivity(intent)
//                }
//            }
//        }
        setContentView(R.layout.activity_login)
//        setContentView(binding.root)
    }
}