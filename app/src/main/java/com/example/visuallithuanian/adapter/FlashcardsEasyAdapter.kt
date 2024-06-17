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

class FlashcardsEasyAdapter(
    private val imageList: List<FlashCardInfo>,
    private val navController: NavController,
    private val unlockedItem: String = "Questions and Pronouns"
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_UNLOCKED = 1
    private val VIEW_TYPE_LOCKED = 2

    inner class UnlockedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewFlashcards: ImageView = itemView.findViewById(R.id.imageViewFlashcards)
        val textViewFlashcards: TextView = itemView.findViewById(R.id.textflashCardName)
        val cardviewFlashcard: CardView = itemView.findViewById(R.id.cardFlashCards)
    }

    inner class LockedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewLock: ImageView = itemView.findViewById(R.id.imageViewLock)
        val textViewFlashcards: TextView = itemView.findViewById(R.id.textflashCardName)
        val cardviewFlashcard: CardView = itemView.findViewById(R.id.cardFlashCards)
    }

    override fun getItemViewType(position: Int): Int {
        return if (imageList[position].name == unlockedItem) VIEW_TYPE_UNLOCKED else VIEW_TYPE_LOCKED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_UNLOCKED) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_flashcards, parent, false)
            UnlockedViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.items_flashcard_locked, parent, false)
            LockedViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val flashCard = imageList[position]

        if (holder is UnlockedViewHolder) {
            holder.imageViewFlashcards.setImageResource(flashCard.imageId)
            holder.textViewFlashcards.text = flashCard.name

            holder.cardviewFlashcard.setOnClickListener {
                when (flashCard.name) {
                    "Questions and Pronouns" -> navController.navigate(R.id.action_flashCards_to_questionsFragment)
                    "Pointers" -> navController.navigate(R.id.action_flashCards_to_pointersFlashcardFragment)
                    "Daily Basic" -> navController.navigate(R.id.action_flashCards_to_dailyBasic)
                    "Day and Months" -> navController.navigate(R.id.action_flashCards_to_daysMonthsFlashcards)
                    "Key Phrases" -> navController.navigate(R.id.action_flashCards_to_keyPhrasesFragment)
                    "Basic actions" -> navController.navigate(R.id.action_flashCards_to_basicActionsFlashcard)
                    "Family" -> navController.navigate(R.id.action_flashCards_to_familyFlashcards)
                    "Workplace language" -> navController.navigate(R.id.action_flashCards_to_workPlaceLanguage)
                    "I verbs" -> navController.navigate(R.id.action_flashCards_to_verbsFragment)
                    "Holidays, Celebration" -> navController.navigate(R.id.action_flashCards_to_holidayCelebrations)
                    "Nature" -> navController.navigate(R.id.action_flashCards_to_natureFragment)
                    "Colours and Shapes" -> navController.navigate(R.id.action_flashCards_to_colorshapeFragment)
                    "Romantic phrases" -> navController.navigate(R.id.action_flashCards_to_romanticPhrasesFragment)
                }
            }
        } else if (holder is LockedViewHolder) {
            holder.textViewFlashcards.text = flashCard.name
            holder.imageViewLock.setImageResource(R.drawable.lockpic)

            holder.cardviewFlashcard.setOnClickListener {
                Toast.makeText(holder.itemView.context, "This set is locked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}
