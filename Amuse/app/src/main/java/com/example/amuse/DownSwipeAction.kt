package com.example.amuse

import android.content.Context
import android.content.Intent
import com.example.amuse.ui.home.AvailableGroupsActivity

class DownSwipeAction(val context: Context):CardAction {
    val localStore = LocalEventStore
    override fun swipeActivity(event: Event){
        var weighted_types = ArrayList<Pair<String,Int>>()
        event.types?.forEach { type ->
            weighted_types.add(Pair(type, 4))
        }

        val intent = Intent(context, AvailableGroupsActivity::class.java)
        intent.putExtra("Group-Info-ID", event.id)
        intent.putExtra("Group-Info-StartTime", event.earliest_time)
        intent.putExtra("Group-Info-Endtime", event.latest_time)
        context.startActivity(intent)
        localStore.cardList.removeFirst()

        localStore.PullUntilFull()
    }

}