package com.example.amuse.ui.dashboard

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.amuse.Event
import com.example.amuse.FirebaseUtils
import com.example.amuse.R
import com.example.amuse.ui.profile.AvailGroupAdapter
import com.google.api.Distribution.BucketOptions.Linear
import com.google.firebase.firestore.FieldValue
import java.util.ArrayList

class LikedEventAdaptor(private val availLikedEventsList: ArrayList<LikedEvent>)
    : RecyclerView.Adapter<LikedEventAdaptor.LikedEventViewHolder>(){

    class LikedEventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val nameTextView : TextView = itemView.findViewById(R.id.likedEventName)
        val descriptionTextView : TextView = itemView.findViewById(R.id.description)
        val addressTextView : TextView = itemView.findViewById(R.id.addy)
        val ratingTextView : TextView = itemView.findViewById(R.id.rating)
        val goToCalButtonView: Button = itemView.findViewById(R.id.goToCal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikedEventViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.each_liked_event, parent, false)
        return LikedEventAdaptor.LikedEventViewHolder(view).apply {
            goToCalButtonView.setOnClickListener {
                // Start the activity here
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.calendar"))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                parent.context.startActivity(intent)
            }
        }
    }

    override fun onBindViewHolder(holder: LikedEventViewHolder, position: Int) {
        val likedEvent = availLikedEventsList[position]

//        Log.e("Group Adaptor", "GroupAdaptor We got here")
        holder.nameTextView.text = likedEvent.name
        holder.descriptionTextView.text = likedEvent.description
        holder.addressTextView.text = likedEvent.address
        holder.ratingTextView.text = likedEvent.rating.toString()

        var toCalId = View.generateViewId()
        Log.e("new id", "${toCalId}")
        holder.goToCalButtonView.id = toCalId
//        holder.goToCalButtonView.setOnClickListener{
////            Log.e("Calendar Tag", "Tapped add to calendar")
//            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.calendar"))
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
////            Log.e("Calendar Tag", "About to start the activity")
//
//            startActivity(intent)
////            Log.e("Calendar Tag", "Started the activity")
//        }
    }

    override fun getItemCount(): Int {
        return availLikedEventsList.size
    }

}
