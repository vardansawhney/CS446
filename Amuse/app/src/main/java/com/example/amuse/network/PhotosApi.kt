package com.example.amuse.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL_PHOTOS_API = "https://maps.googleapis.com/maps/api/place/"
interface PhotosApi {
    @GET("photo")
    suspend fun getPhotos(@Query("photo_reference") photo_reference:String, @Query("key") key:String): Response<ResponseBody>
}

object PhotosApiInstance {
    val api: PhotosApi by lazy {
        ApiClient.subscribe(BASE_URL_PHOTOS_API)
        ApiClient
            .getApiInstance(BASE_URL_PHOTOS_API)
            .create(PhotosApi::class.java)
    }
}