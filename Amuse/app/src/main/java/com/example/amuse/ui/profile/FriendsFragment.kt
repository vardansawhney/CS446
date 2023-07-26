package com.example.amuse.ui.profile

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amuse.R
import com.example.amuse.databinding.FragmentFriendsBinding
import androidx.recyclerview.widget.DividerItemDecoration

class FriendsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var friendList: ArrayList<Friend>
    private lateinit var friendAdapter: FriendAdapter

    private var _binding: FragmentFriendsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFriendsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = binding.root.findViewById(R.id.friendsView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        friendList = ArrayList()

        friendList.add(Friend("Lilo", "lilolovesdancing@gmail.com", R.drawable.lilo, 1))
        friendList.add(Friend("Angel", "angelxoxo@gmail.com", R.drawable.angel, 2))
        friendList.add(Friend("Nani", "bigsisnani@gmail.com", R.drawable.nani, 1))

        friendAdapter = FriendAdapter(friendList)
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