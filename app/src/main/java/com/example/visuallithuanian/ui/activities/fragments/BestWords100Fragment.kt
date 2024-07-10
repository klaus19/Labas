package com.example.visuallithuanian.ui.activities.fragments

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.visuallithuanian.R
import com.example.visuallithuanian.constants.BestWords100Singleton
import com.example.visuallithuanian.database.FlashcardPair
import com.example.visuallithuanian.databinding.FragmentBestWords100Binding
import com.example.visuallithuanian.model.PreferencesHelper
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.ToLearnViewModel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class BestWords100Fragment : Fragment() {

    lateinit var binding: FragmentBestWords100Binding
    lateinit var viewModel: BottomNavigationViewModel

    lateinit var bottomNavigationView: BottomNavigationView
    private val counterViewModel: ToLearnViewModel by viewModels()

    private var currentTripleIndex = 0
    private lateinit var currentTriple: Map.Entry<String, Triple<String, Int, Int>>

    var isFront = true
    private val totalTriples = 117 // change the value to the actual number of entries in your hashMap
    private lateinit var preferencesHelper: PreferencesHelper
    // declaring viewmodel
    private val cardViewModel: FlashCardViewmodel by viewModels {
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }

    @SuppressLint("ResourceType", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBestWords100Binding.inflate(inflater, container, false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity())[BottomNavigationViewModel::class.java]

        bottomNavigationView.visibility = View.GONE

        preferencesHelper = PreferencesHelper(requireContext())
        // Restore saved progress and counter
        val savedCounter = preferencesHelper.getCounter()
        val savedProgress = preferencesHelper.getProgress()
        counterViewModel.setCounter(savedCounter) // Assuming ToLearnViewModel has a method to set counter

        // Restore progress bar progress and set the currentTripleIndex based on saved progress
        binding.progressHorizontal.progress = savedProgress
        currentTripleIndex = (savedProgress * totalTriples) / 100
        if (currentTripleIndex >= BestWords100Singleton.hashMapbestwords.size) {
            currentTripleIndex = 0
        }

        // setting up listener for back Icon
        binding.backIcon?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_bestWords100Fragment_to_sentenceFragment)
        }

        // changing color of progress bar progress
        binding.progressHorizontal.progressTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                requireContext(), R.color.float1
            )
        )

        // changing color of background color of progress bar
        binding.progressHorizontal.progressBackgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                requireContext(),
                R.color.silver
            )
        )

        // Initialize Media Player
        val mediaPlayer = MediaPlayer()
        binding.btnPlay.setOnClickListener {
            // get the audio resource ID from currentTriple
            val audioResource = currentTriple.value.third
            mediaPlayer.apply {
                reset()
                // Set the audio resource using the context and resource ID
                setDataSource(
                    requireContext(),
                    Uri.parse("android.resource://${requireContext().packageName}/$audioResource")
                )

                // Prepare the MediaPlayer asynchronously
                prepareAsync()
            }
            // Set an OnPreparedListener to start playing when the media is prepared
            mediaPlayer.setOnPreparedListener {
                it.start()
            }
        }

        counterViewModel.counter.observe(requireActivity()) { count ->
            binding.textCardTolearn.text = count.toString()
        }

        // Initialize currentTriple with the entry based on the saved progress
        currentTriple = BestWords100Singleton.hashMapbestwords.entries.elementAt(currentTripleIndex)
        updateCardViews(currentTriple)

        // onclick listener on the image to save the image for learning
        binding.imageFlashCard.setOnClickListener {
            handleFlashCardClick(true)
        }

        // On Event of clicking on the image to unsave the image
        binding.imageFlashCardSaveWhite.setOnClickListener {
            handleFlashCardClick(false)
        }

        // Navigating from one fragment to another
        binding.cardLearning.setOnClickListener {
            findNavController().navigate(R.id.action_bestWords100Fragment_to_toLearnFlashCards)
        }

        // onclick listener for the Flip button
        binding.btnFlip.setOnClickListener {
            handleFlipButtonClick()
        }

        return binding.root
    }

    private fun handleFlashCardClick(isSave: Boolean) {
        with(binding) {
            if (isSave) {
                imageFlashCard.visibility = View.GONE
                imageFlashCardSaveWhite.visibility = View.VISIBLE
                counterViewModel.incrementCounter()
                preferencesHelper.saveCounter(counterViewModel.counter.value ?: 0)
                cardViewModel.insertCards(createFlashcardFromCurrentTriple())
            } else {
                imageFlashCardSaveWhite.visibility = View.GONE
                imageFlashCard.visibility = View.VISIBLE
                counterViewModel.decrementCounter()
                preferencesHelper.saveCounter(counterViewModel.counter.value ?: 0)
                cardViewModel.deleteCards(createFlashcardFromCurrentTriple())
            }
            currentTripleIndex++
            if (currentTripleIndex >= BestWords100Singleton.hashMapbestwords.size) {
                currentTripleIndex = 0
            }
            currentTriple = BestWords100Singleton.hashMapbestwords.entries.elementAt(currentTripleIndex)
            updateCardViews(currentTriple)
        }
    }

    private fun handleFlipButtonClick() {
        with(binding) {
            imageFlashCardSaveWhite.visibility = View.GONE
            imageFlashCard.visibility = View.VISIBLE
            currentTripleIndex = (currentTripleIndex + 1) % BestWords100Singleton.hashMapbestwords.size
            updateProgress()
            currentTriple = BestWords100Singleton.hashMapbestwords.entries.elementAt(currentTripleIndex)
            updateCardViews(currentTriple)
        }
    }

    private fun updateProgress() {
        val progress = ((currentTripleIndex + 1) * 100) / totalTriples
        binding.progressHorizontal.progress = progress
        preferencesHelper.saveProgress(progress)
    }

    private fun createFlashcardFromCurrentTriple(): FlashcardPair {
        return FlashcardPair(
            binding.textCardFront.text.toString(),
            binding.textCardBack.text.toString(),
            currentTriple.value.second,
            currentTriple.value.third
        )
    }

    private fun updateCardViews(triple: Map.Entry<String, Triple<String, Int, Int>>) {
        with(binding) {
            textCardFront.text = triple.key
            textCardBack.text = triple.value.first
            imagecardsHelper.setImageResource(triple.value.second)
            btnPlay.setImageResource(triple.value.third)
        }
    }
}
