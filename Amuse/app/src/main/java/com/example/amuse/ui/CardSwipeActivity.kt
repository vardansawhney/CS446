package com.example.amuse.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.amuse.R
import com.example.amuse.databinding.ActivityCardSwipeBinding
import com.yalantis.library.Koloda

class CardSwipeActivity : AppCompatActivity() {
    private lateinit var cardList: ArrayList<Int>
    private lateinit var swipeAdapter: SwipeAdapter
    private lateinit var koloda: Koloda
    private lateinit var binding: ActivityCardSwipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_swipe)

        binding = ActivityCardSwipeBinding.inflate(layoutInflater)
        koloda = binding.root.findViewById(R.id.koloda)
        cardList = ArrayList()

        swipeAdapter = SwipeAdapter(this, cardList)
        koloda.adapter = swipeAdapter
    }
}