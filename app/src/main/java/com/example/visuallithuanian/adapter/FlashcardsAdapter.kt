package com.example.visuallithuanian.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.data.FlashCardInfo


class FlashcardsAdapter(private val imageList: List<FlashCardInfo>) :RecyclerView.Adapter<FlashcardsAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewFlashcards = itemView.findViewById<ImageView>(com.example.visuallithuanian.R.id.imageViewFlashcards)
        val textViewFlashcards = itemView.findViewById<TextView>(com.example.visuallithuanian.R.id.textflashCardName)
        val textViewFlashcardsLithuanian = itemView.findViewById<TextView>(com.example.visuallithuanian.R.id.textflashCardLithuanian)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.example.visuallithuanian.R.layout.item_flashcards, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current_item = imageList[position]

        holder.imageViewFlashcards.setImageResource(current_item.imageId)
        holder.textViewFlashcards.text = current_item.name
        holder.textViewFlashcardsLithuanian.text = current_item.translation

        val imageFlashcards = holder.itemView.findViewById<ImageView>(com.example.visuallithuanian.R.id.imageViewFlashcards)
        val textViewFlashCards = holder.itemView.findViewById<TextView>(com.example.visuallithuanian.R.id.textflashCardName)
        val cardviewFlashcard = holder.itemView.findViewById<CardView>(com.example.visuallithuanian.R.id.cardFlashCards)
        val textLithuanian = holder.itemView.findViewById<TextView>(com.example.visuallithuanian.R.id.textflashCardLithuanian)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}