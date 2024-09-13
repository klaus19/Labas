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
class SportsFragment : Fragment() {

    lateinit var viewModel: BottomNavigationViewModel

    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_sports, container, false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity())[BottomNavigationViewModel::class.java]
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewAnimals)

        // Added a functionality where the bottomnavigation view will get invisible while scrolling and
        // appear after scrolling is stopped
        // recyclerView?.addOnScrollListener(BottomNavigationScrollListener(viewModel))
        // setting up recyclerview

        recyclerView?.layoutManager = LinearLayoutManager(context)



        val back_icon = view.findViewById<ImageView>(R.id.back_icon)

        // setting up listener
        back_icon.setOnClickListener {
            activity?.onBackPressed()
        }

        //setting up FloatingActionButton
        val floatingButton = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)

        floatingButton.setOnClickListener {
            findNavController().navigate(R.id.action_sportsFragment_to_flashCards)
        }

        val exampleList = generateSportsList()

        // settingup ImageAdapter
        val adapter = ImageAdapter(exampleList)
        recyclerView?.adapter = adapter

        return view
    }

    private fun generateSportsList(): List<ImageInfo> {

        return listOf(
            ImageInfo(
                R.drawable.football,"Football","Futbolas",emptyList(), R.drawable.mic,
                R.raw.potato,
                "Football is a popular sport worldwide",
                "Futbolas yra populiarus sportas visame pasaulyje"),
            ImageInfo(
                R.drawable.hockey,"Ice Hockey","Ledo ritulys",emptyList(),
                R.drawable.mic,
                R.raw.cabbage,
                "Ice hockey is played on a frozen rink",
                "Ledo ritulys žaidžiamas ant užšalusio ledo"),
            ImageInfo(
                R.drawable.basketball,"Basketball","Krepšinis",emptyList(),
                R.drawable.mic,
                R.raw.zuccini,
                "Basketball is a team sport with a hoop",
                "Krepšinis yra komandinė sporto šaka su lanku"),
            ImageInfo(
                R.drawable.chess,"Chess","Šachmatai",emptyList(),
                R.drawable.mic,
                R.raw.carrot,
                "Chess is a game of strategy.",
                "Šachmatai yra strategijos žaidimas"),
            ImageInfo(
                R.drawable.cycling,"Cycling","Dviračių sportas",emptyList(),
                R.drawable.mic,
                R.raw.beet,
                "Cycling is a great way to stay fit",
                "Dviračių sportas yra puikus būdas išlikti fiziškai aktyviam"),
            ImageInfo(
                R.drawable.swimming,"Swimming","Plaukimas",emptyList(),
                R.drawable.mic,
                R.raw.onion,
                "Swimming is a relaxing exercise.",
                "Plaukimas yra atpalaiduojantis pratimas"),
            ImageInfo(
                R.drawable.rugby,"Rugby","Regbis",emptyList(),
                R.drawable.mic,
                R.raw.beet,
                "Rugby is a fast and physical sport",
                "Regbis yra greitas ir fiziniškas sportas"),
            ImageInfo(
                R.drawable.skating,"Skating","slidinėjimas",emptyList(),
                R.drawable.mic,
                R.raw.onion,
                "Skating on ice is a fun winter activity.",
                "Slidinėjimas ledu yra smagus žiemos užsiėmimas"),
        )
    }
}