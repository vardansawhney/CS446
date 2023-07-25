package com.example.amuse.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import com.example.amuse.R
import com.example.amuse.ui.groups.GroupPageFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputLayout
import android.content.Intent
import kotlin.math.max
import android.graphics.Color
import android.util.Log

import android.widget.AutoCompleteTextView

import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amuse.FirebaseUtils
import com.example.amuse.MainActivity
import com.example.amuse.databinding.FragmentGroupPageBinding
import com.example.amuse.ui.profile.AvailGroup
import com.example.amuse.ui.profile.Friend
import com.example.amuse.ui.profile.AvailGroupAdapter
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text


class AvailableGroupsActivity : AppCompatActivity() {
    lateinit var create_group_button : MaterialButton
    lateinit var create_group_popup_card : MaterialCardView
    lateinit var checkbox_solo_Adventure : CheckBox
    lateinit var min_ppl : TextInputLayout
    lateinit var max_ppl : TextInputLayout
    lateinit var invited_friends : TextInputLayout
    lateinit var event_title : TextInputLayout

    lateinit var confirm_group_creation : MaterialButton

    lateinit var event_title_text : TextInputEditText
    lateinit var min_ppl_text : TextInputEditText
    lateinit var max_ppl_text : TextInputEditText

    lateinit var friends_text : AutoCompleteTextView
    lateinit var no_groups_text : TextView
    var fruits = arrayOf("Lance","Liam","Lilo","Saksham", "Talha", "Vardhan", "Denis", "Yinuo", "Alan", "Alex", "Alejandro", "Yilin", "Ying", "Varun", "Victor", "Tommy", "Tammy", "D_enis")


    // Making available groups show
    private lateinit var recyclerAvailGroupView: RecyclerView

    private lateinit var invitedList1: ArrayList<Friend>
    private lateinit var invitedList2: ArrayList<Friend>
    private lateinit var acceptedList1: ArrayList<Friend>
    private lateinit var acceptedList2: ArrayList<Friend>
    private lateinit var invitedList3: ArrayList<Friend>
    private lateinit var acceptedList3: ArrayList<Friend>

    private lateinit var availGroupsList: ArrayList<AvailGroup>
    private lateinit var availGroupAdapter: AvailGroupAdapter

    // Dynamic group variables for creations
    private lateinit var availGroup1: ArrayList<Friend>
    private lateinit var dynamicAcceptedList: ArrayList<Friend>
//    private var currentUserFriends: Array<String!>

    private var _binding: FragmentGroupPageBinding? = null

    private val binding get() = _binding!!
    private lateinit var groupInfo_parsed: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available_groups)

        // Mo's work
        groupInfo_parsed = ArrayList()


        recyclerAvailGroupView = findViewById(R.id.recyclerAvailGroupsView)
        recyclerAvailGroupView.setHasFixedSize(true)
        recyclerAvailGroupView.layoutManager = LinearLayoutManager(this)

        availGroupsList = ArrayList()

        // Group 1
        invitedList1 = ArrayList()
        invitedList1.add(Friend("Lilo", "lilolovesdancing@gmail.com", R.drawable.lilo, 1))
        invitedList1.add(Friend("Angel", "angelxoxo@gmail.com", R.drawable.angel, 2))
        invitedList1.add(Friend("Nani", "bigsisnani@gmail.com", R.drawable.nani, 1))
        acceptedList1 = ArrayList()
        acceptedList1.add(Friend("Lilo", "lilolovesdancing@gmail.com", R.drawable.lilo, 1))
        // groupsList.add(AvailGroup("Ohana Outing", 3, 3, false, invitedList1, acceptedList1))
        availGroupsList.add(AvailGroup("What up 1", "6:30pm", "8:30pm", "July 27th",1))
        // data class AvailGroup(var name: String, var minMembers: Int, var maxMembers: Int)

        // Group 2
        invitedList2 = ArrayList()
        invitedList2.add(Friend("Nani", "bigsisnani@gmail.com", R.drawable.nani, 2))
        invitedList2.add(Friend("Lilo", "lilolovesdancing@gmail.com", R.drawable.lilo, 1))
        acceptedList2 = ArrayList()
        acceptedList2.add(Friend("Nani", "bigsisnani@gmail.com", R.drawable.nani, 2))
