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
import com.example.visuallithuanian.databinding.FragmentDailyBasicBinding
import com.example.visuallithuanian.model.PreferencesHelper
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.ToLearnViewModel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class DailyBasic : Fragment() {
    lateinit var binding: FragmentDailyBasicBinding
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

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDailyBasicBinding.inflate(inflater,container,false)

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
            findNavController().navigate(R.id.action_dailyBasic_to_sentenceFragment)
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
            )
        )
        hashMap["What is it?"] = Triple("Kas tai?", R.drawable.what1, R.raw.whatisit_)
        hashMap["this is English newspaper"] = Triple("tai yra angliškas laikraštis",
            R.drawable.newspaper,
            R.raw.thisisenglishnewspaper
        )
        hashMap["Japanese food"] = Triple("Japonų maistas",
            R.drawable.japanesefood,
            R.raw.japanesefood
        )
        hashMap["hot coffee"] = Triple("karšta kava", R.drawable.hotcoffee, R.raw.hotcoffee)
        hashMap["I learn Lithuanian"] = Triple("Aš mokausi lietuvių kalbos",
            R.drawable.languages,
            R.raw.ilearnlithuanian
        )
        hashMap["to eat"] = Triple("valgyti", R.drawable.eat, R.raw.toeat)
        hashMap["I eat japanese food"] = Triple("Aš valgau japonišką maistą",
            R.drawable.eat,
            R.raw.letsjapanese
        )
        hashMap["Would you like some tea?"] = Triple("Ar norėtumete arbatos?",
            R.drawable.tea1,
            R.raw.wouldyoulikesometea
        )
        hashMap["Can you repeat it again?"] = Triple("Ar galite pakartoti dar kartą?",
            R.drawable.repeat,
            R.raw.canyourepeatagain
        )
        hashMap["Of course"] = Triple("Žinoma", R.drawable.ofcourse, R.raw.ofcourse)
        hashMap["rice"] = Triple("ryžiai", R.drawable.rice, R.raw.rice)
        hashMap["soup"] = Triple("sriuba", R.drawable.soup1, R.raw.soup)
        hashMap["Bread"] = Triple("Duona", R.drawable.bread1, R.raw.bread1)
        hashMap["water"] = Triple("vanduo", R.drawable.water1, R.raw.water)
        hashMap["What are you reading?"] = Triple("Ką skaitote?",
            R.drawable.whatreading,
            R.raw.whatareyoureading
        )
        hashMap["to cost"] = Triple("kainuoti", R.drawable.cost, R.raw.tocost)
        hashMap["a cup of coffee"] = Triple("puodelis kavos",
            R.drawable.cupcoffee,
            R.raw.cupofcoffee
        )
        hashMap["apple"] = Triple("obuolys", R.drawable.apple1, R.raw.apple1)
        hashMap["She drinks hot coffee"] = Triple("Ji geria karštą kavą",
            R.drawable.coffee1,
            R.raw.shedrinkshotcoffee
        )
        hashMap["a glass"] = Triple("stiklinė", R.drawable.glass1, R.raw.aglass)
        hashMap["What do you buy?"] = Triple("Ką perkate?", R.drawable.buy11, R.raw.whatdoyoubuy)
        hashMap["parents"] = Triple("tėvai", R.drawable.parents1, R.raw.parents)
        hashMap["classmate"] = Triple("klasiokas", R.drawable.classmate, R.raw.classmate)
        hashMap["my friends"] = Triple("Mano draugai", R.drawable.friends1, R.raw.myfriends)
        hashMap["a little"] = Triple("šiek tiek", R.drawable.little, R.raw.little)
        hashMap["Let's go"] = Triple("eikime!", R.drawable.letsgo, R.raw.letsgo)
        hashMap["more"] = Triple("daugiau", R.drawable.more, R.raw.more)
        hashMap["key"] = Triple("raktas", R.drawable.key, R.raw.key11)
        hashMap["what are you doing?"] = Triple("ką darote?",
            R.drawable.todo,
            R.raw.whatareyoudoing
        )
        hashMap["hand"] = Triple("ranka", R.drawable.hand, R.raw.hand)
        hashMap["to stop"] = Triple("nustoti", R.drawable.stopsign, R.raw.tostop)

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

        binding.cardLearning.setOnClickListener {
            findNavController().navigate(R.id.action_dailyBasic_to_toLearnFlashCards)
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