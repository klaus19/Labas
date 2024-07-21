package com.example.visuallithuanian.ui.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.appcompat.widget.SearchView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.LanguageAdapter
import com.example.visuallithuanian.model.LanguageModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {

    private lateinit var languageAdapter: LanguageAdapter
    private lateinit var languageModelArrayList: ArrayList<LanguageModel>
    private lateinit var filteredList: ArrayList<LanguageModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user, container, false)
        val gridView = view.findViewById<GridView>(R.id.mainSpecimens)
        val searchView = view.findViewById<androidx.appcompat.widget.SearchView>(R.id.searchView)

        // Initialize the list and populate it with items
        languageModelArrayList = ArrayList()
        filteredList = ArrayList()
        populateLanguageList()
        filteredList.addAll(languageModelArrayList)

        // Set up the adapter and GridView
        languageAdapter = LanguageAdapter(requireContext(), filteredList)
        gridView.adapter = languageAdapter
        gridView.isNestedScrollingEnabled = true

        // Set item click listener to navigate to the corresponding fragment
        gridView.setOnItemClickListener { parent, view, position, id ->
            val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            when (filteredList[position].getItem_English()) {
                "Fruits" -> navController.navigate(R.id.action_userFragment_to_fruitsFragment)
                "Flowers" -> navController.navigate(R.id.action_userFragment_to_flowersFragment)
                "Vegetables" -> navController.navigate(R.id.action_userFragment_to_vegetables_Fragment)
                "Institutions" -> navController.navigate(R.id.action_userFragment_to_institutionsFragment)
                "Solar System" -> navController.navigate(R.id.action_userFragment_to_solarsystem)
                "Seasons" -> navController.navigate(R.id.action_userFragment_to_seasonsFragment)
                "Directions" -> navController.navigate(R.id.action_userFragment_to_directionsFragment)
                "Animals" -> navController.navigate(R.id.action_userFragment_to_animals)
                "Birds" -> navController.navigate(R.id.action_userFragment_to_birdsFragment)
                "Sports" -> navController.navigate(R.id.action_userFragment_to_sportsFragment)
                "Colors" -> findNavController().navigate(R.id.action_userFragment_to_colorsFragment)
                "Continents" -> findNavController().navigate(R.id.action_userFragment_to_continentsFragment)
                "Numbers" -> findNavController().navigate(R.id.action_userFragment_to_numbersFragment)
                "Months" -> findNavController().navigate(R.id.action_userFragment_to_monthsFragment)
                "Days" -> findNavController().navigate(R.id.action_userFragment_to_weekFragment)
                "Time" -> findNavController().navigate(R.id.action_userFragment_to_timeFragment)
                "Profession" -> findNavController().navigate(R.id.action_userFragment_to_professionFragment)
                "Transportations" -> findNavController().navigate(R.id.action_userFragment_to_transportFragment)
            }
        }

        // Set up the SearchView to filter the GridView items
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filteredList.clear()
                if (!newText.isNullOrEmpty()) {
                    val searchQuery = newText.lowercase()
                    val resultList = languageModelArrayList.filter {
                        it.getItem_name().lowercase().contains(searchQuery) ||
                                it.getItem_English().lowercase().contains(searchQuery)
                    }
                    filteredList.addAll(resultList)
                } else {
                    filteredList.addAll(languageModelArrayList)
                }
                languageAdapter.notifyDataSetChanged()
                return true
            }
        })

        return view
    }

    // Populate the list with language items
    private fun populateLanguageList() {
        languageModelArrayList.apply {
            add(LanguageModel("", R.drawable.fruits, "Fruits"))
            add(LanguageModel("", R.drawable.flowers, "Flowers"))
            add(LanguageModel("", R.drawable.vegetable, "Vegetables"))
            add(LanguageModel("", R.drawable.campus, "Institutions"))
            add(LanguageModel("", R.drawable.universe, "Solar System"))
            add(LanguageModel("", R.drawable.season, "Seasons"))
            add(LanguageModel("", R.drawable.compass, "Directions"))
            add(LanguageModel("", R.drawable.animals, "Animals"))
            add(LanguageModel("", R.drawable.birds, "Birds"))
            add(LanguageModel("", R.drawable.sports, "Sports"))
            add(LanguageModel("", R.drawable.colors, "Colors"))
            add(LanguageModel("", R.drawable.continents, "Continents"))
            add(LanguageModel("", R.drawable.numbers, "Numbers"))
            add(LanguageModel("", R.drawable.months, "Months"))
            add(LanguageModel("", R.drawable.days, "Days"))
            add(LanguageModel("", R.drawable.time, "Time"))
            add(LanguageModel("", R.drawable.profession, "Profession"))
            add(LanguageModel("", R.drawable.transportation, "Transportations"))
        }
    }
}
