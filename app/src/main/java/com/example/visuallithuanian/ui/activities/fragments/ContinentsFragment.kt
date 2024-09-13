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
class ContinentsFragment : Fragment() {

    lateinit var viewModel: BottomNavigationViewModel
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_continents, container, false)
        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity()).get(BottomNavigationViewModel::class.java)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewAnimals)
        val fb1 = view?.findViewById<FloatingActionButton>(R.id.floatingActionButton)

        recyclerView?.layoutManager = LinearLayoutManager(context)

        val back_icon = view?.findViewById<ImageView>(R.id.back_icon)
        back_icon?.setOnClickListener {
            activity?.onBackPressed()
        }

        val continenettList = generateContinents()
        // settingup ImageAdapter
        val adapter = ImageAdapter(continenettList)
        recyclerView?.adapter = adapter


        fb1?.setOnClickListener {
            findNavController().navigate(R.id.action_continentsFragment_to_flashCards)
        }

        return view
    }

    private fun generateContinents(): List<ImageInfo> {
        return listOf(
            ImageInfo(
                R.drawable.europe,"Europe","Europa",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "Europe has many diverse countries",
                "Europa turi daug įvairių šalių."),
            ImageInfo(
                R.drawable.asia,"Asia","Azija",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "Asia is the largest continent",
                "Azija yra didžiausia kontinenta."),
            ImageInfo(
                R.drawable.africa,"Africa","Afrika",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "Africa is known for its diverse wildlife",
                "Afrika yra žinoma dėl savo įvairios faunos"),
            ImageInfo(
                R.drawable.northamerica,"North America","Šiaurės Amerika",listOf(R.drawable.purp),
                R.drawable.mic,
                R.raw.potato,
                "North America has a variety of climates",
                "Šiaurės Amerika turi įvairias klimato zonas."),
            ImageInfo(
                R.drawable.southamerica,"South America","Pietų Amerika",listOf(R.drawable.purp),
                R.drawable.mic,
                R.raw.potato,
                "South America is known for its rainforests",
                "Pietų Amerika yra žinoma dėl savo atogrąžų miškų"),
            ImageInfo(
                R.drawable.australia,"Australia","Australija",listOf(R.drawable.purp),
                R.drawable.mic,
                R.raw.potato,
                "Australia is famous for its unique wildlife",
                "Australija yra žinoma dėl savo unikalios faunos."),
            ImageInfo(
                R.drawable.antartica,"Antarctica","Antarktida",listOf(R.drawable.purp),
                R.drawable.mic,
                R.raw.potato,
                "Antarctica is covered in ice and snow.",
                "Antarktida yra padengta ledu ir sniegu."),
        )
    }

}