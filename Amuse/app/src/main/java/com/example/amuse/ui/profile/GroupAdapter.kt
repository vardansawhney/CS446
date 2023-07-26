package com.example.amuse.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.amuse.R
import de.hdodenhof.circleimageview.CircleImageView
import java.lang.Math.min

class GroupAdapter(private val groupsList: ArrayList<Group>)
    : RecyclerView.Adapter<GroupAdapter.GroupViewHolder>(){

    class GroupViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val imageView1 : CircleImageView = itemView. findViewById(R.id.group_pic1)
        val imageView2 : CircleImageView = itemView. findViewById(R.id.group_pic2)
        val imageView3 : CircleImageView = itemView. findViewById(R.id.group_pic3)
        val nameTextView : TextView = itemView.findViewById(R.id.eventName)
        val membersTextView : TextView = itemView.findViewById(R.id.listMembers)
        val groupsTextView : TextView = itemView.findViewById(R.id.availSpotsLeft)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_groups, parent, false)
        return GroupViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val group = groupsList[position]
        for (i in 1..min(3, group.invited.size)){
            if (i == 1)
                holder.imageView1.setImageResource(group.invited[0].imageId)
            else if (i == 2)
                holder.imageView2.setImageResource(group.invited[1].imageId)
            else
                holder.imageView3.setImageResource(group.invited[2].imageId)

        }
        holder.nameTextView.text = group.name
        holder.membersTextView.text = group.invited.joinToString(separator = ", ")
        holder.groupsTextView.text = "Members: " + group.accepted.size.toString() + "/" + group.maxMembers
    }

    override fun getItemCount(): Int {
        return groupsList.size
    }
}