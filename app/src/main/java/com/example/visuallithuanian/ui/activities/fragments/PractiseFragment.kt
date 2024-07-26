package com.example.visuallithuanian.ui.activities.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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
    private var counterDiamond = 0
    private var counterGem = 0


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

        binding.textCounterFire.text = counter.toString()
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
            this::handleNoCardsVisibility,  // Ensure the callback is properly referenced,
            binding.textCounterFire,
            this::updateTextCountFire

        )

        binding.recyclerViewPractise.adapter = practiseAdapter
        practiseAdapter.initsetRecyclerView(recyclerViewPractise)

        // Initial visibility check
        handleNoCardsVisibility()

        updateSharedPreferences()

        // Shuffle cards after the recyclerView has been initialized
        practiseAdapter.shuffleCards()

        return binding.root
    }

    private fun updateSharedPreferences() {
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("counter", counter)
        editor.putInt("counterDiamond", counterDiamond)
        editor.putInt("counterGem", counterGem)
        editor.apply()
    }

    fun updateTextCountFire(newValue:Int){
        binding.textCounterFire.text = newValue.toString()
        saveTextCount(newValue)
        val intent = Intent("com.example.UPDATE_TEXT_COUNT")
        intent.putExtra("textCount", newValue)
        requireContext().sendBroadcast(intent)
    }

    private fun saveTextCount(newValue: Int) {
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("textCount", newValue)
        editor.apply()
    }

    private fun incrementCounter() {
        counter++
        preferencesHelper.saveCounter(counter)
        binding.textCounterFire.text = counter.toString()

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
        updateSharedPreferences()
        Log.d("PractiseFragment", "Counters updated: counter=$counter, counterDiamond=$counterDiamond, counterGem=$counterGem")
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