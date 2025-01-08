package com.example.visuallithuanian.ui.activities.fragments

import android.annotation.SuppressLint
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


class MonthsFragment : Fragment() {
    lateinit var viewModel: BottomNavigationViewModel
    lateinit var bottomNavigationView: BottomNavigationView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_months, container, false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity()).get(BottomNavigationViewModel::class.java)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewAnimals)
        val fb1 = view?.findViewById<FloatingActionButton>(R.id.floatingActionButton)

        recyclerView?.layoutManager = LinearLayoutManager(context)

        val back_icon = view?.findViewById<ImageView>(R.id.back_icon)
        back_icon?.setOnClickListener {
            activity?.onBackPressed()
        }

        val monthsList = generateMonths()

        // settingup ImageAdapter
        val adapter = ImageAdapter(monthsList)
        recyclerView?.adapter = adapter

        fb1?.setOnClickListener {
            findNavController().navigate(R.id.action_monthsFragment_to_flashCards)
        }

        return view
    }

    private fun generateMonths(): List<ImageInfo> {

        return listOf(
            ImageInfo(
                R.drawable.january,"January","Sausis",emptyList(), R.drawable.mic,
                R.raw.january,
                "January is the first month of the year",
                "Sausis yra pirmas mėnuo metų"),
            ImageInfo(
                R.drawable.february,"February","Vasaris",emptyList(), R.drawable.mic,
                R.raw.february,
                "February is the shortest month.",
                "Vasaris yra trumpiausias mėnuo"),
            ImageInfo(
                R.drawable.march,"March","Kovas",emptyList(), R.drawable.mic,
                R.raw.march,
                "March marks the beginning of spring",
                "Kovas žymi pavasario pradžią"),
            ImageInfo(
                R.drawable.april,"April","Balandis",emptyList(), R.drawable.mic,
                R.raw.april,
                "April often brings rain.",
                "Balandis dažnai atneša lietų"),
            ImageInfo(
                R.drawable.may,"May","Gegužė",emptyList(), R.drawable.mic,
                R.raw.may,
                "May is a month of blooming flowers.",
                "Gegužė yra mėnuo, kai žydi gėlės"),
            ImageInfo(
                R.drawable.june,"June","Birželis",emptyList(), R.drawable.mic,
                R.raw.june,
                "June is the start of summer",
                "Birželis yra vasaros pradžia"),
            ImageInfo(
                R.drawable.july,"July","Liepa",emptyList(), R.drawable.mic,
                R.raw.july,
                "July is a warm month",
                "Liepa yra šiltas mėnuo."),
            ImageInfo(
                R.drawable.august,"August","Rugpjūtis",emptyList(), R.drawable.mic,
                R.raw.august,
                "August is a time for vacations",
                "Rugpjūtis yra atostogų metas"),
            ImageInfo(
                R.drawable.september,"September","Rugsėjis",emptyList(), R.drawable.mic,
                R.raw.september,
                "September marks the beginning of autumn",
                "Rugsėjis žymi rudens pradžią"),
            ImageInfo(
                R.drawable.october,"October","Spalis",emptyList(), R.drawable.mic,
                R.raw.october,
                "October is known for its colorful leaves",
                "Spalis yra žinomas dėl spalvingų lapų."),
            ImageInfo(
                R.drawable.november,"November","Lapkritis",emptyList(), R.drawable.mic,
                R.raw.november,
                "November often brings cooler weather",
                "Lapkritis dažnai atneša vėsesnį orą"),
            ImageInfo(
                R.drawable.december,"December","Gruodis",emptyList(), R.drawable.mic,
                R.raw.december,
                "December is the last month of the year",
                "Gruodis yra paskutinis metų mėnuo"),
            )
    }

}