package com.example.visuallithuanian

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.adapter.ImageAdapter
import com.example.visuallithuanian.data.ImageInfo
import com.example.visuallithuanian.databinding.FragmentFruitsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class FruitsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_fruits, container, false)
        // setting up Toolbar and it's icon
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        val back_icon = view.findViewById<ImageView>(R.id.back_icon)

        // setting up listener
        back_icon.setOnClickListener {
            activity?.onBackPressed()
        }

        // setting up recyclerview
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewAnimals)
        recyclerView.layoutManager = LinearLayoutManager(context)

        //setting up FloatingActionButton
        val floatingButton = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)

        floatingButton.setOnClickListener {
            findNavController().navigate(R.id.action_fruitsFragment_to_flashCards)
        }

        val exampleList = generateFruitsList()

        // settingup ImageAdapter
        val adapter = ImageAdapter(exampleList)
        recyclerView.adapter = adapter


        return view
    }

    private fun generateFruitsList(): List<ImageInfo> {

        return listOf(
            ImageInfo(R.drawable.apple,"Apple","obuolys",listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.orange,"Orange","Oranžinė",listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.raspberry,"Raspberry","Avietinė",listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.gooseberry,"GooseBerry","agrastas",listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.plum,"Plum","Slyvos",listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.pear,"Pear","Kriaušė",listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.grape,"Grapes","Vynuogės",listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.gooseberry,"Banana","Bananas",listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.blackcurrant,"Black currant","Juodieji serbentai",listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.mango,"Mango","Mango",listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.strawberry,"Strawberry","\n" +
                    "braškių",listOf(R.drawable.purp),R.drawable.mic)
        )


    }


}