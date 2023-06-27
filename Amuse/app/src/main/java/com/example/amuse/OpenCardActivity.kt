package com.example.amuse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class OpenCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_card)

        var cardID = intent.getStringExtra("cardID")

        Log.e("TAG", "Id of card is ====> $cardID")

        when (cardID) {
            "card1" -> {
                Log.e("TAG", "- 1 -")
            }

            "card2" -> {
                Log.e("TAG", "- 2 -")
            }

            "card3" -> {
                Log.e("TAG", "- 3 -")
            }
        }
    }
}