package com.example.amuse.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amuse.LocalEventStore
import com.example.amuse.R
import com.example.amuse.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    // Variables added from XML
//    private lateinit var expandBtn: Button
//    Button expandBtn;
    private lateinit var expandableLayout: LinearLayout
    private lateinit var cardView: CardView
    // Variables added from XML
    private var _binding: FragmentDashboardBinding? = null

    private lateinit var recyclerLikedEventView: RecyclerView
    private lateinit var availLikedEventsList: ArrayList<LikedEvent>
    private lateinit var likedEventAdaptor: LikedEventAdaptor

    private lateinit var toCal: Button

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
Log.d("tag", getActivity().toString())
//        var cardView: CardView =
//            inflater.inflate(R.layout.fragment_dashboard, container, false) as CardView

        //        val expandBtn = cardView.findViewById(R.id.expandBtn) as Button
        //        var expandableLayout = cardView.findViewById(R.id.expandableLayout) as LinearLayout

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root



//        val stupidButton: Button = binding.expandBtn
//        val stupidExpandableLayout: LinearLayout = binding.expandableLayout
//        val stupidCardView: CardView = binding.cardView
//        val toCal = binding.goToCal

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

//        var expandBtn: Button? = getView()?.findViewById(R.id.expandBtn)


//        expandableLayout = binding.expandableLayout
//        cardView = binding.cardView
//        expandBtn = binding.Button

        // Obtaining events
        val localStore = LocalEventStore
        val events = localStore.likedEvents

        recyclerLikedEventView = root.findViewById(R.id.recyclerLikedEventsView)
        Log.e("Group matching 2", "page loaded")
        recyclerLikedEventView.setHasFixedSize(true)
        Log.e("Group matching 3", "page loaded")
        recyclerLikedEventView.layoutManager = LinearLayoutManager(this.context)
        Log.e("Group matching 4", "page loaded")
        availLikedEventsList = ArrayList()



        for (event in events) {
            Log.e("Group matching 4", "${event.name}")
            availLikedEventsList.add(
                LikedEvent(
                    event.name,
                    event.description,
                    event.address,
                    event.price_level,
                    event.rating,
                )
            )

            // Display the liked events
            Log.e("Liked events show!", "availLikedEventsList!: $availLikedEventsList")
            likedEventAdaptor = LikedEventAdaptor(availLikedEventsList)
            recyclerLikedEventView.adapter = likedEventAdaptor
            recyclerLikedEventView.addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }


        val event_id = activity?.intent?.getStringExtra("Info-ID")
        val event_starttime = activity?.intent?.getStringExtra("Info-StartTime")
        val event_endtime = activity?.intent?.getStringExtra("Info-Endtime")
        val event_source_id = activity?.intent?.getStringExtra("Info-source_id")
        val event_source = activity?.intent?.getStringExtra("Info-source")
        val event_description = activity?.intent?.getStringExtra("Info-description")
        val event_address = activity?.intent?.getStringExtra("Info-address")
        val event_city = activity?.intent?.getStringExtra("Info-city")
        val event_price_level = activity?.intent?.getStringExtra("Info-price_level")
        val event_rating = activity?.intent?.getStringExtra("Info-rating")
        if(event_id!=null){
            Log.d("tag", "got the data to liked groups...probably")
        }
//        stupidButton?.setOnClickListener {
//            if (stupidExpandableLayout.visibility == View.GONE) {
//                TransitionManager.beginDelayedTransition(stupidCardView, AutoTransition())
//                stupidExpandableLayout.visibility = View.VISIBLE
//                stupidButton.text = "COLLAPSE"
//            } else {
//                TransitionManager.beginDelayedTransition(stupidCardView, AutoTransition())
//                stupidExpandableLayout.visibility = View.GONE
//                stupidButton.text = "EXPAND"
//            }
//        }

        // https://www.youtube.com/watch?v=ZT4DmaVWSaY
        // https://www.youtube.com/watch?v=0IIueHddQDE
        // https://developer.android.com/training/basics/intents/sending

//        toCal = root.findViewById(R.id.addToCal)
//        Log.e("Calendar Tag", "BEFORE CLICKIE")
//        toCal.setOnClickListener {
////            val calendarUrl = "https://calendar.google.com"
////            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(calendarUrl))
////            Log.d("tag", "cal.endar button pressed")
////            startActivity(intent)
////            val i = PackageManager.getLaunchIntentForPackage("com.google.calendar")
//////            val i = PackageManager.
////            // add data, emails, to clipboard
////            if (i != null) {
////                startActivity(i)
////            } else {
//
//
//            Log.e("Calendar Tag", "Tapped add to calendar")
//                var intent2 = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.calendar"))
//                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
////                intent.setData(Uri.parse("market://details?id=" + "com.google.calender"))
//            Log.e("Calendar Tag", "About to start the activity")
//                startActivity(intent2)
//            Log.e("Calendar Tag", "Started the activity")
////            }
//        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}