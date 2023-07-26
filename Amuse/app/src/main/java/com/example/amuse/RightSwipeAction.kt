package com.example.amuse

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.amuse.ui.dashboard.DashboardFragment
import com.example.amuse.ui.home.AvailableGroupsActivity

class RightSwipeAction(val context: Context):CardAction {
    val localStore = LocalEventStore

    override fun swipeActivity(event: Event){
        var weighted_types = ArrayList<Pair<String,Int>>()
        event.types?.forEach { type ->
            weighted_types.add(Pair(type, 1))
        }
        localStore.likedEvents.add(event)
        Log.d("liked",localStore.likedEvents.size.toString())
        localStore.cardList.removeFirst()

//        val intent = Intent(context, MainActivity::class.java)
//        intent.putExtra("Info-ID", event.id)
//        intent.putExtra("Info-StartTime", event.earliest_time)
//        intent.putExtra("Info-Endtime", event.latest_time)
//        intent.putExtra("Info-source_id", event.source_id)
//        intent.putExtra("Info-source", event.source)
//        intent.putExtra("Info-description", event.description)
//        intent.putExtra("Info-address", event.address)
//        intent.putExtra("Info-city", event.city)
//        intent.putExtra("Info-price_level", event.price_level)
//        intent.putExtra("Info-rating", event.rating)
//        context.startActivity(intent)
        localStore.PullUntilFull()

    }

}