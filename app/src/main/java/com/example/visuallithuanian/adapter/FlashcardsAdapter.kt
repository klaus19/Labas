package com.example.visuallithuanian.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.data.FlashCardInfo
import com.example.visuallithuanian.ui.activities.fragments.FlashCardsDirections


class FlashcardsAdapter(private val imageList: List<FlashCardInfo>
,  private val navController: NavController
) :RecyclerView.Adapter<FlashcardsAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewFlashcards = itemView.findViewById<ImageView>(com.example.visuallithuanian.R.id.imageViewFlashcards)
        val textViewFlashcards = itemView.findViewById<TextView>(com.example.visuallithuanian.R.id.textflashCardName)
        val textViewFlashcardsLithuanian = itemView.findViewById<TextView>(com.example.visuallithuanian.R.id.textflashCardLithuanian)

        val cardviewFlashcard = itemView.findViewById<CardView>(com.example.visuallithuanian.R.id.cardFlashCards)

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

        holder.cardviewFlashcard.setOnClickListener {
            val action = when(position){
                    1 ->FlashCardsDirections.actionFlashCardsToQuestionsFragment()

                else -> return@setOnClickListener
            }
            action.let {
                navController.navigate(it)
            }

        }


    }


    override fun getItemCount(): Int {
        return imageList.size
    }
}