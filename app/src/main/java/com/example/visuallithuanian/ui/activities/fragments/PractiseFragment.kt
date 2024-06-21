package com.example.visuallithuanian.ui.activities.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.PractiseAdapter
import com.example.visuallithuanian.constants.ImageStore
import com.example.visuallithuanian.databinding.FragmentPractiseBinding
import com.example.visuallithuanian.model.PreferencesHelper
import com.example.visuallithuanian.viewModel.CounterViewmodel
import com.example.visuallithuanian.viewModel.ToLearnViewModel

class PractiseFragment : Fragment() {
    private lateinit var binding: FragmentPractiseBinding
    private lateinit var practiseAdapter: PractiseAdapter
    private lateinit var recyclerViewPractise: RecyclerView
    private lateinit var preferencesHelper: PreferencesHelper
    private var counter = 0
    private var counterDiamond = 0
    private var counterGem = 0
    private var counterLearn = 0 // Counter for items to learn
    private var counterLearnt = 0 // Counter for learnt items
    private lateinit var counterViewModel: CounterViewmodel

    @SuppressLint("ClickableViewAccessibility", "UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPractiseBinding.inflate(layoutInflater, container, false)
        recyclerViewPractise = binding.recyclerViewPractise

        preferencesHelper = PreferencesHelper(requireContext())
        counter = preferencesHelper.getCounter()
        counterDiamond = preferencesHelper.getDiamondCounter()

        binding.textCounter.text = counter.toString()
        binding.textCounterDiamond.text = counterDiamond.toString()
        binding.textCounterGem.text = counterGem.toString()


        ImageStore.loadFromPreferences(requireContext())

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewPractise.layoutManager = layoutManager

        binding.backIcon.setOnClickListener {
            findNavController().navigate(R.id.action_practiseFragment_to_userFragment)
        }

        val randomPairs = ImageStore.getRandomPairs(4)

        val imageResources = randomPairs.map { it.first }.toMutableList()
        val imageNames1 = randomPairs.map { it.second }.toMutableList()

        practiseAdapter = PractiseAdapter(
            imageResources,
            imageNames1,
            binding.btnShuffle,
            recyclerViewPractise,
            preferencesHelper,
            this::incrementCounter,
            this::removeCorrectPairFromImageStore,
            this::handleNoCardsVisibility // Ensure the callback is properly referenced
        )

        binding.recyclerViewPractise.adapter = practiseAdapter
        practiseAdapter.initsetRecyclerView(recyclerViewPractise)

        // Initial visibility check
        handleNoCardsVisibility()

        // Shuffle cards after the recyclerView has been initialized
        practiseAdapter.shuffleCards()

        return binding.root
    }

    private fun incrementCounter() {
        counter++
        preferencesHelper.saveCounter(counter)
        binding.textCounter.text = counter.toString()

        // Check if counter reaches a multiple of 50
        if (counter % 20 == 0) {
            counterDiamond += 2
            preferencesHelper.saveDiamondCounter(counterDiamond)
            binding.textCounterDiamond.text = counterDiamond.toString()

            // Check if counterDiamond reaches a multiple of 10
            if (counterDiamond % 10 == 0) {
                counterGem++
                binding.textCounterGem.text = counterGem.toString()
            }
        }
    }

    private fun removeCorrectPairFromImageStore(resId: Int) {
        ImageStore.removeImageResource(resId)
        ImageStore.saveToPreferences(requireContext())
    }

    private fun handleNoCardsVisibility() {
        if (practiseAdapter.itemCount == 0) {
            binding.noCardsLayout.visibility = View.VISIBLE
        } else {
            binding.noCardsLayout.visibility = View.GONE
        }
    }
}