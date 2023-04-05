package com.example.visuallithuanian

import android.annotation.SuppressLint
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
import com.example.visuallithuanian.databinding.FragmentColorsBinding
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ColorsFragment : Fragment() {

    lateinit var viewModel: BottomNavigationViewModel
    lateinit var bottomNavigationView: BottomNavigationView


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_colors, container, false)
        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity()).get(BottomNavigationViewModel::class.java)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewAnimals)
        val fb1 = view?.findViewById<FloatingActionButton>(R.id.floatingActionButton)

        recyclerView?.layoutManager = LinearLayoutManager(context)

        val back_icon = view?.findViewById<ImageView>(R.id.back_icon)
        back_icon?.setOnClickListener {
            activity?.onBackPressed()
        }

        val colorList = generateColorsList()
        // settingup ImageAdapter
        val adapter = ImageAdapter(colorList)
        recyclerView?.adapter = adapter

        fb1?.setOnClickListener {
            findNavController().navigate(R.id.action_colorsFragment_to_flashCards)
        }


        return view
    }

    private fun generateColorsList(): List<ImageInfo> {
       return listOf(
           ImageInfo(R.drawable.red,"Red","Raudona",listOf(R.drawable.purp), R.drawable.mic,
               R.raw.potato,
               "",""),
           ImageInfo(R.drawable.blue,"Blue","Mėlyna",listOf(R.drawable.purp), R.drawable.mic,
               R.raw.potato,
               "",""),
           ImageInfo(R.drawable.yellow,"Yellow","Geltona",listOf(R.drawable.purp), R.drawable.mic,
               R.raw.potato,
               "",""),
           ImageInfo(R.drawable.green,"Green","Žalia",listOf(R.drawable.purp), R.drawable.mic,
               R.raw.potato,
               "",""),
           ImageInfo(R.drawable.orange,"Orange","Oranžinė",listOf(R.drawable.purp), R.drawable.mic,
               R.raw.potato,
               "",""),
           ImageInfo(R.drawable.purple,"Purple","Violetinė",listOf(R.drawable.purp), R.drawable.mic,
               R.raw.potato,
               "",""),
           ImageInfo(R.drawable.black,"Black","Juoda",listOf(R.drawable.purp), R.drawable.mic,
               R.raw.potato,
               "",""),
           ImageInfo(R.drawable.white,"White","Balta",listOf(R.drawable.purp), R.drawable.mic,
               R.raw.potato,
               "",""),
           ImageInfo(R.drawable.gray,"Gray","Pilka",listOf(R.drawable.purp), R.drawable.mic,
               R.raw.potato,
               "",""),
       )
    }


}