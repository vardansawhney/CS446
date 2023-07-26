package com.example.amuse.ui.profile

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.amuse.R
import de.hdodenhof.circleimageview.CircleImageView

class FriendAdapter(private val friendList: ArrayList<Friend>)
    : RecyclerView.Adapter<FriendAdapter.FriendViewHolder>(){

    class FriendViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val imageView : CircleImageView = itemView. findViewById(R.id.friend_profile_pic)
        val nameTextView : TextView = itemView.findViewById(R.id.friendName)
        val emailTextView : TextView = itemView.findViewById(R.id.email)
        val groupsTextView : TextView = itemView.findViewById(R.id.numGroups)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_friend, parent, false)
        return FriendViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val friend = friendList[position]
        holder.imageView.setImageResource(friend.imageId)
        holder.nameTextView.text = friend.name
        holder.emailTextView.text = friend.email
        holder.groupsTextView.text = friend.groups.toString()
    }

    override fun getItemCount(): Int {
        return friendList.size
    }

}