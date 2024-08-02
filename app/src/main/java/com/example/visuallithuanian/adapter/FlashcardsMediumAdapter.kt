package com.example.visuallithuanian.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.visuallithuanian.R
import com.example.visuallithuanian.data.FlashCardInfo

class FlashcardsMediumAdapter(
    context: Context,
    private val imageList: MutableList<FlashCardInfo>,  // Mutable list to allow updates
    private val navController: NavController,
    private val unlockedItem: MutableList<String>,
    private val textCounterPurple: TextView
) : RecyclerView.Adapter<ViewHolder>() {
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

    inner class UnlockedViewHolder(itemView: View) : ViewHolder(itemView) {
        val imageViewFlashcards: ImageView = itemView.findViewById(R.id.imageViewFlashcards)
        val textViewFlashcards: TextView = itemView.findViewById(R.id.textflashCardName)
        val cardviewFlashcard: CardView = itemView.findViewById(R.id.cardFlashCards)
    }

    inner class LockedViewHolder(itemView: View) : ViewHolder(itemView) {
        val cardviewFlashcardPurple: CardView = itemView.findViewById(R.id.cardFlashCardsPurpleGem)
        val textLock: TextView = itemView.findViewById(R.id.textflashCardNamePurple)
        val imageLock: ImageView = itemView.findViewById(R.id.imageViewLockPurple)
        val fireCountTextView: TextView = itemView.findViewById(R.id.purpleTextView)
    }

    override fun getItemViewType(position: Int): Int {
        val flashCard = imageList[position]
        return if (unlockedItem.contains(flashCard.name)) VIEW_TYPE_UNLOCKED else VIEW_TYPE_LOCKED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = if (viewType == VIEW_TYPE_LOCKED) {
            LayoutInflater.from(parent.context).inflate(R.layout.items_flashcard_lockedpurplegem, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.item_flashcards, parent, false)
        }
        return if (viewType == VIEW_TYPE_LOCKED) LockedViewHolder(view) else UnlockedViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged", "MissingInflatedId", "ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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
            holder.fireCountTextView.text = flashCard.topRightValue.toString()

            holder.cardviewFlashcardPurple.setOnClickListener {

                    val dialogView = LayoutInflater.from(holder.itemView.context)
                        .inflate(R.layout.dialog_custom_message, null)

                    val messageTextView: TextView = dialogView.findViewById(R.id.dialogMessage)
                    val imageView: ImageView = dialogView.findViewById(R.id.dialogImage)

                    messageTextView.text = "Do you have ${flashCard.topRightValue}"
                    imageView.setImageResource(R.drawable.purplegemicon)


                    AlertDialog.Builder(holder.itemView.context)
                        .setView(dialogView)
                        .setNegativeButton("NO") { dialog, _ -> dialog.cancel() }
                        .setPositiveButton("YES") { _, _ ->
                            try {
                                val currentCount = textCounterPurple.text.toString().toInt()
                                val fireCount = flashCard.topRightValue
                                if (currentCount >= fireCount) {
                                    val newCount = currentCount - fireCount
                                    textCounterPurple.text = newCount.toString()

                                    // Save the new value to SharedPreferences
                                    sharedPreferences = holder.itemView.context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                                    val editor = sharedPreferences.edit()
                                    editor.putInt("textCountPurple", newCount)
                                    editor.apply()

                                    // Update the unlocked state and save to SharedPreferences
                                    if (flashCard.topRightValue == 1) {
                                        unlockedItem.add("Computer terminology")
                                        commonPrefrences()
                                        someRepeatingCode(holder)
                                        // Update the view to reflect the unlocked state
                                        notifyDataSetChanged()
                                        holder.cardviewFlashcardPurple.setOnClickListener {
                                                navController.navigate(R.id.action_flashCards_to_computerTechnologyFragment)
                                        }
                                    }
                                    if (flashCard.topRightValue ==2){
                                        unlockedItem.add("Towns and Villages")
                                        commonPrefrences()
                                        someRepeatingCode(holder)
                                        // Update the view to reflect the unlocked state
                                        notifyDataSetChanged()
                                        holder.cardviewFlashcardPurple.setOnClickListener {
                                                navController.navigate(R.id.action_flashCards_to_villageFragment)
                                        }
                                    }
                                    if (flashCard.topRightValue==4){
                                        unlockedItem.add("Time")
                                        commonPrefrences()
                                        someRepeatingCode(holder)
                                        notifyDataSetChanged()
                                        holder.cardviewFlashcardPurple.setOnClickListener {
                                            navController.navigate(R.id.action_flashCards_to_timeFlashcardFragment)
                                        }
                                    }
                                    if (flashCard.topRightValue==6){
                                        unlockedItem.add("Cinema")
                                        commonPrefrences()
                                        someRepeatingCode(holder)
                                        notifyDataSetChanged()
                                        holder.cardviewFlashcardPurple.setOnClickListener {
                                            navController.navigate(R.id.action_flashCards_to_cinemaFragment)
                                        }
                                    }
                                    if(flashCard.topRightValue ==8){
                                        unlockedItem.add("Numbers")
                                        commonPrefrences()
                                        someRepeatingCode(holder)
                                        notifyDataSetChanged()
                                        holder.cardviewFlashcardPurple.setOnClickListener {
                                            navController.navigate(R.id.action_flashCards_to_numbersFlashcardFragment)
                                        }
                                    }
                                    if (flashCard.topRightValue ==10 ){
                                        unlockedItem.add("Business Language")
                                        commonPrefrences()
                                        someRepeatingCode(holder)
                                        notifyDataSetChanged()
                                        holder.cardviewFlashcardPurple.setOnClickListener {
                                            navController.navigate(R.id.action_flashCards_to_businessLanguageFlashcardsFragment)
                                        }
                                    }
                                    if (flashCard.topRightValue == 12){
                                        unlockedItem.add("Cafe")
                                        commonPrefrences()
                                        someRepeatingCode(holder)
                                        notifyDataSetChanged()
                                        holder.cardviewFlashcardPurple.setOnClickListener {
                                            navController.navigate(R.id.action_flashCards_to_cafeFlashcardsFragment)
                                        }
                                    }
                                    if (flashCard.topRightValue ==14){
                                        unlockedItem.add("Sports")
                                        commonPrefrences()
                                        someRepeatingCode(holder)
                                        notifyDataSetChanged()
                                        holder.cardviewFlashcardPurple.setOnClickListener {
                                            navController.navigate(R.id.action_flashCards_to_sportsFlashcardFragment)
                                        }
                                    }
                                    if (flashCard.topRightValue == 16){
                                        unlockedItem.add("Things")
                                        commonPrefrences()
                                        someRepeatingCode(holder)
                                        notifyDataSetChanged()
                                        holder.cardviewFlashcardPurple.setOnClickListener {
                                            navController.navigate(R.id.action_flashCards_to_thingsFragment)
                                        }
                                    }
                                    if (flashCard.topRightValue == 20){
                                        unlockedItem.add("Personality")
                                        commonPrefrences()
                                        someRepeatingCode(holder)
                                        notifyDataSetChanged()
                                        holder.cardviewFlashcardPurple.setOnClickListener {
                                            navController.navigate(R.id.action_flashCards_to_personalityFlashcardFragment)
                                        }
                                    }
                                    if (flashCard.topRightValue == 22){
                                        unlockedItem.add("Professions")
                                        commonPrefrences()
                                        someRepeatingCode(holder)
                                        notifyDataSetChanged()
                                        holder.cardviewFlashcardPurple.setOnClickListener {
                                            navController.navigate(R.id.action_flashCards_to_professionFlashcardFragment)
                                        }
                                    }
                                    if (flashCard.topRightValue == 24){
                                        unlockedItem.add("Household")
                                        commonPrefrences()
                                        someRepeatingCode(holder)
                                        notifyDataSetChanged()
                                        holder.cardviewFlashcardPurple.setOnClickListener {
                                            navController.navigate(R.id.action_flashCards_to_householdFlashcardFragment)
                                        }
                                    }
                                    if (flashCard.topRightValue == 26){
                                        unlockedItem.add("Weekly Basics")
                                        commonPrefrences()
                                        someRepeatingCode(holder)
                                        notifyDataSetChanged()
                                        holder.cardviewFlashcardPurple.setOnClickListener {
                                            navController.navigate(R.id.action_flashCards_to_weeklyBasicFlashcardFragment)
                                        }
                                    }
                                    if (flashCard.topRightValue == 28){
                                        unlockedItem.add("100 best words")
                                        commonPrefrences()
                                        someRepeatingCode(holder)
                                        notifyDataSetChanged()
                                        holder.cardviewFlashcardPurple.setOnClickListener {
                                            navController.navigate(R.id.action_flashCards_to_bestWords100Fragment)
                                        }
                                    }

                                    if (flashCard.topRightValue == 30){
                                        unlockedItem.add("Animals")
                                        commonPrefrences()
                                        someRepeatingCode(holder)
                                        notifyDataSetChanged()
                                        holder.cardviewFlashcardPurple.setOnClickListener {
                                            navController.navigate(R.id.action_flashCards_to_animalFlashcardFragment)
                                        }
                                    }
                                    if (flashCard.topRightValue == 32){
                                        unlockedItem.add("Food & Ingredients")
                                        commonPrefrences()
                                        someRepeatingCode(holder)
                                        notifyDataSetChanged()
                                        holder.cardviewFlashcardPurple.setOnClickListener {
                                            navController.navigate(R.id.action_flashCards_to_foodIngrediants)
                                        }
                                    }
                                    if (flashCard.topRightValue == 34){
                                        unlockedItem.add("Veganism")
                                        commonPrefrences()
                                        someRepeatingCode(holder)
                                        notifyDataSetChanged()
                                        holder.cardviewFlashcardPurple.setOnClickListener {
                                            navController.navigate(R.id.action_flashCards_to_veganFlashcardsFragment)
                                        }
                                    }

                                } else {
                                    unlockFailure(holder)
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

    private fun commonPrefrences() {
        val updatedUnlockedSet = unlockedItem.toSet()
        val editor1 = sharedPreferences.edit()
        editor1.putStringSet("unlockedItems", updatedUnlockedSet)
        editor1.apply()
    }

    private fun unlockFailure(holder: LockedViewHolder) {
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


    private fun someRepeatingCode(holder: LockedViewHolder) {
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

    override fun getItemCount(): Int {
        return imageList.size
    }
}
