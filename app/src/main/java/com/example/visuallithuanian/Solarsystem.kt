package com.example.visuallithuanian

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
import com.example.visuallithuanian.adapter.ImageAdapter
import com.example.visuallithuanian.base.BottomNavigationScrollListener
import com.example.visuallithuanian.data.ImageInfo
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Solarsystem : Fragment() {

    private lateinit var viewModel: BottomNavigationViewModel
    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_solarsystem, container, false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity()).get(BottomNavigationViewModel::class.java)
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
            findNavController().navigate(R.id.action_solarsystem_to_flashCards)
        }


        val exampleList = generateSolarSystemList()

        // settingup ImageAdapter
        val adapter = ImageAdapter(exampleList)
        recyclerView?.adapter = adapter

        return view
    }

    private fun generateSolarSystemList(): List<ImageInfo> {

        return listOf(
            ImageInfo(R.drawable.sun,"Sun","Saulė",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(
                R.drawable.star,"Star","Žvaigždė",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(R.drawable.meteor,"Meteor","Meteoras",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(R.drawable.comet,"Comet","Kometa",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(
                R.drawable.mercury,"Mercury","Merkurijus",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(R.drawable.venus,"Venus","Venera",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(R.drawable.earth,"Earth","Žemė",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(
                R.drawable.mercury,"Mars","Marsas",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(R.drawable.jupiter,"Jupiter","Jupiteris",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(R.drawable.saturn,"Saturn","Saturnas",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(
                R.drawable.uranus,"","Uranas",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(R.drawable.moon,"Moon","Mėnulis",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(R.drawable.planets,"Planets","Planetos",listOf(R.drawable.purp), R.drawable.mic,
                "",""),

        )

    }

}