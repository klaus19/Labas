package com.example.visuallithuanian.ui.activities.fragments


import android.annotation.SuppressLint
import com.example.visuallithuanian.adapter.PractiseAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.Utils.shuffleList
import com.example.visuallithuanian.constants.ImageStore
import com.example.visuallithuanian.databinding.FragmentPractiseBinding

class PractiseFragment : Fragment() {
    lateinit var binding: FragmentPractiseBinding
    lateinit var practiseAdapter: PractiseAdapter
    lateinit var  recyclerViewPractise: RecyclerView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentPractiseBinding.inflate(layoutInflater,container,false)
        recyclerViewPractise = binding.recyclerViewPractise


        val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.recyclerViewPractise.layoutManager = layoutManager

        binding.backIcon.setOnClickListener {
            findNavController().navigate(com.example.visuallithuanian.R.id.action_practiseFragment_to_userFragment)
        }


        // Shuffle the list of image names

        val shuffledImageResources = ImageStore.imagesNamesMap.keys.toList().take(4).shuffleList()

        val shuffledImageNames1: List<Pair<String, String>> = shuffledImageResources.mapNotNull { it ->
            val pair = ImageStore.imagesNamesMap[it]
            pair?.let { Pair(pair.first,pair.second)}
        }.toList().take(4).shuffleList()


        practiseAdapter = PractiseAdapter(shuffledImageResources.toMutableList(),
            shuffledImageNames1.toMutableList(),binding.btnShuffle,recyclerViewPractise)

        binding.recyclerViewPractise.adapter = practiseAdapter
        practiseAdapter.initsetRecyclerView(recyclerViewPractise)

        return binding.root

    }

}