package com.example.amuse.ui.dashboard

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

//        var cardView: CardView =
//            inflater.inflate(R.layout.fragment_dashboard, container, false) as CardView

        //        val expandBtn = cardView.findViewById(R.id.expandBtn) as Button
        //        var expandableLayout = cardView.findViewById(R.id.expandableLayout) as LinearLayout

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val stupidButton: Button = binding.expandBtn
        val stupidExpandableLayout: LinearLayout = binding.expandableLayout
        val stupidCardView: CardView = binding.cardView

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

//        var expandBtn: Button? = getView()?.findViewById(R.id.expandBtn)


//        expandableLayout = binding.expandableLayout
//        cardView = binding.cardView
//        expandBtn = binding.Button

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
        if(event_id!=null && event_address!=null && event_description!=null){
            Log.d("tag", "got the data to liked groups...probably")
        }
        stupidButton?.setOnClickListener {
            if (stupidExpandableLayout.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(stupidCardView, AutoTransition())
                stupidExpandableLayout.visibility = View.VISIBLE
                stupidButton.text = "COLLAPSE"
            } else {
                TransitionManager.beginDelayedTransition(stupidCardView, AutoTransition())
                stupidExpandableLayout.visibility = View.GONE
                stupidButton.text = "EXPAND"
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}