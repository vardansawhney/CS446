package com.example.amuse.ui.home

import com.google.gson.Gson
import java.util.Objects

data class Types(
    val id: String,
    val id2: String,
    val id3: String,
)

data class PersonUnderstanding(
    val Types: Types,
    val averagePrice: Float,
    val distance: Int,
    val numOfSources: Int,
    )
object UserUnderstanding {
    val json = """ {
    "Types": {
      "id": "0",
      "id2": "0",
      "id3": "0",
    },
    "averagePrice": "3", 
    "distance": "45"
    "numOfSources": "10"
}
""".trimIndent()
    var gson = Gson()
    val UserProfile: UserUnderstanding = gson.fromJson(json, UserUnderstanding::class.java)

    init {}

    // typesToBeUpdated == 'types'
    fun updateTypes(
        ob: UserUnderstanding,
        typesToBeUpdated: String,
        type: Nothing?,
        action: String
    ) {

//        TODO: NEED TO GET THE SOURCE UPDATES FROM SOMWHERE

        if (action == "right") {
        // TODO: NEED A CONDITIONAL HERE TO CHECK FOR WHETHER WE WANT TO UPDATE THE FILEDS OR NOT, OR WHETHER IT EXISTS
        //            rightSwipeWithPreference(type)

            /** THIS IS FINE **/
        //            rightSwipeAveragePrice(price)
        //            rightSwipeUpdateDistance(distance)
        } else if (action == "left") {
        // TODO: NEED A CONDITIONAL HERE TO CHECK FOR WHETHER WE WANT TO UPDATE THE FILEDS OR NOT, OR WHETHER IT EXISTS
        //            leftSwipeWithPreference(type)
            /** THIS IS FINE **/
        //            leftSwipeAveragePrice(price)
        //            leftSwipeUpdateDistance(distance)
        }

        //        Increment the sources
        //        incrementSources(
    }

    fun rightSwipeWithPreference(Type: String) {
        //++ob.Types.type
    }

    fun rightSwipeAveragePrice(price: Int) {
//        ob.averagePrice = (ob.averagePrice) + (ob.averagePrice / ob.numOfSources)
    }

    fun rightSwipeUpdateDistance(distance: Int) {
//        ob.distance = (ob.distance) + (ob.distance / ob.numOfSources)
    }

    fun leftSwipeWithPreference(Type: String) {
        //++ob.Types.type
    }

    fun leftSwipeAveragePrice(price: Int) {
//        ob.averagePrice = (ob.averagePrice) - (ob.averagePrice / ob.numOfSources)
    }

    fun leftSwipeUpdateDistance(distance: Int) {
//        ob.distance = (ob.distance) - (ob.distance / ob.numOfSources)
    }

    fun incrementSources(sources: Int){
        //        ++ob.numOfSources
    }
}