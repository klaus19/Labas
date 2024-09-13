package com.example.visuallithuanian.ui.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.ImageAdapter
import com.example.visuallithuanian.base.BottomNavigationScrollListener
import com.example.visuallithuanian.data.ImageInfo
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FruitsFragment : Fragment() {


    lateinit var viewModel: BottomNavigationViewModel
    lateinit var bottomNav: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_fruits, container, false)

        //Taking the bOTTOMNavigation view instance from Activity into Fragment
        bottomNav = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!

        // And instance of viewModel is created
        viewModel = ViewModelProvider(requireActivity()).get(BottomNavigationViewModel::class.java)

        // setting up recyclerview
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewAnimals)

        //Putting the base listener here
       // recyclerView?.addOnScrollListener(BottomNavigationScrollListener(viewModel))


        // setting up Toolbar and it's icon
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        val back_icon = view.findViewById<ImageView>(R.id.back_icon)

        // setting up listener
        back_icon.setOnClickListener {
            activity?.onBackPressed()
        }


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
            ImageInfo(
                R.drawable.apple,"Apple","obuolys",emptyList(),
                R.drawable.mic,R.raw.potato,"I love to eat a red apple ","Mėgstu valgyti raudoną obuolį"),
            ImageInfo(
                R.drawable.orange,"Orange","Apelsinas",emptyList(),
                R.drawable.mic, R.raw.potato,"This orange is very sweet"," Šis apelsinas yra labai saldus"),
            ImageInfo(
                R.drawable.raspberry,"Raspberries","Avietės",emptyList(),
                R.drawable.mic,R.raw.potato,"I like raspberry juice","Mėgstu aviečių sultis"),
            ImageInfo(
                R.drawable.gooseberry,"GooseBerry","agrastas",emptyList(),
                R.drawable.mic,R.raw.potato,"I don't like GooseBerry","Nemėgstu agrastas"),
            ImageInfo(R.drawable.plum,"Plum","Slyvos",emptyList(), R.drawable.mic,R.raw.potato,
                "The plum was juicy and delicious","Slyva buvo sultinga ir skani"),
            ImageInfo(R.drawable.pear,"Pear","Kriaušė",emptyList(), R.drawable.mic,R.raw.potato,
                "I like pears a lot","Man labai patinka kriaušės"),
            ImageInfo(
                R.drawable.grape,"Grapes","Vynuogės",emptyList(),
                R.drawable.mic,R.raw.potato,"He ate grapes as a healthy snack","Jis valgė vynuoges kaip sveiką užkandį"),
            ImageInfo(
                R.drawable.banana,"Banana","Bananas",emptyList(),
                R.drawable.mic,R.raw.potato,"She likes to eat bananas","Ji mėgsta valgyti bananus"),
            ImageInfo(
                R.drawable.blackcurrant,"Black currant","Juodieji serbentai",emptyList(),
                R.drawable.mic,R.raw.potato,"Black currants are sweet","Juodieji serbentai yra saldūs"),
            ImageInfo(R.drawable.mango,"Mango","Mango", emptyList(), R.drawable.mic,R.raw.potato,
                "I like Mango juice","Man patinka mangų sultys."),
            ImageInfo(
                R.drawable.strawberry,"Strawberry","braškių", emptyList(), R.drawable.mic,R.raw.potato,
                "I love to eat fresh strawberries in the summer","Vasarą mėgstu valgyti šviežias braškes")
        )


    }


}