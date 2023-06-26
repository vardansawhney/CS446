package com.example.amuse.ui.profile

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amuse.R
import com.example.amuse.databinding.FragmentFriendsBinding

class FriendsFragment : Fragment() {

    private var _binding: FragmentFriendsBinding? = null
    private lateinit var friendArrayList : ArrayList<Friend>
//
//    // This property is only valid between onCreateView and
//    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFriendsBinding.inflate(layoutInflater)
        val root: View = binding.root

        val imageId = intArrayOf(
            R.drawable.lilo, R.drawable.angel, R.drawable.nani
        )

        val name = arrayOf(
            "Lilo",
            "Angel",
            "Nani"
        )

        val email = arrayOf(
            "lilolovesdancing@gmail.com",
            "angelxoxo@gmail.com",
            "bigsisnani@gmail.com"
        )

        val groups = arrayOf(
            1, 2, 1
        )

        friendArrayList = ArrayList()
        for (i in name.indices){
            val friend = Friend(name[i], email[i], imageId[i], groups[i])
            friendArrayList.add(friend)
        }

//        binding.listview.isClickable = true
        binding.listview.adapter = FriendAdapter(this.context as Activity, this.friendArrayList)
//        binding.listview.setOnItemClickListener{ parent, view, position, id ->
//            val name = name[position]
//            val email = email[position]
//            val imageId = imageId[position]
//            val groups = groups[position]
//
//            val i = Intent()
//
//        }

        return inflater.inflate(R.layout.fragment_friends, container, false)
    }
}