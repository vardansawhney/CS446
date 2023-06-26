package com.example.amuse.ui.profile

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.amuse.R

class FriendAdapter(private val context: Activity, private val arrayList: ArrayList<Friend>) :
    ArrayAdapter<Friend>(context, R.layout.list_friend){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.list_friend, null)

        val imageView : ImageView = view.findViewById(R.id.friend_profile_pic)
        val username : TextView = view.findViewById(R.id.friendName)
        val email : TextView = view.findViewById(R.id.email)
        val groups: TextView = view.findViewById(R.id.groups)
        val numGroups : TextView = view.findViewById(R.id.numGroups)

        imageView.setImageResource(arrayList[position].imageId)
        username.text = arrayList[position].name
        email.text = arrayList[position].email
        numGroups.text = arrayList[position].groups.toString()

        return super.getView(position, convertView, parent)
    }

}