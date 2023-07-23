package com.example.amuse

//import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

const val TAG = "FIRESTORE"

class FirebaseUtils {
    val fireStoreDatabase = FirebaseFirestore.getInstance()
}

data class Event(
    val id: String,
    val source_id: String?,
    val source: String?,
    val name: String?,
    val description: String?,
    var earliest_time: String?,
    var latest_time: String?,
    val address: String?,
    val city: String?,
    val price_level: Int?,
    val rating: Double?,
    val types: List<String>?,
    val reviews: Review
)

data class Review(
    val author: String?,
    val rating: Double?,
    val text : String?,
    val time: String?
)

public fun uploadData(event: Event) {

    // use the add() method to create a document inside users collection
    FirebaseUtils().fireStoreDatabase.collection("Events").document(event.id)
        .set(event)
        .addOnSuccessListener {
            Log.d(TAG, "Added document with ID ${event.id}")
        }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error adding document $exception")
        }
}