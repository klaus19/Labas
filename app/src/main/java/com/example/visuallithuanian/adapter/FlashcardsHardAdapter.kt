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

class FlashcardsHardAdapter(
    private val imageList: List<FlashCardInfo>,
    private val navController: NavController,
    private val unlockedItem: String = "Questions and Pronouns"
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_UNLOCKED = 0
        private const val VIEW_TYPE_LOCKED = 1
    }

    inner class UnlockedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewFlashcards = itemView.findViewById<ImageView>(R.id.imageViewFlashcards)
        val textViewFlashcards = itemView.findViewById<TextView>(R.id.textflashCardName)
        val cardviewFlashcard = itemView.findViewById<CardView>(R.id.cardFlashCards)
    }

    inner class LockedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardviewFlashcard = itemView.findViewById<CardView>(R.id.cardFlashCards)
        val textLock = itemView.findViewById<TextView>(R.id.textflashCardName)
        val imageLock = itemView.findViewById<ImageView>(R.id.imageViewLock)
    }

    override fun getItemViewType(position: Int): Int {
        return if (imageList[position].name == unlockedItem) VIEW_TYPE_UNLOCKED else VIEW_TYPE_LOCKED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = if (viewType == VIEW_TYPE_LOCKED) {
            LayoutInflater.from(parent.context).inflate(R.layout.items_flashcard_locked, parent, false)
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
                    "Animal Words" -> navController.navigate(R.id.action_flashCards_to_animalWordsFragment)
                    "Interface" -> navController.navigate(R.id.action_flashCards_to_interfaceFragment)
                    "Maths" -> navController.navigate(R.id.action_flashCards_to_mathsFragment)
                    "Feelings" -> navController.navigate(R.id.action_flashCards_to_feelingsFragment)
                    "Rights" -> navController.navigate(R.id.action_flashCards_to_rightsFlashcardFragment)
                    "Actions" -> navController.navigate(R.id.action_flashCards_to_actionsFlashcardFragment)
                    "Adjectives" -> navController.navigate(R.id.action_flashCards_to_adjectivesFlashcardFragment)
                }
            }
        } else if (holder is LockedViewHolder) {
            holder.textLock.text = flashCard.name
            holder.imageLock.setImageResource(R.drawable.lockpic)

            holder.cardviewFlashcard.setOnClickListener {
                Toast.makeText(holder.itemView.context, "This set is locked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}
