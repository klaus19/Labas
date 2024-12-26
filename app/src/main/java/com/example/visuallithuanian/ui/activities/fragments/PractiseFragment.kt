package com.example.visuallithuanian.ui.activities.fragments

import android.annotation.SuppressLint
import android.content.Context
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
import com.bumptech.glide.Glide
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.PractiseAdapter
import com.example.visuallithuanian.constants.ImageStore
import com.example.visuallithuanian.databinding.FragmentPractiseBinding
import com.example.visuallithuanian.model.PreferencesHelper
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class PractiseFragment : Fragment() {
    private lateinit var binding: FragmentPractiseBinding
    private lateinit var practiseAdapter: PractiseAdapter
    private lateinit var recyclerViewPractise: RecyclerView
    private lateinit var preferencesHelper: PreferencesHelper
    private var counter = 0
    private var counterDiamond = 0
    private var counterGem = 0
    private var rewardedAd: RewardedAd? = null

    @SuppressLint("ClickableViewAccessibility", "UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPractiseBinding.inflate(layoutInflater, container, false)
        recyclerViewPractise = binding.recyclerViewPractise

        preferencesHelper = PreferencesHelper(requireContext())
        counter = preferencesHelper.getCounter()
        counterDiamond = preferencesHelper.getPurpleCounter()
        counterGem = preferencesHelper.getRedCounter()

        binding.textCounterFire.text = counter.toString()
        binding.textCounterPurple.text = counterDiamond.toString()
        binding.textCounterRed.text = counterGem.toString()

        ImageStore.loadFromPreferences(requireContext())

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
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
            binding.recyclerViewPractise,
            preferencesHelper,
            this::removeCorrectPairFromImageStore,
            this::handleNoCardsVisibility,
            binding.textCounterFire,
            binding.textCounterPurple,
            binding.textCounterRed,
            this::updateTextCountFire,
            this::updateTextCountPurple,
            this::updateTextCountRed
        )

        binding.recyclerViewPractise.adapter = practiseAdapter
        practiseAdapter.initsetRecyclerView(recyclerViewPractise)

        handleNoCardsVisibility()
        updateSharedPreferences()
        practiseAdapter.shuffleCards()

        loadTextCountFire()
        loadTextCountPurple()
        loadTextCountRed()

        Glide.with(this).asGif().load(R.drawable.dumpster).into(binding.imageTrash)
        binding.freegift?.let { Glide.with(this).asGif().load(R.drawable.freegift).into(it) }

        binding.freegift?.setOnClickListener { getRewards() }

        return binding.root
    }

    private fun getRewards() {
        RewardedAd.load(
            requireContext(),
            "ca-app-pub-3940256099942544/5224354917",
            AdRequest.Builder().build(),
            object : RewardedAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedAd) {
                    rewardedAd = ad
                    showRewardedAd()
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    Log.d("RewardAd", "Failed to load rewarded ad: ${error.message}")
                    rewardedAd = null
                }
            }
        )
    }

    private fun showRewardedAd() {
        rewardedAd?.let { ad ->
            ad.show(requireActivity()) { rewardItem ->
                val sharedPreferences: SharedPreferences =
                    requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                val currentFireCount = sharedPreferences.getInt("textCount", 0)
                val newFireCount = currentFireCount + 3
                updateTextCountFire(newFireCount)
                Log.d("RewardAd", "User rewarded with: ${rewardItem.amount}, new fire count: $newFireCount")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadTextCountFire()
        loadTextCountPurple()
        loadTextCountRed()
    }

    override fun onDestroy() {
        rewardedAd = null
        super.onDestroy()
    }

    private fun loadTextCountFire() {
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val savedCount = sharedPreferences.getInt("textCount", 0)
        binding.textCounterFire.text = savedCount.toString()
    }

    private fun loadTextCountPurple() {
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val saveCount = sharedPreferences.getInt("textCountPurple", 0)
        binding.textCounterPurple.text = saveCount.toString()
    }

    private fun loadTextCountRed() {
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val saveCount = sharedPreferences.getInt("textCountRed", 0)
        binding.textCounterRed.text = saveCount.toString()
    }

    private fun updateSharedPreferences() {
        val sharedPreferences =
            requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("counter", counter)
        editor.putInt("counterDiamond", counterDiamond)
        editor.putInt("counterGem", counterGem)
        editor.apply()
    }

    private fun updateTextCountFire(newValue: Int) {
        binding.textCounterFire.text = newValue.toString()
        saveTextCount(newValue)
        val intent = Intent("com.example.UPDATE_TEXT_COUNT")
        intent.putExtra("textCount", newValue)
        requireContext().sendBroadcast(intent)
    }

    private fun updateTextCountPurple(newValue: Int) {
        binding.textCounterPurple.text = newValue.toString()
        saveTextCountPurple(newValue)
    }

    private fun updateTextCountRed(newValue: Int) {
        binding.textCounterRed.text = newValue.toString()
        saveTextCountRed(newValue)
    }

    private fun saveTextCount(newValue: Int) {
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("textCount", newValue)
        editor.apply()
    }

    private fun saveTextCountPurple(newValue: Int) {
        val sharedPreferences =
            requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("textCountPurple", newValue)
        editor.apply()
    }

    private fun saveTextCountRed(newValue: Int) {
        val sharedPreferences =
            requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("textCountRed", newValue)
        editor.apply()
    }

    private fun removeCorrectPairFromImageStore(resId: Int) {
        ImageStore.removeImageResource(resId)
        ImageStore.saveToPreferences(requireContext())
    }

    private fun handleNoCardsVisibility() {
        if (practiseAdapter.itemCount == 0) {
            binding.noCardsLayout.visibility = View.VISIBLE
            binding.btnShuffle.visibility = View.GONE
        } else {
            binding.noCardsLayout.visibility = View.GONE
            binding.btnShuffle.visibility = View.VISIBLE
        }
    }
}
