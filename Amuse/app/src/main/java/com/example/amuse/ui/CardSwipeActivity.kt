package com.example.amuse.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.amuse.R

class CardSwipeActivity : AppCompatActivity() {
    private lateinit var cardList: ArrayList<Int>
    private lateinit var swipeAdapter: SwipeAdapter
    private lateinit var koloda: Koloda
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_swipe)

        binding = ActivityCardSwipeBinding.inflate(layoutInflater)
        koloda = binding.root.findViewById(R.id.koloda)
        cardList = ArrayList()

        swipeAdapter = SwipeAdapter(cardList)
        koloda.setAdapter(adapter)
    }
}