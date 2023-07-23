package com.example.amuse.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailsApi {
    @GET("json")
    suspend fun getDetails(@Query("place_id") place_id:String, @Query("key") key:String): Response<PlacesDetailsResponse>
}

object DetailsApiInstance {
    val api: DetailsApi by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/place/details/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DetailsApi::class.java)
    }
}