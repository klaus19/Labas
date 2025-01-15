package com.example.visuallithuanian.ui.activities.fragments

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
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.ImageAdapter
import com.example.visuallithuanian.data.ImageInfo
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ProfessionFragment : Fragment() {

    lateinit var viewModel: BottomNavigationViewModel
    lateinit var bottomNavigationView: BottomNavigationView


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profession, container, false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity()).get(BottomNavigationViewModel::class.java)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewAnimals)
        val fb1 = view?.findViewById<FloatingActionButton>(R.id.floatingActionButton)

        recyclerView?.layoutManager = LinearLayoutManager(context)

        val back_icon = view?.findViewById<ImageView>(R.id.back_icon)
        back_icon?.setOnClickListener {
            activity?.onBackPressed()
        }

        val professionList = generateProfessionList()
        // settingup ImageAdapter
        val adapter = ImageAdapter(professionList)
        recyclerView?.adapter = adapter

        fb1?.setOnClickListener {
            findNavController().navigate(R.id.action_professionFragment_to_flashCards)
        }


        return view
    }

    private fun generateProfessionList(): List<ImageInfo> {

        return listOf(
            ImageInfo(
                R.drawable.doctor,"Doctor","Gydytojas",emptyList(), R.drawable.mic,
                R.raw.doctor,
                "The doctor helps people stay healthy",
                "Gydytojas padeda žmonėms išlikti sveikiems."),
            ImageInfo(
                R.drawable.lawyer,"Lawyer","Advokatas",emptyList(), R.drawable.mic,
                R.raw.lawyer,
                "The lawyer provides legal advice.",
                "Advokatas teikia teisines konsultacijas"),
            ImageInfo(
                R.drawable.teacher,"Teacher","Mokytojas",emptyList(), R.drawable.mic,
                R.raw.teacher,
                "The teacher is in the classroom.",
                "Mokytojas (mokytoja) yra klasėje"),
            ImageInfo(
                R.drawable.engineer,"Engineer","Inžinierius",emptyList(),
                R.drawable.mic,
                R.raw.engineer,
                "The engineer designs buildings.",
                "Inžinierius projektuoja pastatus."),
            ImageInfo(
                R.drawable.programmer,"Programmer","Programuotojas",emptyList(),
                R.drawable.mic,
                R.raw.programmer,
                "The programmer writes code.","Programuotojas rašo kodą"),
            ImageInfo(
                R.drawable.accountant,"Accountant","Buhalteris",emptyList(),
                R.drawable.mic,
                R.raw.accountant,
                "The accountant manages finances",
                "Apskaitininkas (buhalteris) tvarko finansus"),

            ImageInfo(
                R.drawable.architect,"Architect","Architektas",emptyList(),
                R.drawable.mic,
                R.raw.architect,
                "The architect designs buildings","Architektas projektuoja pastatus"),
            ImageInfo(
                R.drawable.chef,"Chef","Šefas",emptyList(), R.drawable.mic,
                R.raw.chef,
                "The chef cooks delicious meals.",
                "Virėjas gamina skanius patiekalus"),
            ImageInfo(
                R.drawable.nurse,"Nurse","Slaugytojas",emptyList(), R.drawable.mic,
                R.raw.nurse,
                "The nurse cares for patients.",
                "Slaugytoja rūpinasi pacientais"),
            ImageInfo(
                R.drawable.musician,"Musician","Muzikantas",emptyList(), R.drawable.mic,
                R.raw.musician,
                "The musician plays the piano",
                "Muzikantas groja pianinu."),
        )
    }


}