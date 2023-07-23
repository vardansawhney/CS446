package com.example.amuse

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.amuse.databinding.ActivityMainBinding
import com.example.amuse.network.PlacesApiInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val groupInfo = this.getIntent()?.getExtras()?.getStringArray("groupInfo")
//        Log.d("MainActivity", "hello, testing things")
//        Log.d("ooogadiboogady", "$groupInfo")
//        for (i in 0..groupInfo!!.size - 1){
//            var a = groupInfo[i]
//            Log.d("in_message", "$a")
//        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.fragment_home)

        val navView: BottomNavigationView = binding.navView

        /*
        * Testing **********************************************************************************
        * */
        val apiKey: String = try {
            val ai = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
            ai.metaData?.getString("com.google.android.geo.API_KEY")!!
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e("API_KEY", "key not found maybe")
            ""
        }
        Log.e("Printing Key", apiKey)

        lifecycleScope.launch {
            val response = try {
                PlacesApiInstance.api.getPlaces("-33.8670522,151.1957362", "1500", apiKey)
            } catch (e: IOException) {
                Log.e("ERROR", "IO error")
                return@launch
            } catch (e: HttpException) {
                Log.e("ERROR", "HTTP error")
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                Log.e("Printing data", response.body().toString())
            } else {
                Log.e("ERROR", "unsuccessful")
            }
        }
        /*
        * Testing **********************************************************************************
        * */

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
    }
}