package com.example.amuse.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.example.amuse.OpenCardActivity
import com.example.amuse.R
import com.example.amuse.databinding.FragmentProfileBinding
import com.example.amuse.ui.CardAdapter
import com.example.amuse.ui.MainViewModel
import com.example.amuse.ui.PreferenceUpdateActivity
import com.example.amuse.ui.PreferencesFragment
import com.example.amuse.ui.dashboard.DashboardViewModel
import com.example.amuse.ui.home.Card
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout


class ProfileFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: VP2Adapter
    private lateinit var preferencesViewModal: MainViewModel


    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var SettingsTab: PreferenceUpdateActivity
    private var _binding: FragmentProfileBinding? = null



    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var colorCode:Int = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val settingButton: Button = binding.SettingsButton

        tabLayout = binding.constraintLayout.findViewById(R.id.tabLayout)
        viewPager2 = binding.constraintLayout.findViewById(R.id.viewPager2)
        adapter = VP2Adapter(activity)

        tabLayout.addTab(tabLayout.newTab().setText("Friends"))
        tabLayout.addTab(tabLayout.newTab().setText("Groups"))
//        tabLayout.addTab(tabLayout.newTab().setText("Settings"))

        viewPager2.adapter = adapter

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager2.currentItem = tab.position
//                    Log.d("TAG", tab.position.toString())

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })


        settingButton?.setOnClickListener {
            val preferencesFragment = PreferencesFragment()
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

            // Replace the current fragment with the target fragment
            fragmentTransaction.replace(R.id.container, preferencesFragment)

            // Optionally, you can add the transaction to the back stack, so the user can navigate back to the source fragment
            // fragmentTransaction.addToBackStack(null)

            fragmentTransaction.commit()

        }
        return root
    }


//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}