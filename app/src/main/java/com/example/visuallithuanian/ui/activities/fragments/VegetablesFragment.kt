package com.example.visuallithuanian.ui.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.ImageAdapter
import com.example.visuallithuanian.data.ImageInfo
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            ImageInfo(
                R.drawable.potato,"Potato","Bulvė",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "Potato is popular in Lithuania","Bulvė yra populiari Lietuvoje"),
            ImageInfo(
                R.drawable.cabbage,"Cabbage","Kopūstas",listOf(R.drawable.purp),
                R.drawable.mic,
                R.raw.cabbage,
                "I don't eat cabbage","Aš nevalgau kopūstų"),
            ImageInfo(
                R.drawable.zucchini,"Zucchini","Cukinija",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.zuccini,
                "Zucchini is healthy to eat","Cukinijas valgyti sveika"),
            ImageInfo(
                R.drawable.carrot,"Carrot","Morka",listOf(R.drawable.purp),
                R.drawable.mic,
                R.raw.carrot,
                "Rabbit likes Carrots","Triušiui patinka morkos"),
            ImageInfo(
                R.drawable.beet,"Beet","Burokas",listOf(R.drawable.purp),
                R.drawable.mic,
                R.raw.beet,
                "Doctor told me to eat beet","Gydytojas liepė valgyti burokus"),
            ImageInfo(
                R.drawable.onion,"Onion","svogūnas",listOf(R.drawable.purp),
                R.drawable.mic,
                R.raw.onion,
                "Onion makes soup tasty","Svogūnai padaro sriubą skanią"),
            ImageInfo(
                R.drawable.pepper,"Pepper","Pipiras",listOf(R.drawable.purp),
                R.drawable.mic,
                R.raw.pepper,
                "This pizza has peppers ","Šioje picoje yra pipirų"),
            ImageInfo(
                R.drawable.tomato,"Tomatoes","Pomidoras",listOf(R.drawable.purp),
                R.drawable.mic,
                R.raw.tomato,
                "I like Tomato sauce","Man patinka pomidorų padažas"),
            ImageInfo(
                R.drawable.cucumber,"Cucumber","Agurkas",listOf(R.drawable.purp),
                R.drawable.mic,
                R.raw.cucumber,
                "She doesn't like Cucumber salad","Ji nemėgsta agurkų salotų"),
            ImageInfo(
                R.drawable.radish,"Radish","Ridikas",listOf(R.drawable.purp),
                R.drawable.mic,
                R.raw.radish,
                "Radish is good for health","Ridikai yra naudingi sveikatai"),
        )

    }

}