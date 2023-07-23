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
    val source_id: String,
    val source: String,
    val name: String,
    val description: String,
    var earliest_time: String,
    var latest_time: String,
    val address: String,
    val price_level: Int,
    val rating: Float
)

public fun uploadData(event: Event) {

    // use the add() method to create a document inside users collection
    FirebaseUtils().fireStoreDatabase.collection("Events")
        .add(event)
        .addOnSuccessListener {
            Log.d(TAG, "Added document with ID ${it.id}")
        }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error adding document $exception")
        }
}

// Haven't tested.
public fun readData(){
    FirebaseUtils().fireStoreDatabase.collection("Events")
        .get()
        .addOnSuccessListener { querySnapshot ->
            querySnapshot.forEach { document ->
                Log.d(TAG, "Read document with ID ${document.id}")
            }
        }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents $exception")
        }
}