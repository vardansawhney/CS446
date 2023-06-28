package com.example.amuse.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.amuse.MainActivity
import com.example.amuse.R
import com.example.amuse.databinding.FragmentDashboardBinding
import com.example.amuse.ui.home.AvailableGroupsActivity

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var test_create_group_button : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        test_create_group_button = root.findViewById<Button>(R.id.test_create)
        test_create_group_button.setOnClickListener(test_create_group_button_listener)

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }

    private val test_create_group_button_listener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.test_create -> {
                val intent = Intent(activity, AvailableGroupsActivity::class.java);
                startActivity(intent);
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}