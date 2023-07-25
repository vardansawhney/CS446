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

    private var onClickListener: OnClickListener? = null

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView : ImageView = itemView.findViewById(R.id.card_media)
        val titleTextView : TextView = itemView.findViewById(R.id.card_title)
        val locationTextView : TextView = itemView.findViewById(R.id.card_location)
        val starsTextView : TextView = itemView.findViewById(R.id.card_stars)
        val priceTextView : TextView = itemView.findViewById(R.id.card_price)
        val descriptionTextView : TextView = itemView.findViewById(R.id.description_open)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cardsList[position]
        if(card.imageId != null){
            holder.imageView.setImageResource(card.imageId!!)
        }else{
            holder.imageView.setImageResource(R.drawable.card1_media)
        }
        holder.titleTextView.text = card.title
        holder.locationTextView.text = card.location
        holder.starsTextView.text = card.stars
        holder.priceTextView.text = card.price
        if(card.desc !=null){
            holder.descriptionTextView.text = card.desc

        }else{
            holder.descriptionTextView.text = ""
        }

        holder.itemView.setOnClickListener{
            if (onClickListener != null){
                onClickListener!!.onClick(position, card)
            }
        }

    }

    override fun getItemCount(): Int {
        return cardsList.size
    }

    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, model: Card)
    }
}