//        groupsList.add(AvailGroup("Picniiiic", 1, 2, false, invitedList2, acceptedList2))
        availGroupsList.add(AvailGroup("What up 2", "2:30pm", "3:30pm", "July 27th", 3))

        // Displaying groups
//        var numGroups = FirebaseUtils().fireStoreDatabase.collection("Groups").count().toString().toInt()
//        Log.e("HEY! HERE", "$numGroups")
//        if (numGroups == 0) {
//            // Display no available groups
//            no_groups_text = findViewById<TextView>(R.id.no_groups_text)
//        } else {
            // Visually displaying the actual groups (DYNAMICALLY)

            // Grabbing the current user
            // val currentUser = ?

            // Grabbing friends from currently logged into user
            // val currentUserFriends = FirebaseUtils().fireStoreDatabase.collection("Users").document("denis@gmail.com (CURRENT USER)").get()


            // Grabbing friends from currently logged into user (REPLACE "denis@gmail.com" WITH CURRENT USER)
//            FirebaseUtils().fireStoreDatabase.collection("Users").document("denis@gmail.com").get()
//                .addOnSuccessListener { documentSnapshot ->
//                    if (documentSnapshot.exists()) {
//                        val friendsDataMap = documentSnapshot.data
//
//                        // Check if friendsDataMap is not null to avoid null pointer exceptions
//                        if (friendsDataMap != null) {
//
//                            // Now you can dynamically populate currentUserFriends with values from the database
//                            currentUserFriends = friendsDataMap.keys.toTypedArray()
//
//                            // Perform any additional operations with the dynamically populated currentUserFriends array
//                            for (friend in currentUserFriends) {
//                                val fieldValue = friendsDataMap[friend]
//                                Log.d("SUCCESS", "The value of '$friend' in the document is: $fieldValue")
//                            }
//                        }
//                    } else {
//                        Log.d("FAILURE", "Document not found!")
//                    }
//                }
//                .addOnFailureListener { exception ->
//                    Log.e("ERROR", "Error getting document: $exception")
//                }

            // Info from event card that was swiped right one
//            val swipeRightEventID = ?
//            // Checkin if startTime or EndTime is null!!!
//            val swipeRightStartTime = ?
//            val swipeRightEndTime = ?
//
//            // For each of my friends, check if they started a group for the card I swiped right on
//            for (friend in currentUserFriends) {
//                // Grabbing each group that this friend is in
//                // val friendsGroups = FirebaseUtils().fireStoreDatabase.collection("Users").document(friend).get("groups"); // syntax issues
//
//                FirebaseUtils().fireStoreDatabase.collection("Users").document(friend).get()
//                    .addOnSuccessListener { documentSnapshot ->
//                        if (documentSnapshot.exists()) {
//                            val groups = documentSnapshot.get("groups") as? Array<String>
//                            if (groups != null) {
//                                // Check each "group" and check the following: eventID + creatorID + startTime + endTime
//                                for (group in groups) {
//                                    if (friend == group.creatorID && swipeRightEventID == group.eventID && swipeRightStartTime == group.startTime && swipeRightEndTime == group.endTime) {
//                                        if (group.availableSpots >= 1) {
//                                            // Add this group to availGroupsList in order for it to be printed
//                                            availGroupsList.add(AvailGroup(group.name, group.startTime, group.endTime, group.date, group.availableSpots))
//                                        }
//                                    }
//                                }
//                            } else {
//                                Log.d("EMPTY", "No 'groups' field found or the value is not an Array<String>")
//                            }
//                        } else {
//                            Log.d("FAILURE", "Document not found!")
//                        }
//                    }
//                    .addOnFailureListener { exception ->
//                        Log.e("ERROR", "Error getting document: $exception")
//                    }

                // Check each "group" and check the following: eventID + creatorID + startTime + endTime
