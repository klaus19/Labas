package com.example.visuallithuanian

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
 import com.example.visuallithuanian.adapter.LearnedWordsAdapter
import com.example.visuallithuanian.databinding.FragmentLearnedWordsBinding
import com.example.visuallithuanian.model.PreferencesHelper

class FragmentLearnedWords : Fragment() {

    private lateinit var binding: FragmentLearnedWordsBinding
    private lateinit var preferencesHelper: PreferencesHelper
    private lateinit var learnedWordsAdapter: LearnedWordsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLearnedWordsBinding.inflate(inflater, container, false)
        initView()
        setupRecyclerView()

        binding.backIcon.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentLearnedWords_to_settingsFragment)
        }


        return binding.root

    }

    private fun setupRecyclerView() {
        // Set up RecyclerView
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerview.adapter = learnedWordsAdapter
    }

    private fun initView() {
        preferencesHelper = PreferencesHelper(requireContext())

        // Fetch saved items from preferences
        val learnedWords = preferencesHelper.getSavedItemsCardPair()  // Assuming this returns List<FlashcardPair>

        // Initialize adapter with empty list
        learnedWordsAdapter = LearnedWordsAdapter()

        // Set up RecyclerView with adapter and layout manager
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerview.adapter = learnedWordsAdapter

        // Submit the list to the adapter
        learnedWordsAdapter.submitList(learnedWords)

        Glide.with(this).asGif().load(R.drawable.dumpster).into(binding.imageTrash)

        // Handle visibility of no_cards_layout
        if (learnedWords.isEmpty()) {
            binding.noCardsLayout.visibility = View.VISIBLE
        } else {
            binding.noCardsLayout.visibility = View.GONE
        }
    }

}
