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

public fun queryEvents(price_level: Int, types: List<String>){
    val event_ref = FirebaseUtils().fireStoreDatabase.collection("Events")

    var query = event_ref
                    .whereLessThanOrEqualTo("price_level", price_level)
                    .whereArrayContainsAny("types", types)

    // This query works but need to add callback
//    query
//        .get()
//        .addOnSuccessListener { documents ->
//            for (document in documents) {
//                Log.d(TAG, "${document.id} => ${document.data}")
//            }
//        }
//        .addOnFailureListener { exception ->
//            Log.w(TAG, "Error getting documents: ", exception)
//        }

        query
            .get()
            .addOnSuccessListener { documents ->
                return documents
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

}