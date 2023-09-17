package com.example.visuallithuanian.ui.activities.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.FlashcardsAdapter
import com.example.visuallithuanian.data.FlashCardInfo
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FlashCards : Fragment() {

    lateinit var bottomNav:BottomNavigationView

    @SuppressLint("MissingInflatedId")
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
        val recyclerViewCardsEasy = view.findViewById<RecyclerView>(R.id.recyclerViewFlashcardsEasy)
        val recyclerViewCardsMedium = view.findViewById<RecyclerView>(R.id.recyclerViewFlashcardsMedium)
        val recyclerViewCardsHard = view.findViewById<RecyclerView>(R.id.recyclerViewFlashcardsHard)


        recyclerViewCardsHard.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCardsMedium.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCardsEasy.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)

        val navController = Navigation.findNavController(requireActivity(),
            R.id.nav_host_fragment
        )

        val flashCardList = generateFlashCards()
        val adapter = FlashcardsAdapter(flashCardList,navController)
        recyclerViewCardsHard.adapter = adapter

        val easyFlashCardsList = generateEasyFlashCards()
        val adapter1 = FlashcardsAdapter(easyFlashCardsList,navController)
        recyclerViewCardsEasy.adapter = adapter1

        val mediumFlashCardsList = generateMediumFlashCards()
        val adapter2 = FlashcardsAdapter(mediumFlashCardsList,navController)
        recyclerViewCardsMedium.adapter = adapter2



        // setting up listener
        back_icon.setOnClickListener {
            activity?.onBackPressed()
        }

        return view
    }

    private fun generateMediumFlashCards(): List<FlashCardInfo> {
        return listOf(
            FlashCardInfo(R.drawable.clothing,"Clothing",""),
            FlashCardInfo(R.drawable.village,"Towns and Villages",""),
            FlashCardInfo(R.drawable.nature,"Nature",""),

        )

    }

    private fun generateEasyFlashCards(): List<FlashCardInfo> {

        return listOf(
            FlashCardInfo(R.drawable.talking,"Daily Conversation",""),
            FlashCardInfo(R.drawable.food,"Food",""),
            FlashCardInfo(R.drawable.relatives,"Relatives",""),
        )

    }

    private fun generateFlashCards():List<FlashCardInfo> {
        return listOf(

            FlashCardInfo(R.drawable.doctorvisit,"Questions and Pronouns",""),
            FlashCardInfo(R.drawable.verbs,"Popular Verbs",""),
        )

    }


}