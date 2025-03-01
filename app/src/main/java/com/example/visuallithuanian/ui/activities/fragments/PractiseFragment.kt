package com.example.visuallithuanian.ui.activities.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
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
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import kotlin.random.Random

class PractiseFragment : Fragment() {
    private lateinit var binding: FragmentPractiseBinding
    private lateinit var practiseAdapter: PractiseAdapter
    private lateinit var recyclerViewPractise: RecyclerView
    private lateinit var preferencesHelper: PreferencesHelper
    private var counter = 0
    private var counterDiamond = 0
    private var counterGem = 0
    private var rewardedAd: RewardedAd? = null
    val duration = 60 * 60 * 1000L  // 1 hour
    val interval = 1000L // Update every second (1000 ms)

    @SuppressLint("ClickableViewAccessibility", "UseCompatLoadingForDrawables", "SetTextI18n")
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

        //Push this
        binding.freegift?.setOnClickListener {
            getRewards()
            // Show loading indicator
            binding.freegift?.isEnabled = false
            binding.freegift?.alpha = 0.5f  // Dim the button to indicate loading

            // Start the countdown timer
            object :CountDownTimer(duration,interval){
                override fun onTick(millisUntilFinished: Long) {
                    // Calculate the current alpha value (gradual increase)
                    val progress = (duration - millisUntilFinished).toFloat() / duration
                    val alphaValue = 0.5f + (0.5f * progress) // Range: 0.5 to 1.0
                    binding.freegift?.alpha = alphaValue
                }

                override fun onFinish() {
                    // Fully enable the button when the timer is complete
                    binding.freegift?.alpha = 1.0f
                    binding.freegift?.isEnabled = true
                }

            }.start()

        }

        return binding.root
    }
    //Push this
    private fun getRewards() {
        RewardedAd.load(
            requireContext(),
            "ca-app-pub-2048328349524526/1123399071",
            AdRequest.Builder().build(),
            object : RewardedAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedAd) {
                    rewardedAd = ad
                    showRewardedAd()
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    Log.d("RewardAd", "Failed to load rewarded ad: ${error.message}")
                    rewardedAd = null
                    // Reset button state on failure
                    binding.freegift?.alpha = 1.0f
                }
            }
        )
    }

    // Push this
    private fun showRewardedAd() {
        rewardedAd?.let { ad ->
            ad.show(requireActivity()) { rewardItem ->
                val sharedPreferences: SharedPreferences =
                    requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                val currentFireCount = sharedPreferences.getInt("textCount", 0)
                val newFireCount = currentFireCount + getRandomNumber()
                updateTextCountFire(newFireCount)
                Log.d("RewardAd", "User rewarded with: ${rewardItem.amount}, new fire count: $newFireCount")
                // Disable button for one hour
                disableGiftButtonForOneHour()
            }
            // Reset button state after ad is displayed
            ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    binding.freegift?.alpha = 1.0f  // Restore button opacity
                }

                override fun onAdFailedToShowFullScreenContent(error: com.google.android.gms.ads.AdError) {
                    Log.d("RewardAd", "Ad failed to show: ${error.message}")
                    binding.freegift?.alpha = 1.0f  // Restore button opacity
                }

                override fun onAdShowedFullScreenContent() {
                    // Ad is being shown; this might not be needed but ensures the flow is complete
                    rewardedAd = null
                }
            }
        } ?: run {
            Log.d("RewardAd", "Rewarded ad is not ready")
            // Reset button state if the ad is not ready
            binding.freegift?.alpha = 1.0f
        }
    }

    private fun getRandomNumber(): Int {
        return Random.nextInt(1,4)

    }

    private fun disableGiftButtonForOneHour() {
        // Disable the button
        binding.freegift?.isEnabled = false
        binding.freegift?.alpha = 0.5f

        // Save the current timestamp
        val sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val currentTimeMillis = System.currentTimeMillis()
        editor.putLong("freeGiftLastClickedTime", currentTimeMillis)
        editor.apply()

        // Start a 1-hour timer
        val oneHourMillis = 60 * 60 * 1000L


        object : CountDownTimer(oneHourMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = ((oneHourMillis - millisUntilFinished).toFloat() / oneHourMillis * 100).toInt()
            }

            override fun onFinish() {
                binding.freegift?.isEnabled = true
                binding.freegift?.alpha = 1.0f
            }
        }.start()
    }

    override fun onResume() {
        super.onResume()
        initializeGiftButton()
        loadTextCountFire()
        loadTextCountPurple()
        loadTextCountRed()
    }

    private fun initializeGiftButton() {
        val sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val lastClickedTime = sharedPreferences.getLong("freeGiftLastClickedTime", 0L)
        val currentTimeMillis = System.currentTimeMillis()
        val oneHourMillis = 60 * 60 * 1000L

        if (currentTimeMillis - lastClickedTime < oneHourMillis) {
            val remainingTime = oneHourMillis - (currentTimeMillis - lastClickedTime)
            binding.freegift?.isEnabled = false
            binding.freegift?.alpha = 0.5f


            object : CountDownTimer(remainingTime, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val progress = ((oneHourMillis - millisUntilFinished).toFloat() / oneHourMillis * 100).toInt()
                }

                override fun onFinish() {
                    binding.freegift?.isEnabled = true
                    binding.freegift?.alpha = 1.0f
                }
            }.start()
        } else {
            binding.freegift?.isEnabled = true
            binding.freegift?.alpha = 1.0f
        }
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