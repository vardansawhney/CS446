package com.example.amuse.ui

import com.example.amuse.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.amuse.ui.home.Card


class CardAdapter(private val cardsList: ArrayList<Card>) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView : ImageView = itemView. findViewById(R.id.OuterCardMedia)
        val titleTextView : TextView = itemView.findViewById(R.id.OuterCardTitle)
        val locationTextView : TextView = itemView.findViewById(R.id.OuterCardLocation)
        val starsTextView : TextView = itemView.findViewById(R.id.OuterCardStars)
        val priceTextView : TextView = itemView.findViewById(R.id.OuterCardPrice)
        val groupsTextView : TextView = itemView.findViewById(R.id.OuterCardGroups)
        val likesTextView : TextView = itemView.findViewById(R.id.OuterCardLikes)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cardsList[position]
        holder.imageView.setImageResource(card.imageId)
        holder.titleTextView.text = card.title
        holder.locationTextView.text = card.location
        holder.starsTextView.text = card.stars
        holder.priceTextView.text = card.price
        holder.groupsTextView.text = card.groups
        holder.likesTextView.text = card.likes

    }

    override fun getItemCount(): Int {
        return cardsList.size
    }
}