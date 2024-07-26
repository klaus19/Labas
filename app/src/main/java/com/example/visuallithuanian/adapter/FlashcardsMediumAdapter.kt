package com.example.visuallithuanian.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.data.FlashCardInfo

class FlashcardsMediumAdapter(
    private val imageList: List<FlashCardInfo>,
    private val navController: NavController,
    private val unlockedItem: String = ""
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_UNLOCKED = 0
        private const val VIEW_TYPE_LOCKED = 1
    }

    inner class UnlockedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewFlashcards: ImageView = itemView.findViewById(R.id.imageViewFlashcards)
        val textViewFlashcards: TextView = itemView.findViewById(R.id.textflashCardName)
        val cardviewFlashcard: CardView = itemView.findViewById(R.id.cardFlashCards)
    }

    inner class LockedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardviewFlashcard = itemView.findViewById<CardView>(R.id.cardFlashCards)
        val textLock = itemView.findViewById<TextView>(R.id.textflashCardName)
        val imageLock = itemView.findViewById<ImageView>(R.id.imageViewLock)
        val purpleCountTextView = itemView.findViewById<TextView>(R.id.purpleTextView)
        val purpleImageview = itemView.findViewById<ImageView>(R.id.topRightImageView)
    }

    override fun getItemViewType(position: Int): Int {
        return if (imageList[position].name == unlockedItem) VIEW_TYPE_UNLOCKED else VIEW_TYPE_LOCKED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = if (viewType == VIEW_TYPE_LOCKED) {
            LayoutInflater.from(parent.context).inflate(R.layout.items_flashcard_lockedpurplegem, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.item_flashcards, parent, false)
        }
        return if (viewType == VIEW_TYPE_LOCKED) LockedViewHolder(view) else UnlockedViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val flashCard = imageList[position]
        if (holder is UnlockedViewHolder) {
            holder.imageViewFlashcards.setImageResource(flashCard.imageId)
            holder.textViewFlashcards.text = flashCard.name
            holder.cardviewFlashcard.setOnClickListener {
                when (flashCard.name) {
                    "Computer terminology" -> navController.navigate(R.id.action_flashCards_to_computerTechnologyFragment)
                    "Towns and Villages" -> navController.navigate(R.id.action_flashCards_to_villageFragment)
                    "Time" -> navController.navigate(R.id.action_flashCards_to_timeFlashcardFragment)
                    "Cinema" -> navController.navigate(R.id.action_flashCards_to_cinemaFragment)
                    "Numbers" -> navController.navigate(R.id.action_flashCards_to_numbersFlashcardFragment)
                    "Business Language" -> navController.navigate(R.id.action_flashCards_to_businessLanguageFlashcardsFragment)
                    "Cafe" -> navController.navigate(R.id.action_flashCards_to_cafeFlashcardsFragment)
                    "Sports" -> navController.navigate(R.id.action_flashCards_to_sportsFlashcardFragment)
                    "Things" -> navController.navigate(R.id.action_flashCards_to_thingsFragment)
                    "Personality" -> navController.navigate(R.id.action_flashCards_to_personalityFlashcardFragment)
                    "Professions" -> navController.navigate(R.id.action_flashCards_to_professionFlashcardFragment)
                    "Household" -> navController.navigate(R.id.action_flashCards_to_householdFlashcardFragment)
                    "Weekly Basics" -> navController.navigate(R.id.action_flashCards_to_weeklyBasicFlashcardFragment)
                    "100 best words" -> navController.navigate(R.id.action_flashCards_to_bestWords100Fragment)
                    "Food & Ingredients" -> navController.navigate(R.id.action_flashCards_to_foodIngrediants)
                    "Veganism" -> navController.navigate(R.id.action_flashCards_to_veganFlashcardsFragment)
                    "Animals" -> navController.navigate(R.id.action_flashCards_to_animalFlashcardFragment)
                }
            }
        } else if (holder is LockedViewHolder) {
            holder.textLock.text = flashCard.name
            holder.imageLock.setImageResource(R.drawable.lockpic)
            holder.purpleCountTextView.text = flashCard.topRightValue.toString()
//            holder.purpleImageview.setImageResource(R.drawable.purplegemicon)
//            holder.cardviewFlashcard.setOnClickListener {
//                Toast.makeText(holder.itemView.context, "This set is locked", Toast.LENGTH_SHORT).show()
//            }
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}
