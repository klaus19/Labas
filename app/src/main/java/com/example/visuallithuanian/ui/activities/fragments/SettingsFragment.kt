package com.example.visuallithuanian.ui.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.SpinnerAdapter
import com.example.visuallithuanian.databinding.FragmentSettingsBinding
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.google.android.material.bottomnavigation.BottomNavigationView


class SettingsFragment : Fragment() {

    private lateinit var binding:FragmentSettingsBinding
    lateinit var bottomNav: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater,container,false)


        // Taking the BottomNavigation view instance from Activity into Fragment
        bottomNav = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        bottomNav.visibility = View.VISIBLE

        val languages = listOf(
            SpinnerAdapter.Language("English", R.drawable.englishuk),
            SpinnerAdapter.Language("Lithuanian", R.drawable.lithuania)
        )

        val adapter = context?.let { SpinnerAdapter(it, languages) }
        binding.spinnerLanguage.adapter = adapter

        return binding.root
    }
}