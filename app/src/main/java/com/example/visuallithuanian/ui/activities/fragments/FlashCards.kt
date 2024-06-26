package com.example.visuallithuanian.ui.activities.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.FlashcardsEasyAdapter
import com.example.visuallithuanian.adapter.FlashcardsHardAdapter
import com.example.visuallithuanian.adapter.FlashcardsMediumAdapter
import com.example.visuallithuanian.custom.FadeEdgeItemDecoration
import com.example.visuallithuanian.data.FlashCardInfo
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FlashCards : Fragment() {

    lateinit var bottomNav: BottomNavigationView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_flash_cards, container, false)

        // Taking the BottomNavigation view instance from Activity into Fragment
        bottomNav = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        bottomNav.visibility = View.VISIBLE

        val back_icon = view.findViewById<ImageView>(R.id.back_icon)
        val recyclerViewCardsEasy = view.findViewById<RecyclerView>(R.id.recyclerViewFlashcardsEasy)
        val recyclerViewCardsMedium = view.findViewById<RecyclerView>(R.id.recyclerViewFlashcardsMedium)
        val recyclerViewCardsHard = view.findViewById<RecyclerView>(R.id.recyclerViewFlashcardsHard)

        recyclerViewCardsHard.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCardsMedium.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCardsEasy.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        val hardFlashCardList = generateHardFlashCards()
        val adapter = FlashcardsHardAdapter(hardFlashCardList, navController)
        recyclerViewCardsHard.adapter = adapter

        val easyFlashCardsList = generateEasyFlashCards()
        val adapter1 = FlashcardsEasyAdapter(easyFlashCardsList, navController)
        recyclerViewCardsEasy.adapter = adapter1

        val mediumFlashCardsList = generateMediumFlashCards()
        val adapter2 = FlashcardsMediumAdapter(mediumFlashCardsList, navController)
        recyclerViewCardsMedium.adapter = adapter2

        // Add the custom ItemDecoration for fading effect
        recyclerViewCardsEasy.addItemDecoration(FadeEdgeItemDecoration(requireContext()))
        recyclerViewCardsMedium.addItemDecoration(FadeEdgeItemDecoration(requireContext()))
        recyclerViewCardsHard.addItemDecoration(FadeEdgeItemDecoration(requireContext()))

        // Setting up listener
        back_icon.setOnClickListener {
            activity?.onBackPressed()
        }

        return view
    }

    private fun generateMediumFlashCards(): List<FlashCardInfo> {
        return listOf(
            FlashCardInfo(R.drawable.computer, "Computer terminology", ""),
            FlashCardInfo(R.drawable.village, "Towns and Villages", ""),
            FlashCardInfo(R.drawable.time, "Time", ""),
            FlashCardInfo(R.drawable.cinemascreen, "Cinema", ""),
            FlashCardInfo(R.drawable.numbers, "Numbers", ""),
            FlashCardInfo(R.drawable.business, "Business Language", ""),
            FlashCardInfo(R.drawable.cafe, "Cafe", ""),
            FlashCardInfo(R.drawable.sports, "Sports", ""),
            FlashCardInfo(R.drawable.things1, "Things", ""),
            FlashCardInfo(R.drawable.personality, "Personality", ""),
            FlashCardInfo(R.drawable.profession, "Professions", ""),
            FlashCardInfo(R.drawable.household, "Household", ""),
            FlashCardInfo(R.drawable.weekly, "Weekly Basics", ""),
            FlashCardInfo(R.drawable.best, "100 best words", ""),
            FlashCardInfo(R.drawable.animals, "Animals", ""),
            FlashCardInfo(R.drawable.food, "Food & Ingredients", ""),
            FlashCardInfo(R.drawable.vegan, "Veganism", ""),
        )
    }

    private fun generateEasyFlashCards(): List<FlashCardInfo> {
        return listOf(
            FlashCardInfo(R.drawable.doctorvisit, "Questions and Pronouns", ""),
            FlashCardInfo(R.drawable.pointers, "Pointers", ""),
            FlashCardInfo(R.drawable.talking, "Daily Basic", ""),
            FlashCardInfo(R.drawable.action, "Basic actions", ""),
            FlashCardInfo(R.drawable.holiday1, "Holidays, Celebration", ""),
            FlashCardInfo(R.drawable.relatives, "Family", ""),
            FlashCardInfo(R.drawable.keyphrase, "Key Phrases", ""),
            FlashCardInfo(R.drawable.days, "Day and Months", ""),
            FlashCardInfo(R.drawable.colorshape, "Colours and Shapes", ""),
            FlashCardInfo(R.drawable.iverb, "I verbs", ""),
            FlashCardInfo(R.drawable.workplace, "Workplace language", ""),
            FlashCardInfo(R.drawable.nature, "Nature", ""),
            FlashCardInfo(R.drawable.romantic, "Romantic phrases", "")
        )
    }

    private fun generateHardFlashCards(): List<FlashCardInfo> {
        return listOf(
            FlashCardInfo(R.drawable.maths, "Maths", ""),
            FlashCardInfo(R.drawable.interface2, "Interface", ""),
            FlashCardInfo(R.drawable.animalwords, "Animal Words", ""),
            FlashCardInfo(R.drawable.feelings1, "Feelings", ""),
            FlashCardInfo(R.drawable.rights, "Rights", ""),
            FlashCardInfo(R.drawable.actions, "Actions", ""),
            FlashCardInfo(R.drawable.adjectives, "Adjectives", ""),
        )
    }
}
