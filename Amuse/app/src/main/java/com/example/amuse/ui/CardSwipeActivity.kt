package com.example.amuse.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.amuse.R
import com.example.amuse.databinding.ActivityCardSwipeBinding
import com.example.amuse.ui.home.Card
import com.yuyakaido.android.cardstackview.CardStackView

class CardSwipeActivity : AppCompatActivity() {
    private lateinit var cardList: ArrayList<Card>
    private lateinit var cardAdapter: CardAdapter
    private lateinit var cardStackView: CardStackView
    private lateinit var binding: ActivityCardSwipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_swipe)

        binding = ActivityCardSwipeBinding.inflate(layoutInflater)
        cardStackView = binding.root.findViewById(R.id.card_stack)
        cardList = ArrayList()

        // Card 1
        cardList.add(Card("Hiking", "Laurel Trail (2.1 km)", "★★★☆☆", "$", "2 groups", "100 interested", R.drawable.card1_media))

        // Card 2
        cardList.add(Card("Fancy Dinner", "Shinwa (1.8 km)", "★★★★★", "$$", "1 groups", "2 interested", R.drawable.card2_media))

        // Card 3
        cardList.add(Card("Clubbing", "Allure (2 km)", "★☆☆☆☆", "$$$$", "0 groups", "0 interested", R.drawable.card3_media))

        cardAdapter = CardAdapter(cardList)
        cardStackView.adapter = cardAdapter
    }
}