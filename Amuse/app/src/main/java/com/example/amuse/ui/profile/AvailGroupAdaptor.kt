package com.example.amuse.ui.profile;

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View;
import android.view.View.generateViewId
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView;
import com.example.amuse.FirebaseUtils
import com.example.amuse.MainActivity.Companion.myUser
import com.example.amuse.R
import com.google.firebase.firestore.FieldValue
import de.hdodenhof.circleimageview.CircleImageView
import java.util.ArrayList;

class AvailGroupAdapter(private val availGroupsList: ArrayList<AvailGroup>)
    : RecyclerView.Adapter<AvailGroupAdapter.AvailGroupViewHolder>(){

    class AvailGroupViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val imageView1 : CircleImageView = itemView.findViewById(R.id.group_pic1)
        val imageView2 : CircleImageView = itemView.findViewById(R.id.group_pic2)
        val imageView3 : CircleImageView = itemView.findViewById(R.id.group_pic3)
        val nameTextView : TextView = itemView.findViewById(R.id.availGroupName)
        val displayTimesTextView : TextView = itemView.findViewById(R.id.dateDisplay)
        val availSpotsLeftTextView : TextView = itemView.findViewById(R.id.availSpotsLeft)
        val joinButtonView: Button = itemView.findViewById(R.id.joinButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvailGroupViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.avail_group_item, parent, false)
        return AvailGroupViewHolder(view)
    }

    override fun onBindViewHolder(holder: AvailGroupViewHolder, position: Int) {
        val availGroup = availGroupsList[position]
        // Displaying the group image
//        holder.imageView1.setImageResource(availGroup.image)
//        for (i in 1..Math.min(3, availGroup.invited.size)){
//            if (i == 1)
//                holder.imageView1.setImageResource(availGroup.invited[0].imageId)
//            else if (i == 2)
//                holder.imageView2.setImageResource(availGroup.invited[1].imageId)
//            else
//                holder.imageView3.setImageResource(availGroup.invited[2].imageId)
//
//        }
        Log.e("Group Adaptor", "GroupAdaptor We got here")
        // Displaying the group name
        holder.nameTextView.text = availGroup.name
        holder.displayTimesTextView.text = availGroup.date
        holder.availSpotsLeftTextView.text = "Spots left: " + availGroup.availSpotsLeft
        var new_id = generateViewId()
        Log.e("new id", "${new_id}")
        holder.joinButtonView.id = new_id
        holder.joinButtonView.setOnClickListener{
            // Access that databaseeee babyyyyyy (accessing the same group and making edits to this group!)
            Log.e("Group AQUIRED", "DYNAMIC NAME OBTAINED: ${availGroup.name}")

            // Add currentUser's ID (email) to the members of this group!
            // UPDATE WITH CURRENT USER INSTEAD OF DENIS@GMAIL.COM
            FirebaseUtils().fireStoreDatabase.collection("Groups")
                .document("${availGroup.name}")
                .update("members", FieldValue.arrayUnion(myUser.email))

            // Decrease available spots in DB
            holder.availSpotsLeftTextView.text = "Spots left: " + (availGroup.availSpotsLeft - 1)

            // UI CHANGES! Change button colour to black and text!
            Log.e("Button Action", "Button is clicked! id: ${holder.joinButtonView}")
            holder.joinButtonView.setBackgroundColor(Color.BLACK);
            holder.joinButtonView.text = "JOINED"
            holder.joinButtonView.textSize = 9F
        }
    }

    override fun getItemCount(): Int {
        return availGroupsList.size
    }
}
