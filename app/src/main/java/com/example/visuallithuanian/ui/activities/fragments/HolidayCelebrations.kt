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
import com.example.visuallithuanian.databinding.FragmentHolidayCelebrationsBinding
import com.example.visuallithuanian.model.PreferencesHelper
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.ToLearnViewModel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


class HolidayCelebrations : Fragment() {

    lateinit var binding: FragmentHolidayCelebrationsBinding
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
        binding = FragmentHolidayCelebrationsBinding.inflate(inflater,container,false)

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
            findNavController().navigate(R.id.action_holidayCelebrations_to_sentenceFragment)
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
        hashMap["party"] = Triple("vakarėlis", R.drawable.party, R.raw.sleep)
        hashMap["guest"] = Triple("svečias", R.drawable.guests, R.raw.sleep)
        hashMap["to go visit (as a guest)"] = Triple("eiti į svečius", R.drawable.tovisit, R.raw.sleep)
        hashMap["to invite guests"] = Triple("kviesti į svečius", R.drawable.toinvite, R.raw.sleep)
        hashMap["to be a guest"] = Triple("būti svečiuose", R.drawable.tobeguest, R.raw.sleep)
        hashMap["to give"] = Triple("dovanoti", R.drawable.togive, R.raw.sleep)
        hashMap["to get, gets, got what?"] = Triple("gauti, gauna, gavo ką?", R.drawable.toget1, R.raw.sleep)
        hashMap["gift"] = Triple("dovana", R.drawable.gift, R.raw.sleep)
        hashMap["flower"] = Triple("gėlė", R.drawable.flowers, R.raw.sleep)
        hashMap["Postcard"] = Triple("atvirukas", R.drawable.postcard, R.raw.sleep)
        hashMap["to send"] = Triple("išsiųsti", R.drawable.send1, R.raw.sleep)
        hashMap["to write, writes, wrote postcard"] = Triple("užrašyti, užrašo, užrašė atvirukas",R.drawable.write, R.raw.sleep)
        hashMap["cake"] = Triple("tortas", R.drawable.cake, R.raw.sleep)
        hashMap["candles"] = Triple("žvakutė", R.drawable.candles, R.raw.sleep)
        hashMap["Christmas Eve"] = Triple("Kūčios", R.drawable.christmaseve, R.raw.sleep)

        hashMap["to congratulate"] = Triple("sveikinti", R.drawable.tocongratulate, R.raw.sleep)
        hashMap["to wish"] = Triple("linkėti,", R.drawable.towish1, R.raw.sleep)
        hashMap["Christmas"] = Triple("Kalėdos", R.drawable.christmas, R.raw.sleep)
        hashMap["christmas tree"] = Triple("eglutė", R.drawable.christmastree, R.raw.sleep)
        hashMap["Santa Claus"] = Triple("Kalėdų senelis", R.drawable.santaclaus, R.raw.sleep)
        hashMap["candle"] = Triple("žvakė", R.drawable.candle, R.raw.sleep)
        hashMap["New Year"] = Triple("Naujieji Metai", R.drawable.happynewyear, R.raw.sleep)
        hashMap["to wait"] = Triple("sutikti", R.drawable.towait1, R.raw.sleep)
        hashMap["fireworks"] = Triple("fejerverkai", R.drawable.fireworks, R.raw.sleep)
        hashMap["Easter"] = Triple("Velykos", R.drawable.easter, R.raw.sleep)

        hashMap["Easter egg"] = Triple("margutis", R.drawable.easteregg, R.raw.sleep)
        hashMap["to dye"] = Triple("dažyti", R.drawable.todye, R.raw.sleep)
        hashMap["public holidays"] = Triple("valstybinės šventes", R.drawable.holiday1, R.raw.sleep)
        hashMap["flag"] = Triple("vėliava", R.drawable.flag, R.raw.sleep)
        hashMap["success"] = Triple("sėkme", R.drawable.success, R.raw.sleep)
        hashMap["happiness"] = Triple("laimė", R.drawable.happiness, R.raw.sleep)
        hashMap["Joy"] = Triple("džiaugsmas", R.drawable.joy, R.raw.sleep)
        hashMap["happy"] = Triple("laimingas", R.drawable.happy, R.raw.sleep)
        hashMap["beloved"] = Triple("mylimas", R.drawable.beloved, R.raw.sleep)
        hashMap["honourable"] = Triple("gerbiamas,", R.drawable.honorable, R.raw.sleep)
        hashMap["colourful"] = Triple("spalvingas", R.drawable.colorful, R.raw.sleep)
        hashMap["Popular"] = Triple("popularus", R.drawable.popular, R.raw.sleep)



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

        //Navigating from one fragment to another
        binding.cardLearning.setOnClickListener {
            findNavController().navigate(R.id.action_holidayCelebrations_to_toLearnFlashCards)
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
