package com.example.amuse.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.amuse.R

class Preferences : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PreferencesFragment.newInstance())
                .commitNow()
        }


    }
}