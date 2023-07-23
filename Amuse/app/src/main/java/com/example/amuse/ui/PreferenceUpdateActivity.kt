package com.example.amuse.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.amuse.MainActivity
import com.example.amuse.R
import com.example.amuse.databinding.RecommendationMenuBinding
import kotlin.properties.Delegates

class PreferenceUpdateActivity : AppCompatActivity(){
    private lateinit var binding: RecommendationMenuBinding
    private var distance by Delegates.notNull<Int>()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecommendationMenuBinding.inflate(layoutInflater)
        setContentView(R.layout.recommendation_menu)

        val buttonClick = findViewById<Button>(R.id.save_preferences)
        buttonClick.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            distance=45
            startActivity(intent)
        }
    }
}