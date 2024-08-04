package com.example.visuallithuanian.adapter

import android.content.Context
import android.content.SharedPreferences
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
import com.bumptech.glide.Glide
import com.example.visuallithuanian.R
import com.example.visuallithuanian.data.FlashCardInfo

class FlashcardsHardAdapter(
    context: Context,
    private val imageList: List<FlashCardInfo>,
    private val navController: NavController,
    private val unlockedItem: MutableList<String>,
    private val textCounterRed:TextView
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var sharedPreferences: SharedPreferences
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
        val imageViewFlashcards = itemView.findViewById<ImageView>(R.id.imageViewFlashcards)
        val textViewFlashcards = itemView.findViewById<TextView>(R.id.textflashCardName)
        val cardviewFlashcard = itemView.findViewById<CardView>(R.id.cardFlashCards)
    }

    inner class LockedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardviewFlashcardRed = itemView.findViewById<CardView>(R.id.cardFlashCardsRed)
        val textLock = itemView.findViewById<TextView>(R.id.textflashCardNameRed)
        val imageLock = itemView.findViewById<ImageView>(R.id.imageViewLockRed)
        val redGemTextView = itemView.findViewById<TextView>(R.id.redTextView)

    }

    override fun getItemViewType(position: Int): Int {
        val flashCard = imageList[position]
        return if (unlockedItem.contains(flashCard.name)) VIEW_TYPE_UNLOCKED else VIEW_TYPE_LOCKED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = if (viewType == VIEW_TYPE_LOCKED) {
            LayoutInflater.from(parent.context).inflate(R.layout.items_flashcard_lockedredgem, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.item_flashcards, parent, false)
        }
        return if (viewType == VIEW_TYPE_LOCKED) LockedViewHolder(view) else UnlockedViewHolder(view)
    }

    private fun commonPrefrences() {
        val updatedUnlockedSet = unlockedItem.toSet()
        val editor1 = sharedPreferences.edit()
        editor1.putStringSet("unlockedItems", updatedUnlockedSet)
        editor1.apply()
    }

    private fun someRepeatingCode(holder: FlashcardsHardAdapter.LockedViewHolder) {
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
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val flashCard = imageList[position]
        if (holder is UnlockedViewHolder) {
            holder.imageViewFlashcards.setImageResource(flashCard.imageId)
            holder.textViewFlashcards.text = flashCard.name
            holder.cardviewFlashcard.setOnClickListener {
                when (flashCard.name) {
                    "Maths" -> navController.navigate(R.id.action_flashCards_to_mathsFragment)
                    "Interface" -> navController.navigate(R.id.action_flashCards_to_interfaceFragment)
                    "Animal Words" -> navController.navigate(R.id.action_flashCards_to_animalWordsFragment)
                    "Feelings" -> navController.navigate(R.id.action_flashCards_to_feelingsFragment)
                    "Rights" -> navController.navigate(R.id.action_flashCards_to_rightsFlashcardFragment)
                    "Actions" -> navController.navigate(R.id.action_flashCards_to_actionsFlashcardFragment)
                    "Adjectives" -> navController.navigate(R.id.action_flashCards_to_adjectivesFlashcardFragment)
                }
            }
        } else if (holder is LockedViewHolder) {
            holder.textLock.text = flashCard.name
            holder.imageLock.setImageResource(R.drawable.lockpic)
            holder.redGemTextView.text = flashCard.topRightValue.toString()

            holder.cardviewFlashcardRed.setOnClickListener {

                val dialogView = LayoutInflater.from(holder.itemView.context)
                    .inflate(R.layout.dialog_custom_message, null)

                val messageTextView: TextView = dialogView.findViewById(R.id.dialogMessage)
                val imageView: ImageView = dialogView.findViewById(R.id.dialogImage)

                messageTextView.text = "Do you have ${flashCard.topRightValue}"
                imageView.setImageResource(R.drawable.redgemicon)

                AlertDialog.Builder(holder.itemView.context)
                    .setView(dialogView)
                    .setNegativeButton("NO") { dialog, _ -> dialog.cancel() }
                    .setPositiveButton("YES") { _, _ ->
                        try {
                            val currentCount = textCounterRed.text.toString().toInt()
                            val redCount = flashCard.topRightValue
                            if (currentCount>= redCount){
                                val newCount = currentCount-redCount
                                textCounterRed.text = newCount.toString()

                                // Save the new value to SharedPreferences
                                sharedPreferences = holder.itemView.context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                                val editor = sharedPreferences.edit()
                                editor.putInt("textCountRed", newCount)
                                editor.apply()

                                if (flashCard.topRightValue==4){
                                    unlockedItem.add("Maths")
                                    commonPrefrences()
                                    someRepeatingCode(holder)
                                    // Update the view to reflect the unlocked state
                                    notifyDataSetChanged()
                                    holder.cardviewFlashcardRed.setOnClickListener {
                                        navController.navigate(R.id.action_flashCards_to_mathsFragment)
                                    }
                                }

                                if(flashCard.topRightValue ==8){
                                    unlockedItem.add("Interface")
                                    commonPrefrences()
                                    someRepeatingCode(holder)
                                    // Update the view to reflect the unlocked state
                                    notifyDataSetChanged()
                                    holder.cardviewFlashcardRed.setOnClickListener {
                                        navController.navigate(R.id.action_flashCards_to_interfaceFragment)
                                    }
                                }
                                if (flashCard.topRightValue ==12){
                                    unlockedItem.add("Animal Words")
                                    commonPrefrences()
                                    someRepeatingCode(holder)
                                    // Update the view to reflect the unlocked state
                                    notifyDataSetChanged()
                                    holder.cardviewFlashcardRed.setOnClickListener {
                                        navController.navigate(R.id.action_flashCards_to_animalWordsFragment)
                                    }
                                }
                                if (flashCard.topRightValue ==16){
                                    unlockedItem.add("Feelings")
                                    commonPrefrences()
                                    someRepeatingCode(holder)
                                    // Update the view to reflect the unlocked state
                                    notifyDataSetChanged()
                                    holder.cardviewFlashcardRed.setOnClickListener {
                                        navController.navigate(R.id.action_flashCards_to_feelingsFragment)
                                    }
                                }
                                if (flashCard.topRightValue ==18){
                                    unlockedItem.add("Rights")
                                    commonPrefrences()
                                    someRepeatingCode(holder)
                                    // Update the view to reflect the unlocked state
                                    notifyDataSetChanged()
                                    holder.cardviewFlashcardRed.setOnClickListener {
                                        navController.navigate(R.id.action_flashCards_to_rightsFlashcardFragment)
                                    }
                                }

                                if (flashCard.topRightValue ==20){
                                    unlockedItem.add("Actions")
                                    commonPrefrences()
                                    someRepeatingCode(holder)
                                    // Update the view to reflect the unlocked state
                                    notifyDataSetChanged()
                                    holder.cardviewFlashcardRed.setOnClickListener {
                                        navController.navigate(R.id.action_flashCards_to_actionsFlashcardFragment)
                                    }
                                }
                                if (flashCard.topRightValue ==24){
                                    unlockedItem.add("Adjectives")
                                    commonPrefrences()
                                    someRepeatingCode(holder)
                                    // Update the view to reflect the unlocked state
                                    notifyDataSetChanged()
                                    holder.cardviewFlashcardRed.setOnClickListener {
                                        navController.navigate(R.id.action_flashCards_to_adjectivesFlashcardFragment)
                                    }
                                }
                            }else{
                                unlockFailure(holder)
                                // Update the view to reflect the unlocked state
                                notifyDataSetChanged()
                            }

                        }catch (e:NumberFormatException){
                            e.printStackTrace()
                        }

                    }.show()
            }

        }
    }

    private fun unlockFailure(holder: FlashcardsHardAdapter.LockedViewHolder) {

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
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}
