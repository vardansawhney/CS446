package com.example.amuse

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class OpenCardActivity : AppCompatActivity() {
    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_card)

        var cardID = intent.getStringExtra("cardID")

        Log.e("TAG", "Id of card is ====> $cardID")

        when (cardID) {
            "card1" -> {
                Log.e("TAG", "- 1 -")
                findViewById<ImageView>(R.id.OuterCardMedia).setImageResource(R.drawable.card1_media)
                findViewById<TextView>(R.id.OuterCardTitle).text = "Hiking"
                findViewById<TextView>(R.id.OuterCardLocation).text = "Laurel Trail (2.1 km)"
                findViewById<TextView>(R.id.OuterCardStars).text = "★★★☆☆"
                findViewById<TextView>(R.id.OuterCardPrice).text = "$"
                findViewById<TextView>(R.id.OuterCardGroups).text = "2 groups"
                findViewById<TextView>(R.id.OuterCardLikes).text = "100 interested"
            }

            "card2" -> {
                Log.e("TAG", "- 2 -")
                findViewById<ImageView>(R.id.OuterCardMedia).setImageResource(R.drawable.card2_media)
                findViewById<TextView>(R.id.OuterCardTitle).text = "Fancy Dinner"
                findViewById<TextView>(R.id.OuterCardLocation).text = "Shinwa (1.8 km)"
                findViewById<TextView>(R.id.OuterCardStars).text = "★★★★★"
                findViewById<TextView>(R.id.OuterCardPrice).text = "$$"
                findViewById<TextView>(R.id.OuterCardGroups).text = "1 groups"
                findViewById<TextView>(R.id.OuterCardLikes).text = "2 interested"
            }

            "card3" -> {
                Log.e("TAG", "- 3 -")
                findViewById<ImageView>(R.id.OuterCardMedia).setImageResource(R.drawable.card3_media)
                findViewById<TextView>(R.id.OuterCardTitle).text = "Clubbing"
                findViewById<TextView>(R.id.OuterCardLocation).text = "Allure (2 km)"
                findViewById<TextView>(R.id.OuterCardStars).text = "★☆☆☆☆"
                findViewById<TextView>(R.id.OuterCardPrice).text = "$$$$"
                findViewById<TextView>(R.id.OuterCardGroups).text = "0 groups"
                findViewById<TextView>(R.id.OuterCardLikes).text = "0 interested"
            }
        }
    }
}