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
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class VegetablesFragment : Fragment() {

    private lateinit var viewModel: BottomNavigationViewModel
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_vegetables, container, false)

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
            findNavController().navigate(R.id.action_vegetables_Fragment_to_flashCards)
        }


        val exampleList = generateVegetablesList()

        // settingup ImageAdapter
        val adapter = ImageAdapter(exampleList)
        recyclerView?.adapter = adapter

        return view
    }

    private fun generateVegetablesList(): List<ImageInfo> {
        return listOf(
            ImageInfo(R.drawable.potato,"Potato","Bulvės",listOf(R.drawable.purp), R.drawable.mic,
                "Potato is popular in Lithuania","Bulvės populiarios Lietuvoje"),
            ImageInfo(
                R.drawable.cabbage,"Cabbage","Kopūstai",listOf(R.drawable.purp), R.drawable.mic,
                "I don't eat cabbage","Aš nevalgau kopūstų"),
            ImageInfo(R.drawable.zucchini,"Zucchini","Cukinijos",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(
                R.drawable.carrot,"Carrot","Morkos",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(R.drawable.beet,"Beet","burokėliai",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(
                R.drawable.onion,"Onion","Svogūnai",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(R.drawable.pepper,"Peppers","Paprikos",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(
                R.drawable.tomato,"Tomatoes","Pomidorai",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(R.drawable.cucumber,"Cucumber","Agurkai",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(
                R.drawable.radish,"Radish","Ridikėliai",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
        )

    }

}