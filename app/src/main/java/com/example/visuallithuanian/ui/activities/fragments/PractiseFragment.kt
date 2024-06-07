package com.example.visuallithuanian.ui.activities.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.PractiseAdapter
import com.example.visuallithuanian.constants.ImageStore
import com.example.visuallithuanian.databinding.FragmentPractiseBinding
import com.example.visuallithuanian.model.PreferencesHelper

class PractiseFragment : Fragment() {
    private lateinit var binding: FragmentPractiseBinding
    private lateinit var practiseAdapter: PractiseAdapter
    private lateinit var recyclerViewPractise: RecyclerView
    private lateinit var preferencesHelper: PreferencesHelper
    private var counter = 0

    @SuppressLint("ClickableViewAccessibility", "UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPractiseBinding.inflate(layoutInflater, container, false)
        recyclerViewPractise = binding.recyclerViewPractise

        preferencesHelper = PreferencesHelper(requireContext())
        counter = preferencesHelper.getCounter()
        binding.textCounter.text = counter.toString()

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewPractise.layoutManager = layoutManager

        binding.backIcon.setOnClickListener {
            findNavController().navigate(com.example.visuallithuanian.R.id.action_practiseFragment_to_userFragment)
        }

        val randomPairs = ImageStore.getRandomPairs(4)

        val imageResources = randomPairs.map { it.first }.toMutableList()
        val imageNames1 = randomPairs.map { it.second }.toMutableList()

        practiseAdapter = PractiseAdapter(
            imageResources,
            imageNames1,
            binding.btnShuffle,
            recyclerViewPractise,
            preferencesHelper
        ) {
            incrementCounter()
        }

        binding.recyclerViewPractise.adapter = practiseAdapter
        practiseAdapter.initsetRecyclerView(recyclerViewPractise)

        // Shuffle cards after the recyclerView has been initialized
        practiseAdapter.shuffleCards()
        return binding.root
    }

    private fun incrementCounter() {
        counter++
        preferencesHelper.saveCounter(counter)
        binding.textCounter.text = counter.toString()
    }
}
