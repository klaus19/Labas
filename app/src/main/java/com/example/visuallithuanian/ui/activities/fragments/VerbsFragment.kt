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

    lateinit var binding: FragmentVerbsBinding
    lateinit var viewModel: BottomNavigationViewModel

    private val sharedPrefFile = "com.example.visuallithuanian.PREFERENCE_FILE_KEY"
    lateinit var bottomNavigationView: BottomNavigationView
    private val counterViewModel: ToLearnViewModel by viewModels()

    private val hashMap = HashMap<String,Triple<String,Int,Int>>()

    private var currentTripleIndex =0
    private lateinit var currentTriple:Map.Entry<String,Triple<String,Int,Int>>

    var isFront=true
    private val totalTriples = 37 // change the value to the actual number of entries in your hashMap
    private lateinit var preferencesHelper: PreferencesHelper
    // declaring viewmodel
    private val cardViewModel: FlashCardViewmodel by viewModels {
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVerbsBinding.inflate(inflater, container, false)

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
            findNavController().navigate(R.id.action_verbsFragment_to_sentenceFragment)
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
        hashMap["be"] = Triple("būti", R.drawable.be, R.raw.whatkas)
        hashMap["to read"] = Triple("skaityti", R.drawable.read1, R.raw.whatkas)
        hashMap["knock"] = Triple("belsti", R.drawable.kock, R.raw.whatkas)
        hashMap["to travel"] = Triple("keliauti", R.drawable.totravel1, R.raw.whatkas)
        hashMap["think about it"] = Triple("pagalvok", R.drawable.thinkabout, R.raw.whatkas)
        hashMap["go"] = Triple("eik", R.drawable.go, R.raw.whatkas)
        hashMap["to have"] = Triple("turėti", R.drawable.have, R.raw.whatkas)
        hashMap["to drink"] = Triple("gerti", R.drawable.drink, R.raw.whatkas)
        hashMap["to draw"] = Triple("piešti", R.drawable.draw1, R.raw.whatkas)
        hashMap["to start"] = Triple("pradėti", R.drawable.start1, R.raw.whatkas)

        hashMap["training"] = Triple("mokymo", R.drawable.training1, R.raw.whatkas)
        hashMap["to speak"] = Triple("kalbėti", R.drawable.speak11, R.raw.whatkas)
        hashMap["to learn"] = Triple("mokytis", R.drawable.tolearn, R.raw.whatkas)
        hashMap["to buy"] = Triple("pirkti", R.drawable.tobuy1, R.raw.whatkas)
        hashMap["to use"] = Triple("naudoti", R.drawable.use1, R.raw.whatkas)
        hashMap["work"] = Triple("dirbti", R.drawable.work11, R.raw.whatkas)
        hashMap["to eat"] = Triple("valgyti", R.drawable.eat11, R.raw.whatkas)
        hashMap["to find"] = Triple("rasti", R.drawable.tofind, R.raw.whatkas)
        hashMap["to continue"] = Triple("tęsti", R.drawable.continue1, R.raw.whatkas)
        hashMap["to say"] = Triple("pasakyti", R.drawable.tosay, R.raw.whatkas)

        hashMap["to clean"] = Triple("valyti", R.drawable.toclean, R.raw.whatkas)
        hashMap["smoking"] = Triple("rūkyti", R.drawable.smoking, R.raw.whatkas)
        hashMap["to wait"] = Triple("palaukti", R.drawable.towait1, R.raw.whatkas)
        hashMap["to ride"] = Triple("važiuoti", R.drawable.toride1, R.raw.whatkas)
        hashMap["enter"] = Triple("įveskite", R.drawable.entrance, R.raw.whatkas)
        hashMap["to put"] = Triple("įdėti", R.drawable.toput, R.raw.whatkas)
        hashMap["to meet"] = Triple("susitikti", R.drawable.meetyou, R.raw.whatkas)
        hashMap["take it"] = Triple("imk", R.drawable.take, R.raw.whatkas)
        hashMap["to fly"] = Triple("skristi", R.drawable.fly, R.raw.whatkas)
        hashMap["Close"] = Triple("Uždaryti", R.drawable.close, R.raw.whatkas)

        hashMap["to live"] = Triple("gyventi", R.drawable.tolive, R.raw.whatkas)
        hashMap["say"] = Triple("sakyk", R.drawable.tosay, R.raw.whatkas)
        hashMap["to stay"] = Triple("pasilikti", R.drawable.tostay, R.raw.whatkas)
        hashMap["to save"] = Triple("sutaupyti", R.drawable.tosave, R.raw.whatkas)
        hashMap["to swim"] = Triple("plaukti", R.drawable.toswim1, R.raw.whatkas)
        hashMap["to run"] = Triple("bėgti", R.drawable.torun, R.raw.whatkas)
        hashMap["to try"] = Triple("bandyti", R.drawable.totry1, R.raw.whatkas)
        hashMap["to do"] = Triple("padaryti", R.drawable.todo, R.raw.whatkas)
        hashMap["boiled"] = Triple("virti", R.drawable.boiled, R.raw.whatkas)
        hashMap["to create"] = Triple("sukurti", R.drawable.tocreate, R.raw.whatkas)

        hashMap["to invite"] = Triple("pakviesti", R.drawable.toinvite, R.raw.whatkas)
        hashMap["to hear"] = Triple("girdėti", R.drawable.tohear, R.raw.whatkas)
        hashMap["see"] = Triple("matyti", R.drawable.tosee, R.raw.whatkas)
        hashMap["turn"] = Triple("posūkis", R.drawable.turn1, R.raw.whatkas)
        hashMap["allow"] = Triple("leisti", R.drawable.allow1, R.raw.whatkas)
        hashMap["prepared"] = Triple("paruošti", R.drawable.prepared1, R.raw.whatkas)
        hashMap["to finish"] = Triple("baigti", R.drawable.finish1, R.raw.whatkas)
        hashMap["to lose"] = Triple("prarasti", R.drawable.tolose1, R.raw.whatkas)
        hashMap["to educate"] = Triple("šviesti", R.drawable.toeducate1, R.raw.whatkas)
        hashMap["to sit"] = Triple("sėdėti", R.drawable.tosit, R.raw.whatkas)

        hashMap["to sing"] = Triple("dainuoti", R.drawable.tosing, R.raw.whatkas)
        hashMap["to dance"] = Triple("šokti", R.drawable.todance1, R.raw.whatkas)
        hashMap["to paint"] = Triple("tapyti", R.drawable.topaint, R.raw.whatkas)
        hashMap["laugh"] = Triple("juoktis", R.drawable.laugh, R.raw.whatkas)
        hashMap["to walk"] = Triple("vaikščioti", R.drawable.walk, R.raw.whatkas)
        hashMap["to collapse"] = Triple("žlugti", R.drawable.tocollapse, R.raw.whatkas)
        hashMap["to get"] = Triple("gauti", R.drawable.toget1, R.raw.whatkas)
        hashMap["to earn"] = Triple("uždirbti", R.drawable.toearn1, R.raw.whatkas)
        hashMap["wasted"] = Triple("švaistyti", R.drawable.wasted, R.raw.whatkas)
        hashMap["to sell"] = Triple("parduoti", R.drawable.tosell, R.raw.whatkas)

        hashMap["kick"] = Triple("spardyti", R.drawable.kick, R.raw.whatkas)
        hashMap["hug"] = Triple("apkabinti", R.drawable.hug, R.raw.whatkas)
        hashMap["to sink"] = Triple("skęsti", R.drawable.tosink1, R.raw.whatkas)
        hashMap["send"] = Triple("siųsti", R.drawable.send1, R.raw.whatkas)
        hashMap["to leave"] = Triple("palikti", R.drawable.leave, R.raw.whatkas)
        hashMap["write"] = Triple("Rašyti", R.drawable.write, R.raw.whatkas)
        hashMap["cry"] = Triple("verkti", R.drawable.tocry, R.raw.whatkas)
        hashMap["to sleep"] = Triple("miegoti", R.drawable.sleep, R.raw.whatkas)

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

        //Go to another fragment
        binding.cardLearning.setOnClickListener {

            findNavController().navigate(R.id.action_verbsFragment_to_toLearnFlashCards)

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