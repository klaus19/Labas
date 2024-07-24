package com.example.visuallithuanian.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.data.FlashCardInfo


class FlashcardsEasyAdapter(private val imageList: List<FlashCardInfo>
                            , private val navController: NavController,
                            private val unlockedItem:String="Questions and Pronouns"


) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

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
        val fireCountTextView = itemView.findViewById<TextView>(R.id.topRightTextView)
        val fireImageview = itemView.findViewById<ImageView>(R.id.topRightImageView)
    }

    override fun getItemViewType(position: Int): Int {
        return if (imageList[position].name == unlockedItem) VIEW_TYPE_UNLOCKED else VIEW_TYPE_LOCKED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = if (viewType == VIEW_TYPE_LOCKED) {
            LayoutInflater.from(parent.context).inflate(R.layout.items_flashcard_lockedfire, parent, false)
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
            holder.textLock.text = flashCard.name
            holder.imageLock.setImageResource(R.drawable.lockpic)
            holder.fireCountTextView.text = flashCard.topRightValue.toString()
            //holder.fireImageview.setImageResource(R.drawable.fireicon)

            holder.cardviewFlashcard.setOnClickListener {
                if (flashCard.name != "Questions and Pronouns") {
                    // Inflate the custom layout for the dialog
                    val dialogView = LayoutInflater.from(holder.itemView.context)
                        .inflate(R.layout.dialog_custom_message, null)

                    // Set up the message and image
                    val messageTextView: TextView = dialogView.findViewById(R.id.dialogMessage)
                    val imageView: ImageView = dialogView.findViewById(R.id.dialogImage)

                    // Set the message text and image resource dynamically
                    messageTextView.text = when (flashCard.name) {
                        "Pointers" -> "Do you have ${flashCard.topRightValue}"
                        "Daily Basic" -> "Do you have ${flashCard.topRightValue}"
                        "Day and Months" -> "Do you have ${flashCard.topRightValue}"
                        "Key Phrases" -> "Do you have ${flashCard.topRightValue}"
                        "Basic actions" -> "Do you have ${flashCard.topRightValue}"
                        "Family" -> "Do you have ${flashCard.topRightValue}"
                        "Workplace language" -> "Do you have ${flashCard.topRightValue}"
                        "I verbs" -> "Do you have ${flashCard.topRightValue}"
                        "Holidays, Celebration" -> "Do you have ${flashCard.topRightValue}"
                        "Nature" -> "Do you have ${flashCard.topRightValue}"
                        "Colours and Shapes" -> "Do you have ${flashCard.topRightValue}"
                        "Romantic phrases" -> "Do you have ${flashCard.topRightValue}"
                        else -> "Do you have ${flashCard.topRightValue}"
                    }

                    // Optionally set a specific image for the category
                    imageView.setImageResource(R.drawable.fireicon)

                    AlertDialog.Builder(holder.itemView.context)
                        .setTitle("Category Locked")
                        .setView(dialogView)
                        .setNegativeButton("NO",){dialog,_->dialog.cancel()}
                        .setPositiveButton("YES") { dialog, _ -> dialog.dismiss() }
                        .show()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}
