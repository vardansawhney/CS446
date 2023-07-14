package com.example.amuse.EventsBackend

object RecommendationEngine {
    //update UserUnderstanding
    //update UserProfile (maybe not needed)
    //
    init {
        print("Hey boi, singletons in Kotlin are easy")
    }

    fun updateUserUnderstanding(price: Int, distance : Float, weighted_types : ArrayList<Pair<String,Int>>){

    }
    fun requestData(){
        //pull from user understanding and query
        //parse this into fields you can use to instantiate an event
        //pass this information as a json to HomeFragment
        //HomeFragment will then create an event
    }
}