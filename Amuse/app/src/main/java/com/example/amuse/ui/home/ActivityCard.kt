package com.example.amuse.ui.home

import android.content.Intent
import com.example.amuse.EventsBackend.Event

public interface ActivityCard {
    public fun swipeActivity(price : Int, distance: Float, types : List<String>);

    //TODO: override this in all concreteStrategies, as needed to do more on their swipe action
    public fun transmitEventOnSwipe(event: Event){
        print("need to change this to move the event to wherever relevant. Like the user profile")
    }
}