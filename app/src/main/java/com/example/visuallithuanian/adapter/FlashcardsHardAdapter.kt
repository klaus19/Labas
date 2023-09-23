package com.example.visuallithuanian.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.data.FlashCardInfo


class FlashcardsHardAdapter(private val imageList: List<FlashCardInfo>
                            , private val navController: NavController
) :RecyclerView.Adapter<FlashcardsHardAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewFlashcards = itemView.findViewById<ImageView>(com.example.visuallithuanian.R.id.imageViewFlashcards)
        val textViewFlashcards = itemView.findViewById<TextView>(com.example.visuallithuanian.R.id.textflashCardName)
        // val textViewFlashcardsLithuanian = itemView.findViewById<TextView>(com.example.visuallithuanian.R.id.textflashCardLithuanian)

        val cardviewFlashcard = itemView.findViewById<CardView>(com.example.visuallithuanian.R.id.cardFlashCards)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_flashcards, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val flashCard = imageList[position]

        holder.imageViewFlashcards.setImageResource(flashCard.imageId)
        holder.textViewFlashcards.text = flashCard.name
        //  holder.textViewFlashcardsLithuanian.text = current_item.translation

        holder.cardviewFlashcard.setOnClickListener {
            when(flashCard.name){
                "Questions and Pronouns" -> navController.navigate(R.id.action_flashCards_to_questionsFragment)
            }

        }


    }


    override fun getItemCount(): Int {
        return imageList.size
    }
}