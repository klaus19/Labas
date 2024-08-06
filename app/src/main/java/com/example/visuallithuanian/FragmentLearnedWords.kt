package com.example.visuallithuanian

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.visuallithuanian.databinding.FragmentLearnedWordsBinding
import com.example.visuallithuanian.model.PreferencesHelper


class FragmentLearnedWords : Fragment() {

    private lateinit var binding: FragmentLearnedWordsBinding
    private lateinit var preferencesHelper: PreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLearnedWordsBinding.inflate(inflater,container,false)
        initView()
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        val learnedWords = preferencesHelper
    }

    private fun initView() {
        preferencesHelper = PreferencesHelper(requireContext())
    }


}