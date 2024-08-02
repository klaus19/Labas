package com.example.visuallithuanian.ui.activities.fragments

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.visuallithuanian.R
import com.example.visuallithuanian.constants.TimeSingleton
import com.example.visuallithuanian.database.FlashcardPair
import com.example.visuallithuanian.databinding.FragmentTimeFlashcardBinding
import com.example.visuallithuanian.model.MediumProgressPreferencesHelper
import com.example.visuallithuanian.model.PreferencesHelper
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.ToLearnViewModel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class TimeFlashcardFragment : Fragment() {

    lateinit var binding: FragmentTimeFlashcardBinding
    lateinit var viewModel: BottomNavigationViewModel

    lateinit var bottomNavigationView: BottomNavigationView
    private val counterViewModel: ToLearnViewModel by viewModels()

    private var currentTripleIndex = 0
    private lateinit var currentTriple: Map.Entry<String, Triple<String, Int, Int>>
    private lateinit var flashPreferencesHelper: MediumProgressPreferencesHelper

    var isFront = true
    private val totalTriples = 36 // change the value to the actual number of entries in your hashMap
    private lateinit var preferencesHelper: PreferencesHelper

    // declaring viewmodel
    private val cardViewModel: FlashCardViewmodel by viewModels {
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }

    @SuppressLint("ResourceType", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimeFlashcardBinding.inflate(inflater, container, false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity())[BottomNavigationViewModel::class.java]

        bottomNavigationView.visibility = View.GONE

        preferencesHelper = PreferencesHelper(requireContext())
        flashPreferencesHelper = MediumProgressPreferencesHelper(requireContext())

        // Restore saved progress and counter
        val savedCounter = preferencesHelper.getCounter()
        val savedProgress = flashPreferencesHelper.getProgressTime()
        counterViewModel.setCounter(savedCounter) // Assuming ToLearnViewModel has a method to set counter

        // Set initial progress based on savedProgress
        currentTripleIndex = (savedProgress * totalTriples) / 100

        // Update ProgressBar
        binding.progressHorizontal.progress = savedProgress

        // setting up listener for back Icon
        binding.backIcon.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_timeFlashcardFragment_to_sentenceFragment)
        }

        // changing color of progress bar progress
        binding.progressHorizontal.progressTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                requireContext(), R.color.float1
            )
        )

        // changing color of background color of progress bar
        binding.progressHorizontal.progressBackgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(requireContext(), R.color.silver)
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

        // Initialize the first triple
        currentTriple = TimeSingleton.hashMapTime.entries.elementAt(currentTripleIndex)
        updateUIWithCurrentTriple()

        // onclick listener on the image to save the image for learning
        binding.imageFlashCard.setOnClickListener {
            binding.imageFlashCard.visibility = View.GONE
            binding.imageFlashCardSaveWhite.visibility = View.VISIBLE

            // Save the current flashcard pair without moving to the next pair
            counterViewModel.incrementCounter()
            // Save the updated counter
            preferencesHelper.saveCounter(counterViewModel.counter.value ?: 0)

            // Save the flashcard pair
            saveFlashcardPair()

            // Log the saved flashcard pair for debugging purposes
            Log.d("Main", "Saved flashcard pair: $currentTriple")
        }

        // On Event of clicking on the image to unsave the image
        binding.imageFlashCardSaveWhite.setOnClickListener {
            binding.imageFlashCardSaveWhite.visibility = View.GONE
            binding.imageFlashCard.visibility = View.VISIBLE

            if (currentTripleIndex >= 0 && currentTripleIndex < TimeSingleton.hashMapTime.size) {
                // Remove the item at the current index from your data structure (e.g., HashMap)
                val removedTriple = TimeSingleton.hashMapTime.entries.elementAt(currentTripleIndex)
                TimeSingleton.hashMapTime.remove(removedTriple.key)

                // Decrease the counter
                counterViewModel.decrementCounter()
                deleteFlashcardPair()
                currentTriple = TimeSingleton.hashMapTime.entries.elementAt(currentTripleIndex)
                updateUIWithCurrentTriple()
            }
        }

        // Displaying GIF image on the screen
        Glide.with(this).asGif().load(R.drawable.finger1).into(binding.gifImageView)

        // Navigating from one fragment to another
        binding.gifImageView.setOnClickListener {
            findNavController().navigate(R.id.action_timeFlashcardFragment_to_toLearnFlashCards)
        }

        // onclick listener for the Flip button
        binding.btnFlip.setOnClickListener {
            binding.imageFlashCardSaveWhite.visibility = View.GONE
            binding.imageFlashCard.visibility = View.VISIBLE

            // Update currentTripleIndex first
            currentTripleIndex = (currentTripleIndex + 1) % TimeSingleton.hashMapTime.size

            // Update card background color based on index
            val cardColor = if (currentTripleIndex % 2 == 0) {
                ContextCompat.getColor(requireContext(), R.color.orange1)
            } else {
                ContextCompat.getColor(requireContext(), R.color.new_design_text_color)
            }
            binding.cardViewQuestions.setCardBackgroundColor(cardColor)

            // Update progress bar
            val progress = ((currentTripleIndex + 1) * 100) / totalTriples
            binding.progressHorizontal.progress = progress

            // Save the updated progress
            flashPreferencesHelper.savedProgressTime(progress)

            currentTriple = TimeSingleton.hashMapTime.entries.elementAt(currentTripleIndex)
            updateUIWithCurrentTriple()
        }

        return binding.root
    }

    private fun updateUIWithCurrentTriple() {
        binding.textCardFront.text = currentTriple.key
        binding.textCardBack.text = currentTriple.value.first
        binding.imagecardsHelper.setImageResource(currentTriple.value.second)
        binding.btnPlay.setImageResource(currentTriple.value.third)
    }

    private fun saveFlashcardPair() {
        val front = binding.textCardFront.text.toString()
        val back = binding.textCardBack.text.toString()
        val imageHelper = currentTriple.value.second
        val voiceClip = currentTriple.value.third
        val flashcardPair = FlashcardPair(front, back, imageHelper, voiceClip)
        cardViewModel.insertCards(flashcardPair)
        Log.d("Main", "$flashcardPair")
    }

    private fun deleteFlashcardPair() {
        val front = binding.textCardFront.text.toString()
        val back = binding.textCardBack.text.toString()
        val imageHelper = currentTriple.value.second
        val voiceClip = currentTriple.value.third
        val flashcardPair = FlashcardPair(front, back, imageHelper, voiceClip)
        cardViewModel.deleteCards(flashcardPair)
        Log.d("Main", "$flashcardPair")
    }
}
