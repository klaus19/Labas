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


class FlashcardsEasyAdapter(private val imageList: List<FlashCardInfo>
                            , private val navController: NavController
) :RecyclerView.Adapter<FlashcardsEasyAdapter.ViewHolder>(){

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
                 "Pointers" -> navController.navigate(R.id.action_flashCards_to_pointersFlashcardFragment)
                 "Daily Basic" -> navController.navigate(R.id.action_flashCards_to_dailyBasic)
                 "Day and Months" -> navController.navigate(R.id.action_flashCards_to_daysMonthsFlashcards)
                 "Key Phrases" -> navController.navigate(R.id.action_flashCards_to_keyPhrasesFragment)
                 "Basic actions" -> navController.navigate(R.id.action_flashCards_to_basicActionsFlashcard)
                 "Family" -> navController.navigate(R.id.action_flashCards_to_familyFlashcards)
                 "Workplace language" -> navController.navigate(R.id.action_flashCards_to_workPlaceLanguage)
                 "I verbs" -> navController.navigate(R.id.action_flashCards_to_verbsFragment)
                 "Holidays, Celebration" -> navController.navigate(R.id.action_flashCards_to_holidayCelebrations)
                 "Nature"-> navController.navigate(R.id.action_flashCards_to_natureFragment)
                 "Colours and Shapes" -> navController.navigate(R.id.action_flashCards_to_colorshapeFragment)
                 "Romantic phrases" -> navController.navigate(R.id.action_flashCards_to_romanticPhrasesFragment)
             }

        }


    }


    override fun getItemCount(): Int {
        return imageList.size
    }
}