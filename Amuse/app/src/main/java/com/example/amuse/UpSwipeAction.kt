package com.example.amuse

import android.content.Context
import android.content.Intent
import com.example.amuse.ui.home.AvailableGroupsActivity

class UpSwipeAction(val context: Context):CardAction {
    override fun swipeActivity(event: Event){

        val intent = Intent(context, AvailableGroupsActivity::class.java)
        intent.putExtra("Group-Info-ID", event.id)
        intent.putExtra("Group-Info-StartTime", event.earliest_time)
        intent.putExtra("Group-Info-Endtime", event.latest_time)
        context.startActivity(intent)
    }

}