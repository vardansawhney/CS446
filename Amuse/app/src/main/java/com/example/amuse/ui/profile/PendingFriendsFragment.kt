package com.example.amuse.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amuse.FirebaseUtils
import com.example.amuse.MainActivity
import com.example.amuse.MainActivity.Companion.myProfileImage
import com.example.amuse.MainActivity.Companion.myUser
import com.example.amuse.R
import com.example.amuse.databinding.FragmentPendingFriendsBinding
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class PendingFriendsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var friendList: ArrayList<Friend>
    private lateinit var friendAdapter: PendingFriendsAdapter

    private var _binding: FragmentPendingFriendsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPendingFriendsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Pending Friends List
        recyclerView = binding.root.findViewById(R.id.pendingView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        friendList = ArrayList()

        runBlocking {
            val user = FirebaseUtils().fireStoreDatabase.collection("Users").document(myUser.email)
            var pending = user.get().await().data?.get("pendingFriends") as ArrayList<String>
            for (friend in pending) {
                val friendUserDocument = FirebaseUtils().fireStoreDatabase.collection("Users").document(friend)
                val friendUser = friendUserDocument.get().await()
                Log.d("Hi", "Added document with ID ${friendUser.data?.get("email").toString()}")
                friendList.add(
                    Friend(
                        friendUser.data?.get("name").toString(),
                        friendUser.data?.get("email").toString(),
                        R.drawable.angel
                    )
                )
            }
        }


//        friendList.add(Friend("Lilo", "lilolovesdancing@gmail.com", R.drawable.lilo))
//        friendList.add(Friend("Angel", "angelxoxo@gmail.com", R.drawable.angel))
//        friendList.add(Friend("Nani", "bigsisnani@gmail.com", R.drawable.nani))

        friendAdapter = PendingFriendsAdapter(this.requireContext(), friendList)
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