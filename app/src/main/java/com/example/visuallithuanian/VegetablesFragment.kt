package com.example.visuallithuanian

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.adapter.ImageAdapter
import com.example.visuallithuanian.data.ImageInfo
import com.google.android.material.floatingactionbutton.FloatingActionButton


class VegetablesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_vegetables, container, false)

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewAnimals)


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


        val adapter = ImageAdapter(exampleList)
        recyclerView?.adapter = adapter

        return view
    }

    private fun generateVegetablesList(): List<ImageInfo> {
        return listOf(
            ImageInfo(R.drawable.potato,"Potato","Bulvė",listOf(R.drawable.purp), R.drawable.mic,
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
            ImageInfo(R.drawable.pepper,"Pepper","Pipirai",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(
                R.drawable.tomato,"Tomatoes","Pomidorai",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(R.drawable.cucumber,"Cucumber","Agurkai",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(
                R.drawable.radish,"Radish","Ridikas",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
        )

    }

}