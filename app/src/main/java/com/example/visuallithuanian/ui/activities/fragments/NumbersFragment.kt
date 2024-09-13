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


class NumbersFragment : Fragment() {

    lateinit var viewModel: BottomNavigationViewModel
    lateinit var bottomNavigationView: BottomNavigationView


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_numbers, container, false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity()).get(BottomNavigationViewModel::class.java)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewAnimals)
        val fb1 = view?.findViewById<FloatingActionButton>(R.id.floatingActionButton)

        recyclerView?.layoutManager = LinearLayoutManager(context)

        val back_icon = view?.findViewById<ImageView>(R.id.back_icon)
        back_icon?.setOnClickListener {
            activity?.onBackPressed()
        }

        val numbersList = generateNumbers()
        // settingup ImageAdapter
        val adapter = ImageAdapter(numbersList)
        recyclerView?.adapter = adapter

        fb1?.setOnClickListener {
            findNavController().navigate(R.id.action_numbersFragment_to_flashCards)
        }


        return view
    }

    private fun generateNumbers(): List<ImageInfo> {
        return listOf(
            ImageInfo(
                R.drawable.zero,"Zero","nulis",emptyList(), R.drawable.mic,
                R.raw.potato,
                "Zero means nothing",
                "Nulis reiškia nieką"),

            ImageInfo(
                R.drawable.one,"One","vienas",emptyList(), R.drawable.mic,
                R.raw.potato,
                "I have one apple",
                "Mano turi vieną obuolį"),
            ImageInfo(
                R.drawable.two,"Two","du",emptyList(), R.drawable.mic,
                R.raw.potato,
                "I have two books",
                "Mano turi du knygas"),
            ImageInfo(
                R.drawable.three,"Three","trys",emptyList(), R.drawable.mic,
                R.raw.potato,
                "I have three pencils",
                "Mano turi tris pieštukus"),
            ImageInfo(
                R.drawable.four,"Four","keturi",emptyList(), R.drawable.mic,
                R.raw.potato,
                "I have four chairs.",
                "Mano turi keturias kėdes"),
            ImageInfo(
                R.drawable.five,"Five","penki",emptyList(), R.drawable.mic,
                R.raw.potato,
                "I have five coins",
                "Mano turi penkis monetas"),
            ImageInfo(
                R.drawable.six,"Six","šeši",emptyList(), R.drawable.mic,
                R.raw.potato,
                "I have six apples",
                "Mano turi šešis obuolius"),
            ImageInfo(
                R.drawable.seven,"Seven","septyni",emptyList(), R.drawable.mic,
                R.raw.potato,
                "I have seven books",
                "Mano turi septynias knygas"),
            ImageInfo(
                R.drawable.eight,"Eight","aštuoni",emptyList(), R.drawable.mic,
                R.raw.potato,
                "I have eight pencils",
                "Mano turi aštuonius pieštukus"),
            ImageInfo(
                R.drawable.nine,"Nine","devyni",emptyList(), R.drawable.mic,
                R.raw.potato,
                "I have nine apples","Mano turi devynis obuolius."),
            ImageInfo(
                R.drawable.ten,"Ten","dešimt",emptyList(), R.drawable.mic,
                R.raw.potato,
                "I have ten pens",
                "Mano turi dešimt rašiklių"),
        )
    }


}