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
import com.example.amuse.ui.profile.Group
import com.example.amuse.ui.profile.GroupAdapter
import android.util.Log

class GroupPageFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var invitedList1: ArrayList<Friend>
    private lateinit var invitedList2: ArrayList<Friend>
    private lateinit var acceptedList1: ArrayList<Friend>
    private lateinit var acceptedList2: ArrayList<Friend>

    private lateinit var invitedList3: ArrayList<Friend>
    private lateinit var acceptedList3: ArrayList<Friend>

    private lateinit var groupsList: ArrayList<Group>
    private lateinit var groupAdapter: GroupAdapter

    private var _binding: FragmentGroupPageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var groupInfo_parsed: ArrayList<String>
//    override fun onCreate(savedInstanceState: Bundle?){
//        super.onCreate(savedInstanceState)
//        val groupInfo = getActivity()?.getIntent()?.getExtras()?.getStringArray("groupInfo")
//        Log.e("bapbadaboobabadumpum", "$groupInfo")
//        for (i in 0..groupInfo!!.size - 1){
//            groupInfo_parsed.add(groupInfo[i])
//        }
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGroupPageBinding.inflate(inflater, container, false)
        val root: View = binding.root

        groupInfo_parsed = ArrayList()


        recyclerView = binding.root.findViewById(R.id.groupView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        groupsList = ArrayList()

        // Group 1
        invitedList1 = ArrayList()
        invitedList1.add(Friend("Lilo", "lilolovesdancing@gmail.com", R.drawable.lilo))
        invitedList1.add(Friend("Nani", "bigsisnani@gmail.com", R.drawable.nani))
        acceptedList1 = ArrayList()
        acceptedList1.add(Friend("Lilo", "lilolovesdancing@gmail.com", R.drawable.lilo))
        groupsList.add(Group("Dinner with Friends", 3, 3, false, invitedList1, acceptedList1))

//        // Group 2
//        invitedList2 = ArrayList()
//        invitedList2.add(Friend("Nani", "bigsisnani@gmail.com", R.drawable.nani))
//        invitedList2.add(Friend("Lilo", "lilolovesdancing@gmail.com", R.drawable.lilo))
//        acceptedList2 = ArrayList()
//        acceptedList2.add(Friend("Nani", "bigsisnani@gmail.com", R.drawable.nani))
//        groupsList.add(Group("Picniiiic", 1, 2, false, invitedList2, acceptedList2))

        // Group 2
        invitedList2 = ArrayList()
        invitedList2.add(Friend("Nani", "bigsisnani@gmail.com", R.drawable.nani, 2))
        invitedList2.add(Friend("Lilo", "lilolovesdancing@gmail.com", R.drawable.lilo, 1))
        acceptedList2 = ArrayList()
        acceptedList2.add(Friend("Nani", "bigsisnani@gmail.com", R.drawable.nani, 2))
        groupsList.add(Group("Picniiiic", 1, 2, false, invitedList2, acceptedList2))

        // New Group
        val groupInfo = activity?.intent?.extras?.getStringArray("groupInfo")
        if(groupInfo != null){
            for (i in 0..groupInfo!!.size - 1){
                groupInfo_parsed.add(groupInfo[i])
            }
            // Group 3
            invitedList3 = ArrayList()
            invitedList3.add(Friend(groupInfo_parsed[4], "angelxoxo@gmail.com", R.drawable.angel, 1))
            acceptedList3 = ArrayList()
            groupsList.add(Group(groupInfo_parsed[0],1,groupInfo_parsed[2].toInt(),groupInfo_parsed[3].toBoolean(), invitedList3, acceptedList3))
        }

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