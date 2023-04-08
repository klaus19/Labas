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
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TimeFragment : Fragment() {

    lateinit var viewModel: BottomNavigationViewModel
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_time, container, false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity()).get(BottomNavigationViewModel::class.java)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewAnimals)
        val fb1 = view?.findViewById<FloatingActionButton>(R.id.floatingActionButton)

        recyclerView?.layoutManager = LinearLayoutManager(context)

        val back_icon = view?.findViewById<ImageView>(R.id.back_icon)
        back_icon?.setOnClickListener {
            activity?.onBackPressed()
        }

        val timeList = generateTime()
        // settingup ImageAdapter
        val adapter = ImageAdapter(timeList)
        recyclerView?.adapter = adapter

        return view
    }

    private fun generateTime(): List<ImageInfo> {

        return listOf(
            ImageInfo(R.drawable.second,"Second","Sekundė",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "",""),
            ImageInfo(R.drawable.minute,"Minute","Minutė",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "",""),
            ImageInfo(R.drawable.hour,"Hour","Valanda",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "",""),
            ImageInfo(R.drawable.day,"Day","Diena",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "",""),
            ImageInfo(R.drawable.week,"Week","Savaitė",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "",""),
            ImageInfo(R.drawable.month,"Month","Mėnuo",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "",""),
            ImageInfo(R.drawable.year,"Year","Metai",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "",""),
            ImageInfo(R.drawable.decade,"Decade","Dešimtmetis",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "",""),
            ImageInfo(R.drawable.century,"Century","Amžius",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "",""),
            ImageInfo(R.drawable.millenium,"Millennium","Tūkstantmetis",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "",""),
        )
    }


}