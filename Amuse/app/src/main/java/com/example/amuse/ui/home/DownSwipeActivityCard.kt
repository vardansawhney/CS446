package com.example.amuse.ui.home

import android.content.Intent

class DownSwipeActivityCard: ActivityCard{

    public override fun swipeActivity(intent : Intent) : Boolean{

        return false;
    }
}