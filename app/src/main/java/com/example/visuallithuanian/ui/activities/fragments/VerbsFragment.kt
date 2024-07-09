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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.visuallithuanian.R
import com.example.visuallithuanian.database.FlashcardPair
import com.example.visuallithuanian.databinding.FragmentVerbsBinding
import com.example.visuallithuanian.model.PreferencesHelper
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.ToLearnViewModel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class VerbsFragment : Fragment() {

    private lateinit var binding: FragmentVerbsBinding
    private lateinit var viewModel: BottomNavigationViewModel
    private lateinit var preferencesHelper: PreferencesHelper
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var currentTriple: Map.Entry<String, Triple<String, Int, Int>>
    private lateinit var mediaPlayer: MediaPlayer

    private val sharedPrefFile = "com.example.visuallithuanian.PREFERENCE_FILE_KEY"
    private val counterViewModel: ToLearnViewModel by viewModels()
    private val cardViewModel: FlashCardViewmodel by viewModels {
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }

    private val hashMap = hashMapOf(
        "to invite" to Triple("pakviesti", R.drawable.toinvite, R.raw.whatkas),
        "to hear" to Triple("girdėti", R.drawable.tohear, R.raw.whatkas),
        "see" to Triple("matyti", R.drawable.tosee, R.raw.whatkas),
        "turn" to Triple("posūkis", R.drawable.turn1, R.raw.whatkas),
        "allow" to Triple("leisti", R.drawable.allow1, R.raw.whatkas),
        "prepared" to Triple("paruošti", R.drawable.prepared1, R.raw.whatkas),
        "to finish" to Triple("baigti", R.drawable.finish1, R.raw.whatkas),
        "to lose" to Triple("prarasti", R.drawable.tolose1, R.raw.whatkas),
        "to educate" to Triple("šviesti", R.drawable.toeducate1, R.raw.whatkas),
        "to sit" to Triple("sėdėti", R.drawable.tosit, R.raw.whatkas),
        "to sing" to Triple("dainuoti", R.drawable.tosing, R.raw.whatkas),
        "to dance" to Triple("šokti", R.drawable.todance1, R.raw.whatkas),
        "to paint" to Triple("tapyti", R.drawable.topaint, R.raw.whatkas),
        "laugh" to Triple("juoktis", R.drawable.laugh, R.raw.whatkas),
        "to walk" to Triple("vaikščioti", R.drawable.walk, R.raw.whatkas),
        "to collapse" to Triple("žlugti", R.drawable.tocollapse, R.raw.whatkas),
        "to get" to Triple("gauti", R.drawable.toget1, R.raw.whatkas),
        "to earn" to Triple("uždirbti", R.drawable.toearn1, R.raw.whatkas),
        "wasted" to Triple("švaistyti", R.drawable.wasted, R.raw.whatkas),
        "to sell" to Triple("parduoti", R.drawable.tosell, R.raw.whatkas),
        "kick" to Triple("spardyti", R.drawable.kick, R.raw.whatkas),
        "hug" to Triple("apkabinti", R.drawable.hug, R.raw.whatkas),
        "to sink" to Triple("skęsti", R.drawable.tosink1, R.raw.whatkas),
        "send" to Triple("siųsti", R.drawable.send1, R.raw.whatkas),
        "to leave" to Triple("palikti", R.drawable.leave, R.raw.whatkas),
        "write" to Triple("Rašyti", R.drawable.write, R.raw.whatkas),
        "cry" to Triple("verkti", R.drawable.tocry, R.raw.whatkas),
        "to sleep" to Triple("miegoti", R.drawable.sleep, R.raw.whatkas)
    )

    private var currentTripleIndex = 0
    private var isFront = true
    private val totalTriples = hashMap.size

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVerbsBinding.inflate(inflater, container, false)

        initializeViews()
        setupListeners()
        setupMediaPlayer()
        restoreSavedState()

        return binding.root
    }

    private fun initializeViews() {
        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity())[BottomNavigationViewModel::class.java]
        bottomNavigationView.visibility = View.GONE

        preferencesHelper = PreferencesHelper(requireContext())

        binding.progressHorizontal.progressTintList = ColorStateList.valueOf(
            ContextCompat.getColor(requireContext(), R.color.orange1)
        )
        binding.progressHorizontal.progressBackgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(requireContext(), R.color.silver)
        )
    }

    private fun setupListeners() {
        binding.backIcon?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_verbsFragment_to_sentenceFragment)
        }

        binding.imageFlashCard.setOnClickListener {
            handleFlashCardClick()
        }

        binding.imageFlashCardSaveWhite.setOnClickListener {
            handleFlashCardSaveClick()
        }

        binding.cardLearning.setOnClickListener {
            flipCard()
        }

        binding.btnPlay.setOnClickListener {
            playAudio()
        }
    }

    private fun setupMediaPlayer() {
        mediaPlayer = MediaPlayer().apply {
            setOnPreparedListener {
                start()
            }
        }
    }

    private fun restoreSavedState() {
        val savedCounter = preferencesHelper.getCounter()
        val savedProgress = preferencesHelper.getProgress()
        counterViewModel.setCounter(savedCounter)
        binding.progressHorizontal.progress = savedProgress
        currentTripleIndex = (savedProgress * totalTriples) / 100
        updateCurrentTriple()
    }

    private fun handleFlashCardClick() {
        with(binding) {
            imageFlashCard.visibility = View.GONE
            imageFlashCardSaveWhite.visibility = View.VISIBLE
        }

        val tripleIdentifier = getCurrentTripleIdentifier()
        if (!preferencesHelper.isItemSaved(tripleIdentifier)) {
            preferencesHelper.addSavedItem(tripleIdentifier)
            val triple = FlashcardPair(currentTriple.key, currentTriple.value.first, currentTriple.value.second, currentTriple.value.third)
            cardViewModel.insertCards(triple)
            Log.d("Main", "$triple")
        } else {
            Log.d("Main", "Item already saved: $tripleIdentifier")
        }

        counterViewModel.incrementCounter()
        binding.textCardTolearn.text = counterViewModel.counter.value.toString()
        updateCurrentTriple()
    }

    private fun handleFlashCardSaveClick() {
        with(binding) {
            imageFlashCardSaveWhite.visibility = View.GONE
            imageFlashCard.visibility = View.VISIBLE

            if (currentTripleIndex >= 0 && currentTripleIndex < hashMap.size) {
                val removedTriple = hashMap.entries.elementAt(currentTripleIndex)
                hashMap.remove(removedTriple.key)

                counterViewModel.decrementCounter()
                val triple = FlashcardPair(currentTriple.key, currentTriple.value.first, currentTriple.value.second, currentTriple.value.third)
                cardViewModel.deleteCards(triple)
                Log.d("Main", "$triple")
                updateCurrentTriple()
            }
        }
    }

    private fun flipCard() {
        with(binding) {
            if (isFront) {
                isFront = false
                textCardBack.visibility = View.VISIBLE
                textCardFront.visibility = View.VISIBLE
                imageFlashCard.visibility = View.VISIBLE
            } else {
                currentTripleIndex = (currentTripleIndex + 1) % hashMap.size
                textCardFront.visibility = View.VISIBLE
                textCardBack.visibility = View.VISIBLE
                imageFlashCard.visibility = View.VISIBLE
            }

            cardViewQuestions.setCardBackgroundColor(
                ContextCompat.getColor(requireContext(),
                    if (currentTripleIndex % 2 == 0) R.color.orange1 else R.color.new_design_text_color)
            )

            val progress = ((currentTripleIndex + 1) * 100) / totalTriples
            binding.progressHorizontal.progress = progress
            saveProgress(progress)
            updateCurrentTriple()
        }
    }

    private fun playAudio() {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(
            requireContext(),
            Uri.parse("android.resource://${requireContext().packageName}/${currentTriple.value.third}")
        )
        mediaPlayer.prepareAsync()
    }

    private fun updateCurrentTriple() {
        currentTriple = hashMap.entries.elementAt(currentTripleIndex)
        with(binding) {
            textCardFront.text = currentTriple.key
            textCardBack.text = currentTriple.value.first
            imagecardsHelper.setImageResource(currentTriple.value.second)
        }
    }

    private fun getCurrentTripleIdentifier(): String {
        val front = binding.textCardFront.text.toString()
        val back = binding.textCardBack.text.toString()
        val imageHelper = currentTriple.value.second
        val voiceClip = currentTriple.value.third
        return "$front-$back-$imageHelper-$voiceClip"
    }

    private fun saveProgress(progress: Int) {
        val sharedPreferences = requireActivity().getSharedPreferences(sharedPrefFile, AppCompatActivity.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putInt("progress", progress)
            apply()
        }
    }
}
