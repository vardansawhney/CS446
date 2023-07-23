package com.example.amuse.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL_PLACES_API = "https://maps.googleapis.com/maps/api/place/nearbysearch/"

interface PlacesApi {
    @GET("json")
    suspend fun getPlaces(@Query("location") location:String, @Query("radius") radius:String, @Query("key") key:String): Response<NearbySearchResponse>
}

object PlacesApiInstance {
    val api: PlacesApi by lazy {
        ApiClient.subscribe(BASE_URL_PLACES_API)
        ApiClient
            .getApiInstance(BASE_URL_PLACES_API)
            .create(PlacesApi::class.java)
    }
}

data class NearbySearchResponse(
    val html_attributions: List<String>,
    val results: List<Place>,
    val status: String
)

data class Place(
    val business_status: String?,
    val geometry: Geometry?,
    val icon: String?,
    val icon_background_color: String?,
    val icon_mask_base_uri: String?,
    val name: String?,
    val opening_hours: OpeningHours?,
    val photos: List<Photo>?,
    val place_id: String?,
    val plus_code: PlusCode?,
    val price_level: Int?,
    val rating: Double?,
    val reference: String?,
    val scope: String?,
    val types: List<String>?,
    val user_ratings_total: Int?,
    val vicinity: String?
)