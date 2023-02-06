package com.example.visuallithuanian

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.visuallithuanian.adapter.LanguageAdapter
import com.example.visuallithuanian.model.LanguageModel


class UserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user, container, false)
        val gridView = view.findViewById<GridView>(R.id.mainSpecimens)

        val languageModelArrayList:ArrayList<LanguageModel> = ArrayList()
        languageModelArrayList.add(LanguageModel("Vaisiai",R.drawable.fruits,"Fruits"))
        languageModelArrayList.add(LanguageModel("Gėlės",R.drawable.flowers,"Flowers"))
        languageModelArrayList.add(LanguageModel("Daržovės",R.drawable.vegetable,"Vegetables"))
        languageModelArrayList.add(LanguageModel("Architektūra",R.drawable.campus,"Architectures"))
        languageModelArrayList.add(LanguageModel("Saulės sistema",R.drawable.universe,"Solar System"))
        languageModelArrayList.add(LanguageModel("Sezonai",R.drawable.season,"Seasons"))
        languageModelArrayList.add(LanguageModel("Kryptys",R.drawable.compass,"Compass"))
        languageModelArrayList.add(LanguageModel("Gyvūnai",R.drawable.animals,"Animals"))
        languageModelArrayList.add(LanguageModel("Paukščiai",R.drawable.birds,"Birds"))
        languageModelArrayList.add(LanguageModel("Sporto",R.drawable.sports,"Sports"))

        val languageAdapter = context?.let { LanguageAdapter(it,languageModelArrayList) }
        gridView.adapter=languageAdapter
        gridView.isNestedScrollingEnabled = true

         gridView.setOnItemClickListener { parent, view, position, id ->
             val navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment)
             navController.navigate(R.id.action_userFragment_to_animals)
         }
        return view
    }



}