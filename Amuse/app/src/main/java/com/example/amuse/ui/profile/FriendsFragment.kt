package com.example.amuse.ui.profile

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amuse.FirebaseUtils
import com.example.amuse.MainActivity.Companion.myProfileImage
import com.example.amuse.MainActivity.Companion.myUser
import com.example.amuse.R
import com.example.amuse.TAG
import com.example.amuse.User
import com.example.amuse.databinding.FragmentFriendsBinding
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import java.io.File

class FriendsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var friendList: ArrayList<Friend>
    private lateinit var friendAdapter: FriendAdapter
    private lateinit var addButton: Button
    private lateinit var builder: AlertDialog.Builder

    private var _binding: FragmentFriendsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFriendsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Add Friend
        addButton = binding.root.findViewById(R.id.add)
        builder = AlertDialog.Builder(this.requireContext())
        addButton.setOnClickListener(){
            val showPopUp = AddFriendFragment()
            showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
        }

        //Friends List
        recyclerView = binding.root.findViewById(R.id.friendsView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        friendList = ArrayList()

        runBlocking {
            val user = FirebaseUtils().fireStoreDatabase.collection("Users").document(myUser.email)
            var friends = user.get().await().data?.get("friends") as ArrayList<String>
            for(friend in friends){
                val friendUserDocument = FirebaseUtils().fireStoreDatabase.collection("Users").document(friend)
                val friendUser = friendUserDocument.get().await()
                Log.d("Hi", "Added document with ID ${friendUser.data?.get("email").toString()}")
                friendList.add(Friend(friendUser.data?.get("name").toString(), friendUser.data?.get("email").toString(), R.drawable.lilo))
//                runBlocking {
//                    val storageRef = FirebaseUtils().fireStoreStorage.reference.child(
//                        "${
//                            friendUser.data?.get("picture").toString()
//                        }"
//                    )
//                    storageRef.downloadUrl.addOnSuccessListener {
//                        friendList.add(
//                            Friend(
//                                friendUser.data?.get("name").toString(),
//                                friendUser.data?.get("email").toString(),
//                                it.toString()
//                            )
//                        )
//                    }
//                }
////                friendList.add(Friend(friendUser.data?.get("name").toString(), friendUser.data?.get("email").toString(), myProfileImage))
//                val storageRef = FirebaseUtils().fireStoreStorage.reference.child("${friendUser.data?.get("picture").toString()}")
//                val localFile = File.createTempFile("tempfile", "jpg")
//                storageRef.getFile(localFile).await()
//                val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            }
//            mAdapter = ImageAdapter(this@ImagesActivity, mUploads)
//            mRecyclerView.setAdapter(mAdapter)
//            mProgressCircle.setVisibility(View.INVISIBLE)

//            friendList.add(Friend("Lilo", "lilolovesdancing@gmail.com", R.drawable.lilo))
//            friendList.add(Friend("Angel", "angelxoxo@gmail.com", R.drawable.angel))
//            friendList.add(Friend("Nani", "bigsisnani@gmail.com", R.drawable.nani))
        }

        friendAdapter = FriendAdapter(this.requireContext(), friendList)
        recyclerView.adapter = friendAdapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        return root
    }
}