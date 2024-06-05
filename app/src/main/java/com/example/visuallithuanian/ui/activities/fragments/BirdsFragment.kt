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
import com.example.visuallithuanian.data.ImageInfo
import com.example.visuallithuanian.databinding.FragmentBirdsBinding
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
            ImageInfo(R.drawable.whitestork,"White Stork","Baltasis gandrovas",listOf(R.drawable.purp), R.drawable.mic,R.raw.potato,
                "",""),
            ImageInfo(R.drawable.crane,"Common Crane","Paprastasis gegužė",listOf(R.drawable.purp), R.drawable.mic,R.raw.potato,
                "",""),
            ImageInfo(R.drawable.blackgrouse,"Black Grouse","Tetervinas",listOf(R.drawable.purp), R.drawable.mic,R.raw.potato,
                "",""),
            ImageInfo(R.drawable.greentit,"Great Tit","Didysis Meškažirnis",listOf(R.drawable.purp), R.drawable.mic,R.raw.potato,
                "",""),
            ImageInfo(R.drawable.chaff,"Common Chaffinch","Paprastasis šernas",listOf(R.drawable.purp), R.drawable.mic,R.raw.potato,
                "",""),
            ImageInfo(R.drawable.jay,"Eurasian Jay","Žvirblys",listOf(R.drawable.purp), R.drawable.mic,R.raw.potato,
                "",""),
            ImageInfo(R.drawable.robin,"European Robin","Raudonkepuraitė",listOf(R.drawable.purp), R.drawable.mic,R.raw.potato,
                "",""),
            ImageInfo(R.drawable.blackbird,"Common Blackbird","Juodvarnis",listOf(R.drawable.purp), R.drawable.mic,R.raw.potato,
                "",""),
            ImageInfo(R.drawable.buzzard,"Common Buzzard"," Paprastasis suopis",listOf(R.drawable.purp), R.drawable.mic,R.raw.potato,
                "",""),

        )



    }

}