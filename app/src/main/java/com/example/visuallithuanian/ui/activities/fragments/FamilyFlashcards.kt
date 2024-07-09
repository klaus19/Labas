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
import com.example.visuallithuanian.databinding.FragmentFamilyFlashcardsBinding
import com.example.visuallithuanian.model.PreferencesHelper
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.ToLearnViewModel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class FamilyFlashcards : Fragment() {


    lateinit var binding:FragmentFamilyFlashcardsBinding
    lateinit var viewModel: BottomNavigationViewModel

    private val sharedPrefFile = "com.example.visuallithuanian.PREFERENCE_FILE_KEY"
    lateinit var bottomNavigationView: BottomNavigationView
    private val counterViewModel: ToLearnViewModel by viewModels()
    private val hashMap = HashMap<String, Triple<String, Int, Int>>()

    private var currentTripleIndex = 0
    private lateinit var currentTriple: Map.Entry<String, Triple<String, Int, Int>>

    var isFront=true
    private val totalTriples = 37 // change the value to the actual number of entries in your hashMap
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
        binding = FragmentFamilyFlashcardsBinding.inflate(inflater,container,false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity())[BottomNavigationViewModel::class.java]

        bottomNavigationView.visibility = View.GONE

        preferencesHelper = PreferencesHelper(requireContext())
        // Restore saved progress and counter
        val savedCounter = preferencesHelper.getCounter()
        val savedProgress = preferencesHelper.getProgress()
        counterViewModel.setCounter(savedCounter) // Assuming ToLearnViewModel has a method to set counter

        // setting up listener for back Icon
        binding.backIcon?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_familyFlashcards_to_sentenceFragment)
        }

        // changing color of progress bar progress
        binding.progressHorizontal.progressTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                requireContext(),
                R.color.orange1
            )
        )

        // changing color of background color of progress bar
        binding.progressHorizontal.progressBackgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                requireContext(),
                R.color.silver
            ))

        // Hashmap of strings that will shown on cardview front and back side
        hashMap["Father"] = Triple("Tėvas", R.drawable.dad1, R.raw.sleep)
        hashMap["Mother"] = Triple("Motina", R.drawable.mother, R.raw.sleep)
        hashMap["Brother"] = Triple("brolis", R.drawable.brother, R.raw.sleep)
        hashMap["Sister"] = Triple("sesuo", R.drawable.sister, R.raw.sleep)
        hashMap["my family"] = Triple("Mano šeima", R.drawable.family1, R.raw.sleep)
        hashMap["grandfather"] = Triple("senelis", R.drawable.grandfather, R.raw.sleep)
        hashMap["grandmother"] = Triple("močiutė", R.drawable.grandmother, R.raw.sleep)
        hashMap["friend"] = Triple("draugė (-as)", R.drawable.friend1, R.raw.sleep)
        hashMap["friends"] = Triple("draugai", R.drawable.friends, R.raw.sleep)
        hashMap["Couple"] = Triple("pora", R.drawable.couple, R.raw.sleep)

        hashMap["Uncle"] = Triple("dėdė", R.drawable.uncle, R.raw.sleep)
        hashMap["Aunt"] = Triple("teta", R.drawable.aunt, R.raw.sleep)
        hashMap["cousin"] = Triple("pusbrolis", R.drawable.cousin, R.raw.sleep)
        hashMap["relatives"] = Triple("giminaičiai", R.drawable.relatives1, R.raw.sleep)
        hashMap["son"] = Triple("sūnus", R.drawable.son1, R.raw.sleep)
        hashMap["daughter"] = Triple("dukra", R.drawable.daughter1, R.raw.sleep)
        hashMap["child"] = Triple("vaikas", R.drawable.child, R.raw.sleep)
        hashMap["children"] = Triple("vaikai", R.drawable.children, R.raw.sleep)
        hashMap["husband"] = Triple("vyras", R.drawable.husband, R.raw.sleep)
        hashMap["wife"] = Triple("žmona", R.drawable.wife, R.raw.sleep)

        hashMap["grandson"] = Triple("anūkas", R.drawable.grandson, R.raw.grandson)
        hashMap["granddaughter"] = Triple("anūkė", R.drawable.granddaughter, R.raw.grandaughter)
        hashMap["husband's mother"] = Triple("anyta",
            R.drawable.husbandsmother,
            R.raw.husbandsmother
        )
        hashMap["mother-in-law"] = Triple("uošvienė", R.drawable.motherinlaw, R.raw.motherinlaw)
        hashMap["widow"] = Triple("našlys", R.drawable.widow, R.raw.widow)
        hashMap["married(m)"] = Triple("vedes", R.drawable.marriedman, R.raw.married)
        hashMap["married(w)"] = Triple("ištekėjusi", R.drawable.marriedwoman, R.raw.married1)
        hashMap["single"] = Triple("nevedęs / netekejusi", R.drawable.single, R.raw.single)
        hashMap["to marry(m)"] = Triple("vesti - veda - vedė", R.drawable.tomarryman, R.raw.tomarry)
        hashMap["to marry(w)"] = Triple("išteketi - išteka - ištekėjo",
            R.drawable.tomarrywoman,
            R.raw.sleep
        )


        hashMap["to be born"] = Triple("gimti - gimsta - gimė", R.drawable.tobeborn, R.raw.tobeborn)
        hashMap["close relative"] = Triple("Artimas giminAitis",
            R.drawable.closerelatives,
            R.raw.closerelative
        )
        hashMap["distant relative"] = Triple("tOlimas giminAitis",
            R.drawable.distantrelatives,
            R.raw.distantrelative
        )
        hashMap["a person"] = Triple("asmuo", R.drawable.aperson, R.raw.aperson)
        hashMap["my head"] = Triple("mano galva", R.drawable.myhead, R.raw.myhead)
        hashMap["headache"] = Triple("galvos skausmas", R.drawable.headache, R.raw.headache)
        hashMap["fever"] = Triple("karščiavimas", R.drawable.fever, R.raw.fever)
        hashMap["Tooth"] = Triple("dantis", R.drawable.tooth, R.raw.tooth)
        hashMap["Toothache"] = Triple("dantų skausmas", R.drawable.toothache, R.raw.toothache)
        hashMap["her hair"] = Triple("jos plaukai", R.drawable.herhair, R.raw.herhair)
        hashMap["soft skin"] = Triple("švelni oda", R.drawable.softskin, R.raw.softskin)

        hashMap["this baby"] = Triple("šis kūdikis", R.drawable.baby, R.raw.thisbaby)
        hashMap["her legs"] = Triple("jos kojos", R.drawable.leg, R.raw.herlegs)
        hashMap["own body"] = Triple("savo kūnas", R.drawable.ownbody, R.raw.ownbody)
        hashMap["your arm"] = Triple("tavo ranka", R.drawable.arm, R.raw.yourarm)
        hashMap["Appearance"] = Triple("išvaizda", R.drawable.appearance, R.raw.appearance)


        // Initialize Media Player
        val mediaPlayer = MediaPlayer()
        binding.btnPlay.setOnClickListener {
            // get the audio resource ID from currentTriple
            val audioResource = currentTriple.value.third
            mediaPlayer.apply {
                reset()
                // Set the audio resource using the context and resource ID
                setDataSource(requireContext(), Uri.parse("android.resource://${requireContext().packageName}/$audioResource"))

                // Prepare the MediaPlayer asynchronously
                prepareAsync()
            }
            // Set an OnPreparedListener to start playing when the media is prepared
            mediaPlayer.setOnPreparedListener {
                it.start()
            }
        }

        currentTripleIndex = (savedProgress * totalTriples) / 100

        currentTriple = hashMap.entries.elementAt(currentTripleIndex)
        binding.textCardFront.text = currentTriple.key
        binding.textCardBack.text = currentTriple.value.first
        binding.imagecardsHelper.setImageResource(currentTriple.value.second)
        binding.btnPlay.setImageResource(currentTriple.value.third)

        binding.imageFlashCard.setOnClickListener {
            with(binding) {
                imageFlashCard.visibility = View.GONE
                imageFlashCardSaveWhite.visibility = View.VISIBLE
            }

            val front = binding.textCardFront.text.toString()
            val back = binding.textCardBack.text.toString()
            val imageHelper = currentTriple.value.second
            val voiceClip = currentTriple.value.third

            val tripleIdentifier = "$front-$back-$imageHelper-$voiceClip" // Create a unique identifier for the flashcard

            if (!preferencesHelper.isItemSaved(tripleIdentifier)) {
                preferencesHelper.addSavedItem(tripleIdentifier)
                val triple = FlashcardPair(front, back, imageHelper, voiceClip)
                cardViewModel.insertCards(triple)
                Log.d("Main", "$triple")
            } else {
                Log.d("Main", "Item already saved: $tripleIdentifier")
            }

            counterViewModel.incrementCounter()
            binding.textCardTolearn.text = counterViewModel.counter.value.toString()
            currentTriple = hashMap.entries.elementAt(currentTripleIndex)
        }

        binding.imageFlashCardSaveWhite.setOnClickListener {
            with(binding) {
                imageFlashCardSaveWhite.visibility = View.GONE
                imageFlashCard.visibility = View.VISIBLE

                if (currentTripleIndex >= 0 && currentTripleIndex < hashMap.size) {
                    val removedTriple = hashMap.entries.elementAt(currentTripleIndex)
                    hashMap.remove(removedTriple.key)

                    counterViewModel.decrementCounter()
                    val front = binding.textCardFront.text.toString()
                    val back = binding.textCardBack.text.toString()
                    val imageHelper = currentTriple.value.second
                    val voiceClip = currentTriple.value.third

                    val triple = FlashcardPair(front, back, imageHelper, voiceClip)
                    cardViewModel.deleteCards(triple)
                    Log.d("Main", "$triple")
                    currentTriple = hashMap.entries.elementAt(currentTripleIndex)
                }
            }
        }

        //Navigating from one fragment to another
        binding.cardLearning.setOnClickListener {
            findNavController().navigate(R.id.action_familyFlashcards_to_toLearnFlashCards)
        }

        // Restore progress bar progress
        binding.progressHorizontal.progress = savedProgress

        with(binding) {
            btnFlip.setOnClickListener {
                imageFlashCardSaveWhite.visibility = View.GONE
                imageFlashCard.visibility = View.VISIBLE

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

                if (currentTripleIndex % 2 == 0) {
                    cardViewQuestions.setCardBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.orange1
                        )
                    )
                } else {
                    cardViewQuestions.setCardBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.new_design_text_color
                        )
                    )
                }

                val progress = ((currentTripleIndex + 1) * 100) / totalTriples
                binding.progressHorizontal.progress = progress
                saveProgress(progress) // Save progress

                currentTriple = hashMap.entries.elementAt(currentTripleIndex)
                binding.textCardFront.text = currentTriple.key
                binding.textCardBack.text = currentTriple.value.first
                binding.imagecardsHelper.setImageResource(currentTriple.value.second)
                binding.btnPlay.setImageResource(currentTriple.value.third)
            }
        }

        // Set initial progress
        binding.progressHorizontal.progress = savedProgress

        return binding.root
    }

    private fun saveProgress(progress: Int) {
        val sharedPreferences = requireActivity().getSharedPreferences(sharedPrefFile, AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("progress", progress)
        editor.apply()
    }

    private fun getSavedProgress(): Int {
        val sharedPreferences = requireActivity().getSharedPreferences(sharedPrefFile, AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences.getInt("progress", 0)
    }
}