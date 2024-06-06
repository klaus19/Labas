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
import com.example.visuallithuanian.R
import com.example.visuallithuanian.database.FlashcardPair
import com.example.visuallithuanian.databinding.FragmentSportsFlashcardBinding
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

    private val hashMap = HashMap<String,Triple<String,Int,Int>>()

    private var currentTripleIndex =0
    private lateinit var currentTriple:Map.Entry<String,Triple<String,Int,Int>>

    var isFront=true
    private val totalTriples = 43// change the value to the actual number of entries in your hashMap

    // declaring viewmodel
    private val cardViewModel: FlashCardViewmodel by viewModels {
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }


    @SuppressLint("ResourceType", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSportsFlashcardBinding.inflate(inflater,container,false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity())[BottomNavigationViewModel::class.java]


        bottomNavigationView.visibility = View.GONE


        // setting up listener for back Icon
        binding.backIcon?.setOnClickListener {
            activity?.onBackPressed()
        }

//        binding.floatingActionButton.setOnClickListener {
//            findNavController().navigate(R.id.action_dailyBasic_to_flashCards)
//        }
        //changing color of progress bar progress
        binding.progressHorizontal.progressTintList = ColorStateList.valueOf(
            ContextCompat.getColor(requireContext()
                , R.color.float1
            ))

        //changing color of background color of progress bar
        binding.progressHorizontal.progressBackgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(requireContext(),
                R.color.silver
            ))

        // Hashmap of strings that will shown on cardview front and back side
        hashMap["final"] = Triple("galutinis", R.drawable.tofinal, R.raw.sleep)
        hashMap["pool"] = Triple("baseinas", R.drawable.pool, R.raw.sleep)
        hashMap["champion"] = Triple("čempionas", R.drawable.champion, R.raw.sleep)
        hashMap["Quarterly"] = Triple("ketvirtis", R.drawable.quarterly, R.raw.sleep)
        hashMap["injured"] = Triple("susižeisti", R.drawable.injured, R.raw.sleep)
        hashMap["field"] = Triple("laukas", R.drawable.field, R.raw.sleep)
        hashMap["player"] = Triple("žaidėjas", R.drawable.player, R.raw.sleep)
        hashMap["decathlon competition"] = Triple("dešimtkovės varžybos",
            R.drawable.decthalon,
            R.raw.sleep
        )
        hashMap["darts"] = Triple("smiginis", R.drawable.dart, R.raw.sleep)
        hashMap["whistle"] = Triple("švilpti", R.drawable.whistle, R.raw.sleep)

        hashMap["Winner"] = Triple("Nugalėtojas", R.drawable.winner, R.raw.sleep)
        hashMap["captain"] = Triple("kapitonas", R.drawable.captain, R.raw.sleep)
        hashMap["slide"] = Triple("čiuožykla", R.drawable.slide, R.raw.sleep)
        hashMap["yoga"] = Triple("joga", R.drawable.yoga, R.raw.sleep)
        hashMap["team"] = Triple("komanda", R.drawable.team, R.raw.sleep)
        hashMap["baseball"] = Triple("beisbolas", R.drawable.baseball, R.raw.sleep)
        hashMap["to play sports"] = Triple("Sportuoti", R.drawable.toplaysports, R.raw.sleep)
        hashMap["to overcome"] = Triple("įveikti", R.drawable.overcome, R.raw.sleep)
        hashMap["judge"] = Triple("teisėjas", R.drawable.judge, R.raw.sleep)

        hashMap["triathlon"] = Triple("triatlonas", R.drawable.triathlon, R.raw.sleep)
        hashMap["to win"] = Triple("laimėti", R.drawable.towin, R.raw.sleep)
        hashMap["basketball"] = Triple("krepšinį", R.drawable.basketball, R.raw.sleep)
        hashMap["trainer"] = Triple("treneris", R.drawable.trainer, R.raw.sleep)
        hashMap["exercise"] = Triple("pratimas", R.drawable.excersice, R.raw.sleep)
        hashMap["quarterfinals"] = Triple("ketvirtfinalis", R.drawable.quarterfinals, R.raw.sleep)
        hashMap["viewer"] = Triple("žiūrovas", R.drawable.viewer, R.raw.sleep)
        hashMap["trophy"] = Triple("trofėjus", R.drawable.trophy, R.raw.sleep)
        hashMap["world Championship"] = Triple("pasaulio čempionatas",
            R.drawable.worldchampionship,
            R.raw.sleep
        )
        hashMap["sports equipment"] = Triple("sporto įranga",
            R.drawable.sportsequipment,
            R.raw.sleep
        )

        hashMap["game"] = Triple("žaidimas", R.drawable.game, R.raw.sleep)
        hashMap["fitness gym"] = Triple("treniruoklių salė", R.drawable.gym, R.raw.sleep)
        hashMap["stadium"] = Triple("stadionas", R.drawable.stadium, R.raw.sleep)
        hashMap["manual"] = Triple("rankinis", R.drawable.manual, R.raw.sleep)
        hashMap["aerobics"] = Triple("aerobika", R.drawable.aerobic, R.raw.sleep)
        hashMap["weightlifting"] = Triple("sunkiosios atletikos",
            R.drawable.weightlifting,
            R.raw.sleep
        )
        hashMap["injury"] = Triple("sužalojimas", R.drawable.injury, R.raw.sleep)
        hashMap["skis"] = Triple("slidės", R.drawable.skis, R.raw.sleep)
        hashMap["badminton"] = Triple("badmintonas", R.drawable.badminton, R.raw.sleep)
        hashMap["tennis court"] = Triple("teniso kortas", R.drawable.tenniscourt, R.raw.sleep)






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


        counterViewModel.counter.observe(requireActivity()){count->
            binding.textCounter.text = count.toString()
        }
        currentTriple = hashMap.entries.elementAt(currentTripleIndex)
        binding.textCardFront.text = currentTriple.key
        binding.textCardBack.text = currentTriple.value.first
        binding.imagecardsHelper.setImageResource(currentTriple.value.second)
        binding.btnPlay.setImageResource(currentTriple.value.third)

        // onclick listener on the image to save the image for learning
        binding.imageFlashCard.setOnClickListener {
            binding.imageFlashCard.visibility = View.GONE
            binding.imageFlashCardSaveWhite.visibility = View.VISIBLE

            counterViewModel.incrementCounter()
            // increment currentTripleIndex and get the next Triple
            currentTripleIndex++
            if (currentTripleIndex >= hashMap.size) {
                // if we have reached the end of the hashmap, start again from the beginning
                currentTripleIndex = 0
            }
            val front = binding.textCardFront.text.toString()
            val back = binding.textCardBack.text.toString()
            val imageHelper = currentTriple.value.second
            val voiceClip = currentTriple.value.third


            val Triple = FlashcardPair(front, back, imageHelper,voiceClip)
            cardViewModel.insertCards(Triple)
            //Toast.makeText(requireContext(),"saved data", Toast.LENGTH_SHORT).show()
            Log.d("Main","$Triple")
            currentTriple = hashMap.entries.elementAt(currentTripleIndex)

        }
        //On Event of clicking on the image to unsave the image
        binding.imageFlashCardSaveWhite.setOnClickListener {
            with(binding){
                imageFlashCardSaveWhite.visibility = View.GONE
                imageFlashCard.visibility = View.VISIBLE

                if (currentTripleIndex >= 0 && currentTripleIndex < hashMap.size) {
                    // Remove the item at the current index from your data structure (e.g., HashMap)
                    val removedTriple = hashMap.entries.elementAt(currentTripleIndex)
                    hashMap.remove(removedTriple.key)

                    // Decrease the counter
                    counterViewModel.decrementCounter()
                    val front = binding.textCardFront.text.toString()
                    val back = binding.textCardBack.text.toString()
                    val imageHelper = currentTriple.value.second
                    val voiceClip = currentTriple.value.third

                    val Triple = FlashcardPair(front, back, imageHelper,voiceClip)
                    cardViewModel.deleteCards(Triple)
                    //Toast.makeText(requireContext(),"saved data", Toast.LENGTH_SHORT).show()
                    Log.d("Main","$Triple")
                    currentTriple = hashMap.entries.elementAt(currentTripleIndex)

                }
            }
        }

        //Navigating from one fragment to another
        binding.cardLearning.setOnClickListener {
            findNavController().navigate(R.id.action_sportsFlashcardFragment_to_toLearnFlashCards)
        }

        //onclick listener for the Flip button
        with(binding) {
            btnFlip.setOnClickListener {
                imageFlashCardSaveWhite.visibility = View.GONE
                imageFlashCard.visibility = View.VISIBLE


                val progress = ((currentTripleIndex + 1) * 100) / totalTriples
                binding.progressHorizontal.progress = progress

                // initialize currentTripleIndex to 0 if it hasn't been initialized yet
                if (currentTripleIndex < 0) {
                    currentTripleIndex = 0
                }
                if (isFront) {
                    isFront = false
                    textCardBack.visibility = View.VISIBLE
                    textCardFront.visibility = View.VISIBLE
                    imageFlashCard.visibility = View.VISIBLE
                    cardViewQuestions.setCardBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.new_design_text_color
                    ))

                } else {
                    currentTripleIndex = (currentTripleIndex + 1) % hashMap.size
                    textCardFront.visibility = View.VISIBLE
                    textCardBack.visibility = View.VISIBLE
                    imageFlashCard.visibility = View.VISIBLE
                    cardViewQuestions.setCardBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.orange1
                    ))
                    isFront = true
                }
                // retrieve the current Triple from the hashMap
                currentTriple = hashMap.entries.elementAt(currentTripleIndex)
                binding.textCardFront.text = currentTriple.key
                binding.textCardBack.text = currentTriple.value.first
                binding.imagecardsHelper.setImageResource(currentTriple.value.second)
                binding.btnPlay.setImageResource(currentTriple.value.third)
            }
        }
        return binding.root

    }
}