package com.example.amuse

import android.util.Log
import com.example.amuse.ui.CardAdapter
import com.example.amuse.ui.home.Card
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.delay
object RecommendationEngine{
    val preferredTypes : MutableList<String> = mutableListOf()
}

object LocalEventStore {
    public var currentAvailableEventsStack: MutableList<Event> = mutableListOf()
    public var likedEvents: MutableList<Event> = mutableListOf()
    public var cardList: ArrayList<Card> = ArrayList()
    public var isRunning :Boolean = false
    init{
        Log.d("tag", "YO")
        PullUntilFull()

    }
    fun getRating(rating: Double):String{
        if(rating < 1){
            return "☆☆☆☆☆"
        }else if (rating < 2){
            return "★☆☆☆☆"
        }else if (rating < 3){
            return "★★☆☆☆"
        }else if (rating < 4){
            return "★★★☆☆"
        }else{
            return "★★★★☆"
        }
    }

    fun getPriceLevelDollarSigns(p_lvl : Int):String{
        if(p_lvl == 1){
            return "$"
        }else if (p_lvl ==2){
            return "$$"
        }else if (p_lvl ==3){
            return "$$$"
        }else if (p_lvl == 4){
            return "$$$$"
        }else{
            return "$$$$$"
        }
    }
    fun PullUntilFull(override: Boolean = false, preferencesFormatter: ArrayList<String>? = null){
        if(currentAvailableEventsStack.size<5 || override){
            CoroutineScope(Dispatchers.IO).launch {
                if(override){
                    currentAvailableEventsStack.clear()
                    cardList.clear()
                }
                var preferencesFormattertemp: ArrayList<String> = ArrayList()
                if(preferencesFormatter == null){
                    preferencesFormattertemp.add("store")
                } else {
                    for (thing in preferencesFormatter) {
                        preferencesFormattertemp.add(thing)
                    }
                }
                while (currentAvailableEventsStack.size<10) {
                    Log.d("tag", "currentAvailableEventsStack is less than 10")
                    queryEvents(3, preferencesFormattertemp).collect { data->
                        for (document in data) {
                            Log.d("tag", "${document.id} => price_level: ${document.data.get("price_level")}, types: ${document.data.get("types")}")
                            val types_list = document.data.get("types") as List<String>
                            var price_level = 1
                            if (document.data.get("price_level") != null){
                                    price_level = document.data.get("price_level").toString().toInt()
                            }

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
                                price_level,
                                document.data.get("rating").toString().toDouble(),
                                types_list
                            )
                            Log.d("tag1", event.name.toString())
                            cardList.add(Card(document.data.get("name").toString(),
                                document.data.get("address").toString(),
                                getRating(document.data.get("rating").toString().toDouble()),
                                document.data.get("description").toString(),
                                getPriceLevelDollarSigns(price_level)))
                            Log.d("tag", event.name.toString())
                            if(!currentAvailableEventsStack.contains(event)){
                                currentAvailableEventsStack.add(event)
                            }
                        }
                    }
                }
            }
        }
    } 
    fun doSomething(){
        Log.d("tag", "hopefully this works")
    }
}