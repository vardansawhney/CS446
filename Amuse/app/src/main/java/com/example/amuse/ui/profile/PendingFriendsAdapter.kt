package com.example.amuse.ui.profile

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.amuse.FirebaseUtils
import com.example.amuse.MainActivity
import com.example.amuse.MainActivity.Companion.myUser
import com.example.amuse.R
import com.example.amuse.databinding.FragmentPendingFriendsBinding
import com.google.firebase.firestore.FieldValue
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await


class PendingFriendsAdapter(private val context: Context, private val friendList: ArrayList<Friend>)
    : RecyclerView.Adapter<PendingFriendsAdapter.PendingFriendViewHolder>(){

    class PendingFriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView : CircleImageView = itemView.findViewById(R.id.friend_profile_pic)
        val nameTextView : TextView = itemView.findViewById(R.id.friendName)
        val emailTextView : TextView = itemView.findViewById(R.id.email)
        val acceptButton : Button = itemView.findViewById(R.id.accept_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingFriendViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_pending_friend, parent, false)
        return PendingFriendViewHolder(view)
    }

    override fun onBindViewHolder(holder: PendingFriendViewHolder, position: Int) {
        val friend = friendList[position]
        holder.nameTextView.text = friend.name
        holder.emailTextView.text = friend.email
        holder.imageView.setImageResource(friend.imageId)
//        Glide.with(context).load(friend.imageId).into(holder.imageView)
        holder.acceptButton.setOnClickListener {
            val myDocument =
                FirebaseUtils().fireStoreDatabase.collection("Users").document(myUser.email)
            val friendDocument =
                FirebaseUtils().fireStoreDatabase.collection("Users").document(friend.email)
            runBlocking {
                val user = myDocument.get().await()
                if (user.exists()) {
                    myDocument.update("friends", FieldValue.arrayUnion(friend.email))
                    friendDocument.update("friends", FieldValue.arrayUnion(myUser.email))
                    friendList.removeAt(position)
                }
            }
            val refresh = Intent(context, FragmentPendingFriendsBinding::class.java)
            startActivity(context, refresh, null)
        }
    }

    override fun getItemCount(): Int {
        return friendList.size
    }

}