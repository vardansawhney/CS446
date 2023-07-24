package com.example.amuse.ui.profile;

import android.view.LayoutInflater
import android.view.View;
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView;
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView;
import com.example.amuse.R
import de.hdodenhof.circleimageview.CircleImageView
import java.util.ArrayList;

class AvailGroupAdapter(private val availGroupsList: ArrayList<AvailGroup>)
    : RecyclerView.Adapter<AvailGroupAdapter.AvailGroupViewHolder>(){

    class AvailGroupViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val imageView1 : CircleImageView = itemView.findViewById(R.id.group_pic1)
        val imageView2 : CircleImageView = itemView.findViewById(R.id.group_pic2)
        val imageView3 : CircleImageView = itemView.findViewById(R.id.group_pic3)
        val nameTextView : TextView = itemView.findViewById(R.id.availGroupName)
        val displayTimesTextView : TextView = itemView.findViewById(R.id.hiddenMemberNames)
        val groupsTextView : TextView = itemView.findViewById(R.id.numMembers)
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
        // Displaying the group name
        holder.nameTextView.text = availGroup.name
        holder.displayTimesTextView.text = availGroup.date + " at " + availGroup.startTime + " - " + availGroup.endTime
        holder.groupsTextView.text = "Members: " + availGroup.accepted.size.toString() + "/" + availGroup.maxMembers
        // Do something with button here?!?!?!
    }

    override fun getItemCount(): Int {
        return availGroupsList.size
    }
}
