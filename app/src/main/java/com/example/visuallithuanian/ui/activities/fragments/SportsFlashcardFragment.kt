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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.visuallithuanian.R
import com.example.visuallithuanian.database.FlashcardPair
import com.example.visuallithuanian.databinding.FragmentSportsFlashcardBinding
import com.example.visuallithuanian.model.MediumProgressPreferencesHelper
import com.example.visuallithuanian.model.PreferencesHelper
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.ToLearnViewModel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


class SportsFlashcardFragment : Fragment() {
    lateinit var binding: FragmentSportsFlashcardBinding
    lateinit var viewModel: BottomNavigationViewModel


    lateinit var bottomNavigationView: BottomNavigationView
    private val counterViewModel: ToLearnViewModel by viewModels()
    private val hashMap = HashMap<String, Triple<String, Int, Int>>()

    private var currentTripleIndex = 0
    private lateinit var currentTriple: Map.Entry<String, Triple<String, Int, Int>>

    var isFront = true
    private val totalTriples = 39 // change the value to the actual number of entries in your hashMap
    private lateinit var preferencesHelper: PreferencesHelper
    private lateinit var mediumProgressPreferencesHelper: MediumProgressPreferencesHelper
    // declaring viewmodel
    private val cardViewModel: FlashCardViewmodel by viewModels {
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSportsFlashcardBinding.inflate(inflater,container,false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity())[BottomNavigationViewModel::class.java]

        bottomNavigationView.visibility = View.GONE

        preferencesHelper = PreferencesHelper(requireContext())
        mediumProgressPreferencesHelper = MediumProgressPreferencesHelper((requireContext()))
        // Restore saved progress and counter
        val savedCounter = preferencesHelper.getCounter()
        val savedProgress = mediumProgressPreferencesHelper.getProgressSports()
        counterViewModel.setCounter(savedCounter) // Assuming ToLearnViewModel has a method to set counter

        // setting up listener for back Icon
        binding.backIcon?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_sportsFlashcardFragment_to_sentenceFragment)
        }

        // changing color of progress bar progress
        binding.progressHorizontal.progressTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                requireContext(),
                R.color.float1
            )
        )

        // changing color of background color of progress bar
        binding.progressHorizontal.progressBackgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                requireContext(),
                R.color.silver
            ))

        // Hashmap of strings that will shown on cardview front and back side
        hashMap["final"] = Triple("galutinis", R.drawable.tofinal, R.raw.final1)
        hashMap["pool"] = Triple("baseinas", R.drawable.pool, R.raw.pool)
        hashMap["champion"] = Triple("čempionas", R.drawable.champion, R.raw.champion)
        hashMap["Quarter"] = Triple("ketvirtis", R.drawable.quarterly, R.raw.quarter)
        hashMap["to get injured"] = Triple("susižeisti", R.drawable.injured, R.raw.togetinjured)
        hashMap["field"] = Triple("laukas", R.drawable.field, R.raw.field)
        hashMap["player"] = Triple("žaidėjas", R.drawable.player, R.raw.player)
        hashMap["decathlon competition"] = Triple("dešimtkovės varžybos", R.drawable.decthalon, R.raw.decathloncompetion)
        hashMap["darts"] = Triple("smiginis", R.drawable.dart, R.raw.darts)
        hashMap["to whistle"] = Triple("švilpti", R.drawable.whistle, R.raw.whistle)
        hashMap["winner"] = Triple("Nugalėtojas", R.drawable.winner, R.raw.winner)
        hashMap["captain"] = Triple("kapitonas", R.drawable.captain, R.raw.captain)
        hashMap["slide"] = Triple("čiuožykla", R.drawable.slide, R.raw.slide)
        hashMap["yoga"] = Triple("joga", R.drawable.yoga, R.raw.yoga)
        hashMap["team"] = Triple("komanda", R.drawable.team, R.raw.team)
        hashMap["baseball"] = Triple("beisbolas", R.drawable.baseball, R.raw.baseball)
        hashMap["to play sports"] = Triple("Sportuoti", R.drawable.toplaysports, R.raw.toplaysports)
        hashMap["to overcome"] = Triple("įveikti", R.drawable.overcome, R.raw.tovercome)
        hashMap["judge"] = Triple("teisėjas", R.drawable.judge, R.raw.judge)
        hashMap["triathlon"] = Triple("triatlonas", R.drawable.triathlon, R.raw.triathlon)
        hashMap["to win"] = Triple("laimėti", R.drawable.towin, R.raw.towin)
        hashMap["basketball"] = Triple("krepšinis", R.drawable.basketball, R.raw.basketball)
        hashMap["trainer"] = Triple("treneris", R.drawable.trainer, R.raw.trainer)
        hashMap["exercise"] = Triple("pratimas", R.drawable.excersice, R.raw.excercise)
        hashMap["quarterfinals"] = Triple("ketvirtfinalis", R.drawable.quarterfinals, R.raw.quarterfinals)
        hashMap["viewer"] = Triple("žiūrovas", R.drawable.viewer, R.raw.viewer)
        hashMap["trophy"] = Triple("trofėjus", R.drawable.trophy, R.raw.trophy)
        hashMap["world Championship"] = Triple("pasaulio čempionatas", R.drawable.worldchampionship, R.raw.worldchampionship)
        hashMap["sports equipment"] = Triple("sporto įranga", R.drawable.sportsequipment, R.raw.sportsequipment)
        hashMap["game"] = Triple("žaidimas", R.drawable.game, R.raw.game)
        hashMap["fitness gym"] = Triple("treniruoklių salė", R.drawable.gym, R.raw.fitnessgym)
        hashMap["stadium"] = Triple("stadionas", R.drawable.stadium, R.raw.stadium)
        hashMap["manual"] = Triple("rankinis", R.drawable.manual, R.raw.manual)
        hashMap["aerobics"] = Triple("aerobika", R.drawable.aerobic, R.raw.aerobics)
        hashMap["weightlifting"] = Triple("sunkioji atletika", R.drawable.weightlifting, R.raw.weightlifting)
        hashMap["injury"] = Triple("sužalojimas", R.drawable.injury, R.raw.injury)
        hashMap["skis"] = Triple("slidės", R.drawable.skis, R.raw.skis)
        hashMap["badminton"] = Triple("badmintonas", R.drawable.badminton, R.raw.badminton)
        hashMap["tennis court"] = Triple("teniso kortas", R.drawable.tenniscourt, R.raw.tenniscourt)



        // Initialize Media Player
        val mediaPlayer = MediaPlayer()

        // Restore progress bar progress
        binding.progressHorizontal.progress = savedProgress
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
        //Displaying GIF image on the screen
        Glide.with(this).asGif().load(R.drawable.finger1).into(binding.gifImageView)

        //Navigating from one fragment to another
        binding.gifImageView.setOnClickListener {
            findNavController().navigate(R.id.action_sportsFlashcardFragment_to_toLearnFlashCards)
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
                mediumProgressPreferencesHelper.savedProgressSports(progress) // Save progress

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
}