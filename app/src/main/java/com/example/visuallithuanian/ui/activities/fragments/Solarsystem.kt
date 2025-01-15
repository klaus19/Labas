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
class Solarsystem : Fragment() {


    lateinit var viewModel: BottomNavigationViewModel

    lateinit var bottomNavigationView: BottomNavigationView


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
            ImageInfo(
                R.drawable.sun,"Sun","Saulė",emptyList(), R.drawable.mic,
                R.raw.sun,
                "The sun is bright","Saulė yra ryški"),
            ImageInfo(
                R.drawable.star,"Star","Žvaigždė",emptyList(), R.drawable.mic,
                R.raw.star,
                "The star shines in the night sky.",
                "Žvaigždė šviečia nakties danguje."),
            ImageInfo(
                R.drawable.meteor,"Meteor","Meteoras",emptyList(), R.drawable.mic,
                R.raw.meteor,
                "The meteor moves quickly across the sky",
                "Meteoritas greitai juda per dangų"),
            ImageInfo(
                R.drawable.comet,"Comet","Kometa",emptyList(), R.drawable.mic,
                R.raw.comet,
                "The comet has a bright tail",
                "Kometa turi ryškią uodegą"),
            ImageInfo(
                R.drawable.mercury,"Mercury","Merkurijus",emptyList(), R.drawable.mic,
                R.raw.mercury,
                "Mercury is the closest planet to the sun",
                "Merkurijus yra arčiausia planeta prie saulės"),
            ImageInfo(
                R.drawable.venus,"Venus","Venera",emptyList(), R.drawable.mic,
                R.raw.venus,
                "Venus is known as the hottest planet",
                "Venera yra žinoma kaip karščiausia planeta"),
            ImageInfo(
                R.drawable.earth,"Earth","Žemė",emptyList(), R.drawable.mic,
                R.raw.earth,
                "Earth is our home planet",
                "Žemė yra mūsų namų planeta."),
            ImageInfo(
                R.drawable.mars,"Mars","Marsas",emptyList(), R.drawable.mic,
                R.raw.mars,
                "Mars is called the red planet",
                "Marsas vadinamas raudonąja planeta"),
            ImageInfo(
                R.drawable.jupiter,"Jupiter","Jupiteris",emptyList(), R.drawable.mic,
                R.raw.jupiter,
                "Jupiter is the largest planet in the solar system.",
                "Jupiteris yra didžiausia planeta saulės sistemoje"),
            ImageInfo(
                R.drawable.saturn,"Saturn","Saturnas",emptyList(), R.drawable.mic,
                R.raw.saturn,
                "Saturn has beautiful rings around it.",
                "Saturnas turi gražius žiedus aplink save"),
            ImageInfo(
                R.drawable.uranus,"Uranus","Uranas",emptyList(), R.drawable.mic,
                R.raw.uranus,
                "Uranus rotates on its side","Uranas sukasi ant šono"),
            ImageInfo(
                R.drawable.moon,"Moon","Mėnulis",emptyList(), R.drawable.mic,
                R.raw.moon,
                "The moon shines at night",
                "Mėnulis šviečia naktį."),
            ImageInfo(
                R.drawable.planets,"Planets","Planetos",emptyList(), R.drawable.mic,
                R.raw.planets,
                "The planets orbit the sun.",
                "Planetos sukasi aplink saulę"),

        )

    }

}