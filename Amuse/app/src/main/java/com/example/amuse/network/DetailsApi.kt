package com.example.amuse.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL_DETAILS_API = "https://maps.googleapis.com/maps/api/place/details/"
interface DetailsApi {
    @GET("json")
    suspend fun getDetails(@Query("place_id") place_id:String, @Query("key") key:String): Response<PlacesDetailsResponse>
}

object DetailsApiInstance {
    val api: DetailsApi by lazy {
        ApiClient.subscribe(BASE_URL_DETAILS_API)
        ApiClient
            .getApiInstance(BASE_URL_DETAILS_API)
            .create(DetailsApi::class.java)
    }
}

data class PlacesDetailsResponse(
    val html_attributions: List<String>,
    val result: PlaceDetails,
    val status: String
)

data class PlaceDetails(
    val address_components: List<AddressComponent>?,
    val adr_address: String?,
    val business_status: String?,
    val editorial_summary: PlaceEditorialSummary?,
    val formatted_address: String?,
    val formatted_phone_number: String?,
    val geometry: Geometry?,
    val icon: String?,
    val icon_background_color: String?,
    val icon_mask_base_uri: String?,
    val international_phone_number: String?,
    val name: String?,
    val opening_hours: OpeningHours?,
    val photos: List<Photo>?,
    val place_id: String?,
    val plus_code: PlusCode?,
    val rating: Double?,
    val reference: String?,
    val reviews: List<ApiReview>?,
    val types: List<String>?,
    val url: String?,
    val user_ratings_total: Int?,
    val utc_offset: Int?,
    val vicinity: String?,
    val website: String?
)

data class PlaceEditorialSummary(
    val language: String,
    val overview: String
)