package com.example.amuse.ui.groups

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amuse.R
import com.example.amuse.databinding.FragmentGroupPageBinding
import com.example.amuse.ui.profile.Friend
import com.example.amuse.ui.profile.FriendAdapter
import com.example.amuse.ui.profile.Group
import com.example.amuse.ui.profile.GroupAdapter

class GroupPageFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var invitedList1: ArrayList<Friend>
    private lateinit var invitedList2: ArrayList<Friend>
    private lateinit var acceptedList1: ArrayList<Friend>
    private lateinit var acceptedList2: ArrayList<Friend>
    private lateinit var groupsList: ArrayList<Group>
    private lateinit var groupAdapter: GroupAdapter

    private var _binding: FragmentGroupPageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGroupPageBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = binding.root.findViewById(R.id.groupView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        groupsList = ArrayList()

        // Group 1
        invitedList1 = ArrayList()
        invitedList1.add(Friend("Lilo", "lilolovesdancing@gmail.com", R.drawable.lilo, 1))
        invitedList1.add(Friend("Angel", "angelxoxo@gmail.com", R.drawable.angel, 2))
        invitedList1.add(Friend("Nani", "bigsisnani@gmail.com", R.drawable.nani, 1))
        acceptedList1 = ArrayList()
        acceptedList1.add(Friend("Lilo", "lilolovesdancing@gmail.com", R.drawable.lilo, 1))
        groupsList.add(Group("Ohana Outing", 3, 3, false, invitedList1, acceptedList1))

        // Group 2
        invitedList2 = ArrayList()
        invitedList2.add(Friend("Angel", "angelxoxo@gmail.com", R.drawable.angel, 2))
        acceptedList2 = ArrayList()
        acceptedList2.add(Friend("Angel", "angelxoxo@gmail.com", R.drawable.angel, 2))
        groupsList.add(Group("Park Date", 1, 1, false, invitedList2, acceptedList2))

        groupAdapter = GroupAdapter(groupsList)
        recyclerView.adapter = groupAdapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        return root
    }
}