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
import com.example.amuse.network.AddressComponent
import com.example.amuse.network.DetailsApiInstance
import com.example.amuse.network.PlacesApiInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.fragment_home)

        val navView: BottomNavigationView = binding.navView

        // API requests and DB updates
        apiPullDbPush()

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_groups, R.id.navigation_notifications
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun apiPullDbPush() {
        val apiKey: String = try {
            val ai = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
            ai.metaData?.getString("com.google.android.geo.API_KEY")!!
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e("API_KEY", "key not found maybe")
            ""
        }

        lifecycleScope.launch {
            val places_response = try {
                PlacesApiInstance.api.getPlaces("43.642884, -79.388413", "1000", apiKey)
            } catch (e: IOException) {
                Log.e("ERROR", "IO error in")
                return@launch
            } catch (e: HttpException) {
                Log.e("ERROR", "HTTP error")
                return@launch
            }

            if (places_response.isSuccessful && places_response.body() != null) {
                val data = places_response.body()!!
                for (place in data.results) {

                    val details_response = try {
                        DetailsApiInstance.api.getDetails(place.place_id!!, apiKey)
                    } catch (e: IOException) {
                        Log.e("ERROR", "IO error")
                        return@launch
                    } catch (e: HttpException) {
                        Log.e("ERROR", "HTTP error")
                        return@launch
                    }

                    if (details_response.isSuccessful && details_response.body() != null) {
                        val details_data = details_response.body()!!
                        val place_details = details_data.result

                        val place_city = getCity(place_details.address_components!!)

                        val event = Event(
                            "e_${place.place_id}",
                            place.place_id,
                            "google",
                            place.name,
                            place_details.editorial_summary?.overview,
                            place_details.opening_hours?.periods?.get(0)?.open?.time,
                            place_details.opening_hours?.periods?.get(0)?.close?.time,
                            place_details.formatted_address,
                            place_city,
                            place.price_level,
                            place.rating,
                            place.types,
                            Review(
                                place_details.reviews?.get(0)?.author_name,
                                place_details.reviews?.get(0)?.rating,
                                place_details.reviews?.get(0)?.text,
                                place_details.reviews?.get(0)?.relative_time_description
                            )
                        )

                        CoroutineScope(Dispatchers.IO).launch {
                            uploadData(event)
                        }
                    }
                }
                Log.i("apiPullDbPush", "No errors")
            } else {
                Log.e("ERROR", "unsuccessful")
            }
        }
    }

    private fun getCity(comps: List<AddressComponent>): String {
        for (comp in comps) {
            if ("administrative_area_level_2" in comp.types) {
                return comp.long_name
            }
        }

        return "NaC"
    }
}