package com.example.visuallithuanian.ui.activities.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
    private lateinit var textCounterFire:TextView

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
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

        textCounterFire = view.findViewById(R.id.text_counter_fire_flashcard)

        loadTextCountFire()

        val navList:MutableList<String> = mutableListOf()
          navList.add("Questions and Pronouns")


        recyclerViewCardsHard.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCardsMedium.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCardsEasy.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        val hardFlashCardList = generateHardFlashCards()
        val adapter = FlashcardsHardAdapter(hardFlashCardList, navController,"")
        recyclerViewCardsHard.adapter = adapter

        val easyFlashCardsList = generateEasyFlashCards()
        val adapter1 = FlashcardsEasyAdapter(requireContext(),easyFlashCardsList.toMutableList(), navController,navList,textCounterFire)
        recyclerViewCardsEasy.adapter = adapter1

        val mediumFlashCardsList = generateMediumFlashCards()
        val adapter2 = FlashcardsMediumAdapter(mediumFlashCardsList, navController,"")
        recyclerViewCardsMedium.adapter = adapter2

        // Add the custom ItemDecoration for fading effect
      //  recyclerViewCardsEasy.addItemDecoration(FadeEdgeItemDecoration(requireContext()))
      //  recyclerViewCardsMedium.addItemDecoration(FadeEdgeItemDecoration(requireContext()))
       // recyclerViewCardsHard.addItemDecoration(FadeEdgeItemDecoration(requireContext()))


        loadSharedPreferences()


        // Setting up listener
        back_icon.setOnClickListener {
            activity?.onBackPressed()
        }

        sendData()
        return view
    }

    private fun sendData() {
        val newCount = textCounterFire.text.toString().toIntOrNull() ?: 0

        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putInt("textCount", newCount)
            apply()
        }

        sendUpdateBroadcast(newCount)

    }

    private fun sendUpdateBroadcast(newCount: Int) {
        val intent = Intent("com.example.UPDATE_TEXT_COUNT")
        intent.putExtra("textCount", newCount)
        requireContext().sendBroadcast(intent)
    }

    private fun loadTextCountFire() {
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val savedCount = sharedPreferences.getInt("textCount", 0)
        textCounterFire.text = savedCount.toString()
    }

    private fun loadSharedPreferences() {
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val counter = sharedPreferences.getInt("counter", 0)
        val counterDiamond = sharedPreferences.getInt("counterDiamond", 0)
        val counterGem = sharedPreferences.getInt("counterGem", 0)

        // Update UI with retrieved values
        view?.findViewById<TextView>(R.id.text_counter_fire_flashcard)?.text = counter.toString()
        view?.findViewById<TextView>(R.id.text_counter_diamond_flashcard)?.text = counterDiamond.toString()
        view?.findViewById<TextView>(R.id.text_counter_gem_flashcard)?.text = counterGem.toString()
    }

    private fun generateMediumFlashCards(): List<FlashCardInfo> {
        return listOf(
            FlashCardInfo(R.drawable.computer, "Computer terminology", "",2,R.drawable.purplegemicon),
            FlashCardInfo(R.drawable.village, "Towns and Villages", "",6,R.drawable.purplegemicon),
            FlashCardInfo(R.drawable.time, "Time", "",8,R.drawable.purplegemicon),
            FlashCardInfo(R.drawable.cinemascreen, "Cinema", "",10,R.drawable.purplegemicon),
            FlashCardInfo(R.drawable.numbers, "Numbers", "",14,R.drawable.purplegemicon),
            FlashCardInfo(R.drawable.business, "Business Language", "",16,R.drawable.purplegemicon),
            FlashCardInfo(R.drawable.cafe, "Cafe", "",18,R.drawable.purplegemicon),
            FlashCardInfo(R.drawable.sports, "Sports", "",20,R.drawable.purplegemicon),
            FlashCardInfo(R.drawable.things1, "Things", "",24,R.drawable.purplegemicon),
            FlashCardInfo(R.drawable.personality, "Personality", "",26,R.drawable.purplegemicon),
            FlashCardInfo(R.drawable.profession, "Professions", "",30,R.drawable.purplegemicon),
            FlashCardInfo(R.drawable.household, "Household", "",32,R.drawable.purplegemicon),
            FlashCardInfo(R.drawable.weekly, "Weekly Basics", "",34,R.drawable.purplegemicon),
            FlashCardInfo(R.drawable.best, "100 best words", "",36,R.drawable.purplegemicon),
            FlashCardInfo(R.drawable.animals, "Animals", "",40,R.drawable.purplegemicon),
            FlashCardInfo(R.drawable.food, "Food & Ingredients", "",44,R.drawable.purplegemicon),
            FlashCardInfo(R.drawable.vegan, "Veganism", "",46,R.drawable.purplegemicon),
        )
    }

    private fun generateEasyFlashCards(): List<FlashCardInfo> {
        return listOf(
            FlashCardInfo(R.drawable.doctorvisit, "Questions and Pronouns", "",0,R.drawable.fireicon),
            FlashCardInfo(R.drawable.pointers, "Pointers", "",5,R.drawable.fireicon),
            FlashCardInfo(R.drawable.talking, "Daily Basic", "",30,R.drawable.fireicon),
            FlashCardInfo(R.drawable.action, "Basic actions", "",40,R.drawable.fireicon),
            FlashCardInfo(R.drawable.holiday1, "Holidays, Celebration", "",50,R.drawable.fireicon),
            FlashCardInfo(R.drawable.relatives, "Family", "",65,R.drawable.fireicon),
            FlashCardInfo(R.drawable.keyphrase, "Key Phrases", "",70,R.drawable.fireicon),
            FlashCardInfo(R.drawable.days, "Day and Months", "",75,R.drawable.fireicon),
            FlashCardInfo(R.drawable.colorshape, "Colours and Shapes", "",80,R.drawable.fireicon),
            FlashCardInfo(R.drawable.iverb, "I verbs", "",85,R.drawable.fireicon),
            FlashCardInfo(R.drawable.workplace, "Workplace language", "",90,R.drawable.fireicon),
            FlashCardInfo(R.drawable.nature, "Nature", "",95,R.drawable.fireicon),
            FlashCardInfo(R.drawable.romantic, "Romantic phrases", "",100,R.drawable.fireicon)
        )
    }

    private fun generateHardFlashCards(): List<FlashCardInfo> {
        return listOf(
            FlashCardInfo(R.drawable.maths, "Maths", "",4,R.drawable.redgemicon),
            FlashCardInfo(R.drawable.interface2, "Interface", "",8,R.drawable.redgemicon),
            FlashCardInfo(R.drawable.animalwords, "Animal Words", "",12,R.drawable.redgemicon),
            FlashCardInfo(R.drawable.feelings1, "Feelings", "",16,R.drawable.redgemicon),
            FlashCardInfo(R.drawable.rights, "Rights", "",18,R.drawable.redgemicon),
            FlashCardInfo(R.drawable.actions, "Actions", "",20,R.drawable.redgemicon),
            FlashCardInfo(R.drawable.adjectives, "Adjectives", "",24,R.drawable.redgemicon),
        )
    }
}
