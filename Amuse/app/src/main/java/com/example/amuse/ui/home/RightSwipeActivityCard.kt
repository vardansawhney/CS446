package com.example.amuse.ui.home

import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.amuse.EventsBackend.RecommendationEngine

class RightSwipeActivityCard: ActivityCard {
    public override fun swipeActivity(price : Int, distance: Float, types : List<String>){
        //TODO: send to recEngine
        var weighted_types = ArrayList<Pair<String,Int>>();

        for(type in types){
            weighted_types.add(Pair(type,2))
        }
        RecommendationEngine.updateUserUnderstanding(price,distance,weighted_types)
        //TODO: potentially return false if data fails to send onward
    }

    //transmitEventOnSwipe can be overridden here to send liked event to liked events page @Denis
}