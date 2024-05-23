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

class PractiseFragment : Fragment() {
    lateinit var binding: FragmentPractiseBinding
    lateinit var practiseAdapter: PractiseAdapter
    lateinit var recyclerViewPractise: RecyclerView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPractiseBinding.inflate(layoutInflater, container, false)
        recyclerViewPractise = binding.recyclerViewPractise

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewPractise.layoutManager = layoutManager

        binding.backIcon.setOnClickListener {
            findNavController().navigate(com.example.visuallithuanian.R.id.action_practiseFragment_to_userFragment)
        }

        val shuffledImageResources = ImageStore.imagesNamesMap.keys.shuffled().take(4).toMutableList()
        val shuffledImageNames1 = ImageStore.imagesNamesMap.values.shuffled().take(4).toMutableList()

        practiseAdapter = PractiseAdapter(
            shuffledImageResources,
            shuffledImageNames1,
            binding.btnShuffle,
            recyclerViewPractise
        )

        binding.recyclerViewPractise.adapter = practiseAdapter
        practiseAdapter.initsetRecyclerView(recyclerViewPractise)

        return binding.root
    }
}
