package com.example.amuse.network

data class AddressComponent(
    val long_name: String,
    val short_name: String,
    val types: List<String>
)


data class OpeningHours(
    val open_now: Boolean?,
    val periods: List<Period>?,
    val weekday_text: List<String>?
)

data class Period(
    val close: DayTime,
    val open: DayTime
)

data class DayTime(
    val day: Int,
    val time: String
)

data class ApiReview(
    val author_name: String,
    val author_url: String,
    val language: String,
    val profile_photo_url: String,
    val rating: Double,
    val relative_time_description: String,
    val text: String,
    val time: Long
)

data class Geometry(
    val location: Location?,
    val viewport: Viewport?
)

data class Location(
    val lat: Double?,
    val lng: Double?
)

data class Viewport(
    val northeast: Location?,
    val southwest: Location?
)


data class Photo(
    val height: Int?,
    val html_attributions: List<String>?,
    val photo_reference: String?,
    val width: Int?
)

data class PlusCode(
    val compound_code: String?,
    val global_code: String?
)