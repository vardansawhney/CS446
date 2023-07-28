package com.example.amuse

//import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

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
    val reviews: Review? = null,
    val imageURL: String? = null
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

public suspend fun queryEvents(price_level: Int, types: List<String>) = callbackFlow<QuerySnapshot>{
    var list:MutableList<Int?> = mutableListOf()
    var i : Int = 1
    while(i <= price_level){
        list.add(i)
        i++
    }
    list.add(null)
    FirebaseUtils().fireStoreDatabase.collection("Events")
        .whereIn("price_level",list)
        .whereArrayContainsAny("types", types)
        .get()
        .addOnSuccessListener { documents ->
            trySend(documents)
        }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents: ", exception)
        }

    awaitClose {
        Log.d(TAG, "Await channel closed")
        channel.close()
    }
}

data class Group(
    val groupID: String,
    val availableSpots: Int,
    var creatorID: String,
    var startTime: String,
    var endTime: String,
    var members: ArrayList<String>
)

public fun createGroup(group: Group) {
    FirebaseUtils().fireStoreDatabase.collection("Groups").document(group.groupID)
        .set(group)
        .addOnSuccessListener {
            Log.d(TAG, "Added document with ID ${group.groupID}")
        }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error adding document $exception")
        }
}

data class User(
    val email: String,
    var name: String,
    var password: String,
    var picture: String,
    var friends: ArrayList<String>,
    var groups: ArrayList<String>,
    var pendingFriends: ArrayList<String>
)

public fun createUser(user: User) {
    FirebaseUtils().fireStoreDatabase.collection("Users").document(user.email)
        .set(user)
        .addOnSuccessListener {
            Log.d(TAG, "Added document with ID ${user.email}")
        }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error adding document $exception")
        }
}

public fun resetPassword(email: String){

}

public fun getUser(email: String) = callbackFlow<DocumentSnapshot> {
    FirebaseUtils().fireStoreDatabase.collection("Users").document(email)
        .get()
        .addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot != null) {
                Log.d(TAG, "DocumentSnapshot data: ${documentSnapshot.data}")
                trySend(documentSnapshot)
            } else {
                Log.d(TAG, "No such document")
            }
        }
        .addOnFailureListener { exception ->
            Log.d(TAG, "get failed with ", exception)
        }
    awaitClose {
        Log.d(TAG, "Await channel closed")
        channel.close()
    }
}

//public fun getUser(email: String): User?{
//    var user = User("","","", "", ArrayList<String>(), ArrayList<String>())
//    GlobalScope.launch(Dispatchers.IO) {
//        val doc = FirebaseUtils().fireStoreDatabase.collection("Users").document(email).get().await().toObject(User)
//    }
//}

public fun updateUser(email: String){

}

public fun deleteUser(email: String){}


public fun createGroup(user: User) {
}

public fun updateGroup(email: String){}

public fun getPendingGroups(email: String){
}

public fun getClosedGroups(email: String){
}

public fun deleteGroup(groupID: String, email: String){}

public fun removeFromJoinedGroup(groupID: String, email: String){}

