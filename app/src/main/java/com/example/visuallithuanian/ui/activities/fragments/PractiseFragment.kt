package com.example.visuallithuanian.ui.activities.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.adapter.PractiseAdapter
import com.example.visuallithuanian.constants.ImageStore
import com.example.visuallithuanian.databinding.FragmentPractiseBinding
import com.example.visuallithuanian.model.PreferencesHelper


class PractiseFragment : Fragment() {
    lateinit var binding: FragmentPractiseBinding
    lateinit var practiseAdapter: PractiseAdapter
    lateinit var recyclerViewPractise: RecyclerView
    lateinit var preferencesHelper:PreferencesHelper

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPractiseBinding.inflate(layoutInflater, container, false)
        recyclerViewPractise = binding.recyclerViewPractise

        preferencesHelper = PreferencesHelper(requireContext())

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
        )

        binding.recyclerViewPractise.adapter = practiseAdapter
        practiseAdapter.initsetRecyclerView(recyclerViewPractise)

        return binding.root
    }
}
