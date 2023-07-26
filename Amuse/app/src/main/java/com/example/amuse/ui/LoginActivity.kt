package com.example.amuse.ui

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.example.amuse.Event
import com.example.amuse.LocalEventStore
import com.example.amuse.MainActivity
import com.example.amuse.R
import com.example.amuse.Review
import com.example.amuse.databinding.ActivityLoginBinding
import com.example.amuse.network.AddressComponent
import com.example.amuse.network.DetailsApiInstance
import com.example.amuse.network.PlacesApiInstance
import com.example.amuse.uploadData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    val localStore = LocalEventStore
    override fun onCreate(savedInstanceState: Bundle?) {
        // API requests and DB updates
        apiPullDbPush()

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)

        val buttonClick = findViewById<Button>(R.id.login_button)
        buttonClick.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        localStore.doSomething()
        val registerButtonClick = findViewById<Button>(R.id.register_button)
        registerButtonClick.setOnClickListener {
            val intent = Intent(this, RegisterUserActivity::class.java)
            startActivity(intent)
    }
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
                PlacesApiInstance.api.getPlaces("43.468529, -80.550664", "1000", apiKey)
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