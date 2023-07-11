package com.example.visuallithuanian.ui.activities.fragments

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
import androidx.viewpager.widget.ViewPager
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.FlashcardsAdapter
import com.example.visuallithuanian.adapter.FragmentAdapter
import com.example.visuallithuanian.data.FlashCardInfo
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.ui.activities.Hard
import com.example.visuallithuanian.ui.activities.Medium
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FlashCards : Fragment() {

    lateinit var bottomNav:BottomNavigationView

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
        val recyclerViewCards1 = view.findViewById<RecyclerView>(R.id.recyclerViewFlashcards)

        recyclerViewCards1.layoutManager = LinearLayoutManager(context)

        val navController = Navigation.findNavController(requireActivity(),
            R.id.nav_host_fragment
        )

        val flashCardList = generateFlashCards()
        val adapter = FlashcardsAdapter(flashCardList,navController)
        recyclerViewCards1.adapter = adapter

        // setting up listener
        back_icon.setOnClickListener {
            activity?.onBackPressed()
        }

        return view
    }

    private fun generateFlashCards():List<FlashCardInfo> {
        return listOf(
            FlashCardInfo(R.drawable.talking,"Daily Conversation","Kasdienis pokalbis"),
            FlashCardInfo(R.drawable.doctorvisit,"Questions and Pronouns","Klausimai ir įvardžiai"),
            FlashCardInfo(R.drawable.food,"Food","Maistas"),
            FlashCardInfo(R.drawable.relatives,"Relatives","Giminaičiai"),
            FlashCardInfo(R.drawable.clothing,"Clothing","Drabužiai"),
            FlashCardInfo(R.drawable.village,"Towns and Villages","Miestai ir kaimai"),
            FlashCardInfo(R.drawable.nature,"Nature","Gamta"),
            FlashCardInfo(R.drawable.verbs,"Popular Verbs","Populiariausi veiksmažodžiai"),
        )

    }


}