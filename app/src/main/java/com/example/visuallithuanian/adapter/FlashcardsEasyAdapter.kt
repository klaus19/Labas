package com.example.visuallithuanian.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.visuallithuanian.R
import com.example.visuallithuanian.data.FlashCardInfo

class FlashcardsEasyAdapter(
    context: Context,
    private val imageList: MutableList<FlashCardInfo>,  // Mutable list to allow updates
    private val navController: NavController,
    private val unlockedItem: MutableList<String>,
    private val textCounterFire: TextView
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_UNLOCKED = 0
        private const val VIEW_TYPE_LOCKED = 1
    }

    init {
        // Load unlocked state from SharedPreferences
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val unlockedSet = sharedPreferences.getStringSet("unlockedItems", mutableSetOf())
        if (unlockedSet != null) {
            unlockedItem.addAll(unlockedSet)
        }
    }

    inner class UnlockedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewFlashcards: ImageView = itemView.findViewById(R.id.imageViewFlashcards)
        val textViewFlashcards: TextView = itemView.findViewById(R.id.textflashCardName)
        val cardviewFlashcard: CardView = itemView.findViewById(R.id.cardFlashCards)
    }

    inner class LockedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardviewFlashcardFire: CardView = itemView.findViewById(R.id.cardFlashCardsFire)
        val textLock: TextView = itemView.findViewById(R.id.textflashCardName)
        val imageLock: ImageView = itemView.findViewById(R.id.imageViewLock)
        val fireCountTextView: TextView = itemView.findViewById(R.id.topRightTextView)
    }

    override fun getItemViewType(position: Int): Int {
        val flashCard = imageList[position]
        return if (unlockedItem.contains(flashCard.name)) VIEW_TYPE_UNLOCKED else VIEW_TYPE_LOCKED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = if (viewType == VIEW_TYPE_LOCKED) {
            LayoutInflater.from(parent.context).inflate(R.layout.items_flashcard_lockedfire, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.item_flashcards, parent, false)
        }
        return if (viewType == VIEW_TYPE_LOCKED) LockedViewHolder(view) else UnlockedViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged", "MissingInflatedId", "ResourceType")
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

            holder.cardviewFlashcardFire.setOnClickListener {
                if (flashCard.name != "Questions and Pronouns") {
                    val dialogView = LayoutInflater.from(holder.itemView.context)
                        .inflate(R.layout.dialog_custom_message, null)

                    val messageTextView: TextView = dialogView.findViewById(R.id.dialogMessage)
                    val imageView: ImageView = dialogView.findViewById(R.id.dialogImage)

                    messageTextView.text = "Do you have ${flashCard.topRightValue}"
                    imageView.setImageResource(R.drawable.fireicon)


                    AlertDialog.Builder(holder.itemView.context)
                        .setView(dialogView)
                        .setNegativeButton("NO") { dialog, _ -> dialog.cancel() }
                        .setPositiveButton("YES") { _, _ ->
                            try {
                                val currentCount = textCounterFire.text.toString().toInt()
                                val fireCount = flashCard.topRightValue
                                if (currentCount >= fireCount) {
                                    val newCount = currentCount - fireCount
                                    textCounterFire.text = newCount.toString()

                                    // Save the new value to SharedPreferences
                                    val sharedPreferences = holder.itemView.context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                                    val editor = sharedPreferences.edit()
                                    editor.putInt("textCount", newCount)
                                    editor.apply()

                                    // Update the unlocked state and save to SharedPreferences
                                    if (flashCard.topRightValue == 5) {
                                        unlockedItem.add("Pointers")
                                        val updatedUnlockedSet = unlockedItem.toSet()
                                        val editor1 = sharedPreferences.edit()
                                        editor1.putStringSet("unlockedItems", updatedUnlockedSet)
                                        editor1.apply()

                                        // Create a success dialog
                                        val successDialogView = LayoutInflater.from(holder.itemView.context)
                                            .inflate(R.layout.dialog_card_unlocked, null)

                                        val successMessageTextView: TextView = successDialogView.findViewById(R.id.successDialogMessage)
                                        val successImageView: ImageView = successDialogView.findViewById(R.id.successDialogImage)

                                        successMessageTextView.text = "Yay,Card Unlocked!"
                                        Glide.with(holder.itemView.context).asGif()
                                            .load(R.drawable.happyunlocked)
                                            .into(successImageView)

                                        AlertDialog.Builder(holder.itemView.context)
                                            .setView(successDialogView)
                                            .show()

                                        // Update the view to reflect the unlocked state
                                        notifyDataSetChanged()

                                        holder.cardviewFlashcardFire.setOnClickListener {
                                            if (flashCard.name == "Pointers") {
                                                navController.navigate(R.id.action_flashCards_to_pointersFlashcardFragment)
                                            }
                                        }
                                    }
                                } else {
                                           // create a failure dialog
                                    val failureDialogView = LayoutInflater.from(holder.itemView.context)
                                        .inflate(R.layout.card_locked, null)

                                    val failureMessageTextView: TextView = failureDialogView.findViewById(R.id.notEnoughPointsMessage)
                                    val failureImageView: ImageView = failureDialogView.findViewById(R.id.notEnoughPointsImage)
                                    failureMessageTextView.text = "You don't have enough points!"

                                    Glide.with(holder.itemView.context)
                                        .asGif().load(R.drawable.sadlock)
                                        .into(failureImageView)

                                    AlertDialog.Builder(holder.itemView.context)
                                        .setTitle("")
                                        .setView(failureDialogView)
                                        .show()

                                    // Update the view to reflect the unlocked state
                                    notifyDataSetChanged()

                                }
                            } catch (e: NumberFormatException) {
                                e.printStackTrace()
                            }
                        }
                        .show()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}
