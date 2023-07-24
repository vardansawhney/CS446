package com.example.amuse

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import com.google.firebase.firestore.DocumentReference

object LocalEventStore {
    public lateinit var currentAvailableEventsStack: MutableList<Event>
    init{
        Log.d("tag", "YO")
        CoroutineScope(Dispatchers.IO).launch {
            queryEvents(3, listOf("restaurant")).collect { data->
                for (document in data) {
                    Log.d("tag", "${document.id} => price_level: ${document.data.get("price_level")}, types: ${document.data.get("types")}")
                    val types_list = document.data.get("types") as List<String>
                    val event = Event(
                        document.id,
                        document.data.get("source-id").toString(),
                        document.data.get("source").toString(),
                        document.data.get("name").toString(),
                        document.data.get("description").toString(),
                        document.data.get("earliest_time").toString(),
                        document.data.get("latest_time").toString(),
                        document.data.get("address").toString(),
                        document.data.get("city").toString(),
                        document.data.get("price_level").toString().toInt(),
                        document.data.get("rating").toString().toDouble(),
                        types_list
                        )
                    Log.d("tag", event.toString())
                    if(!currentAvailableEventsStack.contains(event)){
                        currentAvailableEventsStack.add(event)
                    }
                    if(currentAvailableEventsStack.size >=10){
                        break;
                    }

                }
            }
        }










    }
    fun doSomething(){
        Log.d("tag", "hopefully this works")
    }
}