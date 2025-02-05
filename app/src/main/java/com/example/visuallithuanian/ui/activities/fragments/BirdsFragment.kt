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
class BirdsFragment : Fragment() {

    lateinit var viewModel: BottomNavigationViewModel
    lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_birds, container, false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity()).get(BottomNavigationViewModel::class.java)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewAnimals)
        val fb1 = view?.findViewById<FloatingActionButton>(R.id.floatingActionButton)

        // Added a functionality where the bottomnavigation view will get invisible while scrolling and
        // appear after scrolling is stopped
        // recyclerView?.addOnScrollListener(BottomNavigationScrollListener(viewModel))
        // setting up recyclerview

        recyclerView?.layoutManager = LinearLayoutManager(context)

        val back_icon = view?.findViewById<ImageView>(R.id.back_icon)
        back_icon?.setOnClickListener {
            findNavController().navigate(R.id.action_birdsFragment_to_userFragment)
        }


        val birdList = generateBirdsList()
        // settingup ImageAdapter
        val adapter = ImageAdapter(birdList)
        recyclerView?.adapter = adapter

        fb1?.setOnClickListener {
            findNavController().navigate(R.id.action_birdsFragment_to_flashCards)
        }

        return view
    }

    private fun generateBirdsList(): List<ImageInfo> {
        return listOf(
            ImageInfo(
                R.drawable.whitestork,"White Stork","Baltasis gandras",emptyList(),
                R.drawable.mic,
                R.raw.whitestork,
                "The white stork is known for its long legs and beak",
                "Baltasis gandras žinomas dėl savo ilgų kojų ir snapo"),
            ImageInfo(
                R.drawable.crane,"Common Crane","paprastoji gervė",emptyList(),
                R.drawable.mic,
                R.raw.commoncrane,
                "The common crane migrates long distances",
                "Įprastasis garnys migruoja ilgus atstumus"),
            ImageInfo(
                R.drawable.blackgrouse,"Black Grouse","juodasis tetervinas",emptyList(),
                R.drawable.mic,
                R.raw.blackgrouse,
                "The black grouse is known for its distinctive call",
                "Juodasis tetervinas yra žinomas dėl savo išskirtinio balso."),
            ImageInfo(
                R.drawable.greentit,"Great Tit","didžioji zylė",emptyList(),
                R.drawable.mic,
                R.raw.greattit,
                "The great tit is a common bird in the forest",
                "Didysis zylė yra dažnas paukštis miške."),
            ImageInfo(
                R.drawable.chaff,"Common Chaffinch","paprastasis žvirblis",emptyList(),
                R.drawable.mic,
                R.raw.commonchaffench,
                "The common chaffinch has a bright red chest",
                "Paprastasis žvirblis turi ryškiai raudoną krūtinę"),
            ImageInfo(
                R.drawable.blackbird,"Blackbird","juodasis varnėnas",emptyList(),
                R.drawable.mic,
                R.raw.blackbird,
                "The blackbird has a melodious song",
                "Juodasis varnėnas turi melodingą dainą"),
            ImageInfo(
                R.drawable.buzzard,"Buzzard","suopis",emptyList(),
                R.drawable.mic,
                R.raw.buzzarrd,
                "The buzzard soars high in the sky.",
                "Sklandytuvas kyla aukštai danguje")

        )



    }

}