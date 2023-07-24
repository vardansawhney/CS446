package com.example.amuse

import android.content.Context
import android.content.Intent
import com.example.amuse.ui.home.AvailableGroupsActivity

class LeftSwipeAction(val context: Context):CardAction {
    override fun swipeActivity(event: Event){
        var weighted_types = ArrayList<Pair<String,Int>>()
        event.types?.forEach { type ->
            weighted_types.add(Pair(type, -1))
        }

    }

}