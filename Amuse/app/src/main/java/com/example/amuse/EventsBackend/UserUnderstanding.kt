package com.example.amuse.EventsBackend

import com.google.gson.Gson

//data class PersonUnderstanding(
//    val Types: Types,
//    val desiredPrice: Float,
//    val desiredDistance: Int,
//    val numOfSources: Int,
//    )
object UserUnderstanding {
    lateinit val desireForTypes : MutableList<Pair<Types,Int>>

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

//        TODO: NEED TO GET THE SOURCE UPDATES FROM SOMEWHERE

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
        //        incrementSources()
    }
}