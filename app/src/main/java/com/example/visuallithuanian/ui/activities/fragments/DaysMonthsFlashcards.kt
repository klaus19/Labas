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
import com.example.visuallithuanian.databinding.FragmentDaysMonthsFlashcardsBinding
import com.example.visuallithuanian.model.EasyPreferencesHelper
import com.example.visuallithuanian.model.PreferencesHelper
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.ToLearnViewModel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


class DaysMonthsFlashcards : Fragment() {

    lateinit var binding: FragmentDaysMonthsFlashcardsBinding
    lateinit var viewModel: BottomNavigationViewModel

    lateinit var bottomNavigationView: BottomNavigationView
    private val counterViewModel: ToLearnViewModel by viewModels()
    private val hashMap = HashMap<String, Triple<String, Int, Int>>()

    private var currentTripleIndex = 0
    private lateinit var currentTriple: Map.Entry<String, Triple<String, Int, Int>>

    var isFront=true
    private val totalTriples = 45 // change the value to the actual number of entries in your hashMap
    private lateinit var preferencesHelper: PreferencesHelper
    private lateinit var easyPreferencesHelper: EasyPreferencesHelper
    // declaring viewmodel
    private val cardViewModel: FlashCardViewmodel by viewModels {
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDaysMonthsFlashcardsBinding.inflate(inflater,container,false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity())[BottomNavigationViewModel::class.java]

        bottomNavigationView.visibility = View.GONE

        preferencesHelper = PreferencesHelper(requireContext())
        easyPreferencesHelper = EasyPreferencesHelper(requireContext())
        // Restore saved progress and counter
        val savedCounter = preferencesHelper.getCounter()
        val savedProgress = easyPreferencesHelper.getProgressDaysMonths()
        counterViewModel.setCounter(savedCounter) // Assuming ToLearnViewModel has a method to set counter

        // setting up listener for back Icon
        binding.backIcon?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_daysMonthsFlashcards_to_sentenceFragment)
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
        hashMap["Sunday"] = Triple("Sekmadienis", R.drawable.sunday, R.raw.sunday)
        hashMap["Monday"] = Triple("Pirmadienis", R.drawable.monday, R.raw.monday)
        hashMap["Tuesday"] = Triple("Antradienis", R.drawable.tuesday, R.raw.tuesday)
        hashMap["Wednesday"] = Triple("Trečiadienis", R.drawable.wednesday, R.raw.wednesday)
        hashMap["Thursday"] = Triple("Ketvirtadienis", R.drawable.thursday, R.raw.thursday)
        hashMap["Friday"] = Triple("penktadienis", R.drawable.friday, R.raw.friday)
        hashMap["Saturday"] = Triple("Šeštadienis", R.drawable.saturday, R.raw.saturday)
        hashMap["January"] = Triple("Sausis", R.drawable.january, R.raw.january)
        hashMap["February"] = Triple("Vasaris", R.drawable.february, R.raw.february)
        hashMap["March"] = Triple("Kovas", R.drawable.march, R.raw.march)
        hashMap["April"] = Triple("Balandis", R.drawable.april, R.raw.april)
        hashMap["May"] = Triple("Gegužė", R.drawable.may, R.raw.may)
        hashMap["June"] = Triple("Birželis", R.drawable.june, R.raw.june)
        hashMap["July"] = Triple("Liepa", R.drawable.july, R.raw.july)
        hashMap["August"] = Triple("Rugpjūtis", R.drawable.august, R.raw.august)
        hashMap["September"] = Triple("Rugsėjis", R.drawable.september, R.raw.september)
        hashMap["October"] = Triple("Spalis", R.drawable.october, R.raw.october)
        hashMap["November"] = Triple("lapkritis", R.drawable.november, R.raw.november)
        hashMap["December"] = Triple("Gruodis", R.drawable.december, R.raw.december)
        hashMap["Spring"] = Triple("pavasaris", R.drawable.sleep, R.raw.spring)
        hashMap["Summer"] = Triple("vasara", R.drawable.summer, R.raw.vasara)
        hashMap["autumn"] = Triple("ruduo", R.drawable.autumn, R.raw.ruduo)
        hashMap["winter"] = Triple("žiema", R.drawable.winter, R.raw.ziema)
        hashMap["day"] = Triple("diena", R.drawable.day, R.raw.diena)
        hashMap["Month"] = Triple("Mėnuo", R.drawable.month, R.raw.menuo)
        hashMap["Year"] = Triple("metai", R.drawable.year, R.raw.metai)
        hashMap["Season"] = Triple("sezonas", R.drawable.season, R.raw.sezonas)
        hashMap["decade"] = Triple("dešimtmetį", R.drawable.decade, R.raw.desimtmeti)
        hashMap["Midnight"] = Triple("vidurnaktis", R.drawable.midnight, R.raw.vidurnaktis)
        hashMap["half a day"] = Triple("pusę dienos", R.drawable.halfaday, R.raw.pusedienos)
        hashMap["before sleep"] = Triple("prieš miegą", R.drawable.beforesleep, R.raw.priesmiega)
        hashMap["evening"] = Triple("vakaras", R.drawable.evening, R.raw.vakaras)
        hashMap["Morning"] = Triple("rytas", R.drawable.morning, R.raw.rytas)
        hashMap["Noon"] = Triple("vidurdienis", R.drawable.noon, R.raw.vidurdienis)
        hashMap["late"] = Triple("vėlai", R.drawable.late, R.raw.velai)
        hashMap["early"] = Triple("anksti", R.drawable.early, R.raw.anksti)
        hashMap["in the afternoon"] = Triple("po pietų", R.drawable.inafternoon, R.raw.popietu)
        hashMap["Today"] = Triple("šiandien", R.drawable.today, R.raw.siandien)
        hashMap["tomorrow"] = Triple("rytoj", R.drawable.tomorrow, R.raw.rytoj)
        hashMap["week"] = Triple("savaitė", R.drawable.week, R.raw.savaite)
        hashMap["once"] = Triple("vieną kartą", R.drawable.once, R.raw.vienakarta)
        hashMap["twice"] = Triple("du kartus", R.drawable.twice, R.raw.dukartus)
        hashMap["weekend"] = Triple("savaitgalis", R.drawable.weekend, R.raw.savaitgalis)
        hashMap["workday"] = Triple("darbo diena", R.drawable.workday, R.raw.darbodiena)
        hashMap["period"] = Triple("laikotarpį", R.drawable.period, R.raw.laikotarpi)



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
            findNavController().navigate(R.id.action_daysMonthsFlashcards_to_toLearnFlashCards)
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
               easyPreferencesHelper.saveProgressDaysMonths(progress)// Save progress

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