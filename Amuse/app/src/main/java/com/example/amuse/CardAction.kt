package com.example.amuse

import android.content.Intent
import com.example.amuse.FirebaseUtils
public interface CardAction {
    public fun swipeActivity(event: Event);

    public fun transmitEventOnSwipe(event: Event){
        print("need to change this to move the event to wherever relevant. Like the user profile")
    }
}