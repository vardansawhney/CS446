package com.example.amuse.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class VP2Adapter(activity: FragmentActivity?) : FragmentStateAdapter(activity!!) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        if(position == 0)
            return FriendsFragment()
        else if(position == 1)
            return PendingFriendsFragment()
        else
            return SettingsFragment()
    }
}