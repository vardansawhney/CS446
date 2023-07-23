package com.example.amuse.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApi {
    @GET("json")
    suspend fun getPlaces(@Query("location") location:String, @Query("radius") radius:String, @Query("key") key:String): Response<NearbySearchResponse>
}

object PlacesApiInstance {
    val api: PlacesApi by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/place/nearbysearch/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PlacesApi::class.java)
    }
}