package com.example.visuallithuanian

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.visuallithuanian.adapter.ToLearnWordsAdapter
import com.example.visuallithuanian.custom.OverlappingLayoutManager
import com.example.visuallithuanian.databinding.FragmentToLearnWordsBinding
import com.example.visuallithuanian.model.PreferencesHelper

class FragmentToLearnWords : Fragment() {
    private lateinit var binding: FragmentToLearnWordsBinding
    private lateinit var preferencesHelper: PreferencesHelper
    private lateinit var learnedWordsAdapter: ToLearnWordsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToLearnWordsBinding.inflate(inflater, container, false)
        initView()

        binding.backIcon.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentToLearnWords_to_settingsFragment)
        }

        return binding.root
    }

    private fun initView() {
        preferencesHelper = PreferencesHelper(requireContext())

        // Fetch saved items from preferences
        val learnedWords = preferencesHelper.getSavedItemCardPairToLearn() // Assuming this returns List<FlashcardPair>

        // Initialize adapter with fetched list
        learnedWordsAdapter = ToLearnWordsAdapter()
        learnedWordsAdapter.submitList(learnedWords)

        // Set up RecyclerView with adapter and layout manager
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerview.adapter = learnedWordsAdapter

        Glide.with(this).asGif().load(R.drawable.dumpster).into(binding.imageTrash)

        // Handle visibility of no_cards_layout
        binding.noCardsLayout.visibility = if (learnedWords.isEmpty()) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}
