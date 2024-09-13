package com.example.visuallithuanian.ui.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.ImageAdapter
import com.example.visuallithuanian.data.ImageInfo
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TimeFragment : Fragment() {

    lateinit var viewModel: BottomNavigationViewModel
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_time, container, false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity()).get(BottomNavigationViewModel::class.java)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewAnimals)
        val fb1 = view?.findViewById<FloatingActionButton>(R.id.floatingActionButton)

        recyclerView?.layoutManager = LinearLayoutManager(context)

        val back_icon = view?.findViewById<ImageView>(R.id.back_icon)
        back_icon?.setOnClickListener {
            activity?.onBackPressed()
        }

        val timeList = generateTime()
        // settingup ImageAdapter
        val adapter = ImageAdapter(timeList)
        recyclerView?.adapter = adapter

        fb1?.setOnClickListener {
            findNavController().navigate(R.id.action_timeFragment_to_flashCards)
        }

        return view
    }

    private fun generateTime(): List<ImageInfo> {

        return listOf(
            ImageInfo(
                R.drawable.second,"Second","Sekundė",emptyList(), R.drawable.mic,
                R.raw.potato,
                "A minute has sixty seconds",
                "Minutė turi šešiasdešimt sekundžių"),
            ImageInfo(
                R.drawable.minute,"Minute","Minutė",emptyList(), R.drawable.mic,
                R.raw.potato,
                "A minute has sixty seconds",
                "Minutė turi šešiasdešimt sekundžių"),
            ImageInfo(
                R.drawable.hour,"Hour","Valanda",emptyList(), R.drawable.mic,
                R.raw.potato,
                "The meeting is in one hour",
                "Susitikimas bus per valandą"),
            ImageInfo(
                R.drawable.day,"Day","Diena",emptyList(), R.drawable.mic,
                R.raw.potato,
                "Today is a sunny day",
                "Šiandien yra saulėta diena"),
            ImageInfo(
                R.drawable.week,"Week","Savaitė",emptyList(), R.drawable.mic,
                R.raw.potato,
                "This week is very busy",
                "Ši savaitė yra labai užimta"),
            ImageInfo(
                R.drawable.month,"Month","Mėnuo",emptyList(), R.drawable.mic,
                R.raw.potato,
                "March is the third month of the year",
                "Kovas yra trečiasis mėnuo metų."),
            ImageInfo(
                R.drawable.year,"Year","Metai",emptyList(), R.drawable.mic,
                R.raw.potato,
                "This year is going by quickly.",
                "Šie metai praeina greitai"),
            ImageInfo(
                R.drawable.decade,"Decade","Dešimtmetis",emptyList(), R.drawable.mic,
                R.raw.potato,
                "The 1990s was an interesting decade.",
                "1990-ieji buvo įdomus dešimtmetis"),
            ImageInfo(
                R.drawable.century,"Century","Amžius",emptyList(), R.drawable.mic,
                R.raw.potato,
                "The 21st century has brought many changes.",
                "21 amžius atnešė daug pokyčių"),
            ImageInfo(
                R.drawable.millenium,"Millennium","Tūkstantmetis",emptyList(),
                R.drawable.mic,
                R.raw.potato,
                "We entered the new millennium in 2000",
                "Įžengėme į naują tūkstantmetį 2000 metais"),
        )
    }


}