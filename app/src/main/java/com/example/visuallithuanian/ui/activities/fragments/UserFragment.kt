package com.example.visuallithuanian.ui.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.navigation.Navigation
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.LanguageAdapter
import com.example.visuallithuanian.model.LanguageModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user, container, false)
        val gridView = view.findViewById<GridView>(R.id.mainSpecimens)

        val languageModelArrayList:ArrayList<LanguageModel> = ArrayList()
        languageModelArrayList.add(LanguageModel("Vaisiai", R.drawable.fruits,"Fruits"))
        languageModelArrayList.add(LanguageModel("Gėlės", R.drawable.flowers,"Flowers"))
        languageModelArrayList.add(LanguageModel("Daržovės", R.drawable.vegetable,"Vegetables"))
        languageModelArrayList.add(LanguageModel("Institucijos", R.drawable.campus,"Institutions"))
        languageModelArrayList.add(LanguageModel("Saulės sistema", R.drawable.universe,"Solar System"))
        languageModelArrayList.add(LanguageModel("Metų laikai", R.drawable.season,"Seasons"))
        languageModelArrayList.add(LanguageModel("Kryptys", R.drawable.compass,"Directions"))
        languageModelArrayList.add(LanguageModel("Gyvūnai", R.drawable.animals,"Animals"))
        languageModelArrayList.add(LanguageModel("Paukščiai", R.drawable.birds,"Birds"))
        languageModelArrayList.add(LanguageModel("Sporto", R.drawable.sports,"Sports"))
        languageModelArrayList.add(LanguageModel("Spalvos", R.drawable.colors,"Colors"))
        languageModelArrayList.add(LanguageModel("Kontinentai", R.drawable.continents,"Continents"))
        languageModelArrayList.add(LanguageModel("Numeriai", R.drawable.numbers,"Numbers"))
        languageModelArrayList.add(LanguageModel("Mėnesiai", R.drawable.months,"Months"))
        languageModelArrayList.add(LanguageModel("Dienos", R.drawable.days,"Days"))
        languageModelArrayList.add(LanguageModel("Laikas", R.drawable.time,"Time"))
        languageModelArrayList.add(LanguageModel("Profesija", R.drawable.profession,"Profession"))
        languageModelArrayList.add(LanguageModel("Transportas", R.drawable.transportation,"Transportations"))



        val languageAdapter = context?.let { LanguageAdapter(it,languageModelArrayList) }
        gridView.adapter=languageAdapter
        gridView.isNestedScrollingEnabled = true

         gridView.setOnItemClickListener { parent, view, position, id ->
             val navController = Navigation.findNavController(requireActivity(),
                 R.id.nav_host_fragment
             )
             //The position of the Fruits item in the Gridview
              val fruitsPosition=0
             if (position==fruitsPosition){
                 navController.navigate(R.id.action_userFragment_to_fruitsFragment)
             }
             val flowersPosition =1
             if (position==flowersPosition){
                 navController.navigate(R.id.action_userFragment_to_flowersFragment)
             }
             val vegetablesPosition =2
             if (position==vegetablesPosition){
                 navController.navigate(R.id.action_userFragment_to_vegetables_Fragment)
             }
             val instituitonsPosition =3
             if (position==instituitonsPosition){
                 navController.navigate(R.id.action_userFragment_to_institutionsFragment)
             }

             val solarsystemPosition =4
             if (position==solarsystemPosition){
                 navController.navigate(R.id.action_userFragment_to_solarsystem)
             }
             val seasonsPosition =5
             if (position==seasonsPosition){
                 navController.navigate(R.id.action_userFragment_to_seasonsFragment)
             }
             val directionsPosition =6
             if (position==directionsPosition){
                 navController.navigate(R.id.action_userFragment_to_directionsFragment)
             }

             // The position of the Animals item in the GridView
             val animalPosition = 7
             if (position==animalPosition){
                 navController.navigate(R.id.action_userFragment_to_animals)
             }

             val birdsPosition = 8
             if (position==birdsPosition){
                 navController.navigate(R.id.action_userFragment_to_birdsFragment)
             }

             val sportsPosition =9
             if (position==sportsPosition){
                 navController.navigate(R.id.action_userFragment_to_sportsFragment)
             }


         }
        return view
    }



}