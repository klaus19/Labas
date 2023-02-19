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

        viewModel.bottomNavigationVisibility.observe(viewLifecycleOwner, Observer { visibility ->
            bottomNav.visibility = if (visibility) View.VISIBLE else View.GONE
        })

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
                R.drawable.apple,"Apple","obuolys",listOf(R.drawable.purp),
                R.drawable.mic,"I love to eat a red apple ","Mėgstu valgyti raudoną obuolį",
                "Apples","Obuoliai"),
            ImageInfo(
                R.drawable.orange,"Orange","Apelsinas",listOf(R.drawable.purp),
                R.drawable.mic,"This orange is very sweet"," Šis apelsinas yra labai saldus",
                "",""),
            ImageInfo(
                R.drawable.raspberry,"Raspberries","Avietės",listOf(R.drawable.purp),
                R.drawable.mic,"I like raspberry juice","Mėgstu aviečių sultis","",""),
            ImageInfo(
                R.drawable.gooseberry,"GooseBerry","agrastas",listOf(R.drawable.purp),
                R.drawable.mic,"I don't like GooseBerry","Nemėgstu agrastas","",""),
            ImageInfo(R.drawable.plum,"Plum","Slyvos",listOf(R.drawable.purp), R.drawable.mic,
                "The plum was juicy and delicious","Slyva buvo sultinga ir skani","",""),
            ImageInfo(R.drawable.pear,"Pear","Kriaušė",listOf(R.drawable.purp), R.drawable.mic,
                "I like pears a lot","Man labai patinka kriaušės","",""),
            ImageInfo(
                R.drawable.grape,"Grapes","Vynuogės",listOf(R.drawable.purp),
                R.drawable.mic,"He ate grapes as a healthy snack","Jis valgė vynuoges kaip sveiką užkandį","",""),
            ImageInfo(
                R.drawable.banana,"Banana","Bananas",listOf(R.drawable.purp),
                R.drawable.mic,"She likes to eat bananas","Ji mėgsta valgyti bananus","",""),
            ImageInfo(
                R.drawable.blackcurrant,"Black currant","Juodieji serbentai",listOf(R.drawable.purp),
                R.drawable.mic,"Black currants are sweet","Juodieji serbentai yra saldūs","",""),
            ImageInfo(R.drawable.mango,"Mango","Mango",listOf(R.drawable.purp), R.drawable.mic,
                "I made jam from black currants","Gaminau uogienę iš juodųjų serbentų","",""),
            ImageInfo(
                R.drawable.strawberry,"Strawberry","braškių",listOf(R.drawable.purp), R.drawable.mic,
                "I love to eat fresh strawberries in the summer","Vasarą mėgstu valgyti šviežias braškes","","")
        )


    }


}