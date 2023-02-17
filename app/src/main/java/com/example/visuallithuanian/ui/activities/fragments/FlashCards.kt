package com.example.visuallithuanian.ui.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.FlashcardsAdapter
import com.example.visuallithuanian.data.FlashCardInfo
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.google.android.material.bottomnavigation.BottomNavigationView


class FlashCards : Fragment() {

    private lateinit var bottomNav:BottomNavigationView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_flash_cards, container, false)

        //Taking the bOTTOMNavigation view instance from Activity into Fragment
        bottomNav = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        bottomNav.visibility = View.VISIBLE

        val back_icon = view.findViewById<ImageView>(R.id.back_icon)
        val recyclerViewCards = view.findViewById<RecyclerView>(R.id.recyclerViewFlashcards)
        recyclerViewCards.layoutManager = LinearLayoutManager(context)

        val flashCardList = generateFlashCards()
        val adapter = FlashcardsAdapter(flashCardList)
        recyclerViewCards.adapter = adapter


        // setting up listener
        back_icon.setOnClickListener {
            activity?.onBackPressed()
        }

        return view
    }

    private fun generateFlashCards():List<FlashCardInfo> {
        return listOf(
            FlashCardInfo(R.drawable.word,"Words"),
            FlashCardInfo(  R.drawable.talking,"Daily Conversation"),
            FlashCardInfo(R.drawable.food,"Food"),
            FlashCardInfo(R.drawable.relatives,"Relatives"),
            FlashCardInfo(R.drawable.clothing,"Clothing"),

        )

    }


}