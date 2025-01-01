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
class WeekdaysFragment : Fragment() {


    lateinit var viewModel: BottomNavigationViewModel
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_weekdays, container, false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity()).get(BottomNavigationViewModel::class.java)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewAnimals)
        val fb1 = view?.findViewById<FloatingActionButton>(R.id.floatingActionButton)

        recyclerView?.layoutManager = LinearLayoutManager(context)

        val back_icon = view?.findViewById<ImageView>(R.id.back_icon)
        back_icon?.setOnClickListener {
            activity?.onBackPressed()
        }

        val dayList = generateWeekdayList()
        // settingup ImageAdapter
        val adapter = ImageAdapter(dayList)
        recyclerView?.adapter = adapter

        fb1?.setOnClickListener {
            findNavController().navigate(R.id.action_weekFragment_to_flashCards)
        }
        return view
    }

    private fun generateWeekdayList(): List<ImageInfo> {

        return listOf(
            ImageInfo(
                R.drawable.sunday,"Sunday","Sekmadienis",emptyList(), R.drawable.mic,
                R.raw.sunday,
                "Sunday is a day for rest",
                "Sekmadienis yra poilsio diena"),
            ImageInfo(
                R.drawable.monday,"Monday","Pirmadienis",emptyList(), R.drawable.mic,
                R.raw.monday,
                "Monday is the first day of the week.",
                "Pirmadienis yra pirmoji savaitės diena"),
            ImageInfo(
                R.drawable.tuesday,"Tuesday","Antradienis",emptyList(), R.drawable.mic,
                R.raw.tuesday,
                "Tuesday comes after Monday",
                "Antradienis ateina po pirmadienio"),
            ImageInfo(
                R.drawable.wednesday,"Wednesday","Trečiadienis",emptyList(),
                R.drawable.mic,
                R.raw.wednesday,
                "Wednesday is in the middle of the week",
                "Trečiadienis yra savaitės viduryje"),
            ImageInfo(
                R.drawable.thursday,"Thursday","Ketvirtadienis",emptyList(),
                R.drawable.mic,
                R.raw.thursday,
                "Thursday is the day before Friday",
                "Ketvirtadienis yra diena prieš penktadien"),
            ImageInfo(
                R.drawable.friday,"Friday","Penktadienis",emptyList(), R.drawable.mic,
                R.raw.friday,
                "Friday is the end of the workweek.",
                "Penktadienis yra darbo savaitės pabaiga"),
            ImageInfo(
                R.drawable.saturday,"Saturday","Šeštadienis",emptyList(),
                R.drawable.mic,
                R.raw.saturday,
                "Saturday is a popular day for relaxing.",
                "Šeštadienis yra populiari diena atsipalaiduoti"),
        )
    }


}