//                for (group in friendsGroups) {
//                    if (friend == group.creatorID && swipeRightEventID == group.eventID && swipeRightStartTime == group.startTime && swipeRightEndTime == group.endTime) {
//                        if (group.availableSpots >= 1) {
//                            // Add this group to availGroupsList in order for it to be printed
//                            availGroupsList.add(AvailGroup(group.name, group.startTime, group.endTime, group.date, group.availableSpots))
//                        }
//                    }
//                }
//            }

            // Display the groups
            // groupAdapter = GroupAdapter(groupsList)
            availGroupAdapter = AvailGroupAdapter(availGroupsList)
            recyclerAvailGroupView.adapter = availGroupAdapter
            recyclerAvailGroupView.addItemDecoration(
                DividerItemDecoration(
                    this,
                    DividerItemDecoration.VERTICAL
                )
            )





        // Creating a group flow/code
        create_group_button = findViewById<MaterialButton>(R.id.create_group_button)
        create_group_button.setOnClickListener(create_button_listener)

        create_group_popup_card = findViewById<MaterialCardView>(R.id.create_group_popup_card)

        checkbox_solo_Adventure = findViewById<CheckBox>(R.id.solo_checkbox)
        checkbox_solo_Adventure.setOnClickListener(solo_checkbox_listener)

        min_ppl = findViewById<TextInputLayout>(R.id.min_ppl)
        min_ppl_text = findViewById<TextInputEditText>(R.id.min_ppl_text)

        max_ppl = findViewById<TextInputLayout>(R.id.max_ppl)
        max_ppl_text = findViewById<TextInputEditText>(R.id.max_ppl_text)

        event_title = findViewById<TextInputLayout>(R.id.event_title)
        event_title_text = findViewById<TextInputEditText>(R.id.event_title_text)

        invited_friends = findViewById<TextInputLayout>(R.id.invite_friends)
        friends_text = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)

        confirm_group_creation = findViewById<MaterialButton>(R.id.confirm_new_group_settings)
        confirm_group_creation.setOnClickListener(confirm_group_listener)


        val adapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.select_dialog_item, fruits)
        val actv = findViewById<View>(R.id.autoCompleteTextView) as AutoCompleteTextView
        actv.threshold = 1
        actv.setAdapter(adapter)

        val available_group_info_id = intent.getStringExtra("Group-Info-ID")
        val available_group_info_start_time = intent.getStringExtra("Group-Info-StartTime")
        val available_group_info_end_time = intent.getStringExtra("Group-Info-Endtime")
        if(available_group_info_end_time !=null && available_group_info_id != null && available_group_info_start_time != null){
            Log.d("groupinfo", available_group_info_id)
            Log.d("groupinfo", available_group_info_start_time)
            Log.d("groupinfo", available_group_info_end_time)
        }
    }

    private val create_button_listener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.create_group_button -> {
                create_group_popup_card.visibility = View.VISIBLE;
            }
        }
    }

    private val solo_checkbox_listener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.solo_checkbox -> {
                if(checkbox_solo_Adventure.isChecked){
                    min_ppl.isEnabled = false;
                    max_ppl.isEnabled = false;
                    invited_friends.isEnabled = false;
                }else{
                    min_ppl.isEnabled = true;
                    max_ppl.isEnabled = true;
                    invited_friends.isEnabled = true;
                }
            }
        }
    }

    private val confirm_group_listener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.confirm_new_group_settings -> {
                val group_to_add = arrayOf<String>(event_title_text.text.toString(), min_ppl_text.text.toString(), max_ppl_text.text.toString(), checkbox_solo_Adventure.isChecked.toString() , friends_text.text.toString())
                val intent = Intent(this, MainActivity::class.java);
//                setContentView(R.layout.fragment_group_page)
                startActivity(intent.putExtra("groupInfo", group_to_add));
                create_group_popup_card.visibility = View.GONE;
                no_groups_text.visibility = View.GONE;
            }
        }
    }
}