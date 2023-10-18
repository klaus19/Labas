package com.example.visuallithuanian

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
import com.example.visuallithuanian.database.FlashcardPair
import com.example.visuallithuanian.databinding.FragmentDailyBasicBinding
import com.example.visuallithuanian.databinding.FragmentDaysMonthsFlashcardsBinding
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

    private val hashMap = HashMap<String,Triple<String,Int,Int>>()

    private var currentTripleIndex =0
    private lateinit var currentTriple:Map.Entry<String,Triple<String,Int,Int>>

    var isFront=true
    private val totalTriples = 43 // change the value to the actual number of entries in your hashMap

    // declaring viewmodel
    private val cardViewModel: FlashCardViewmodel by viewModels {
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }


    @SuppressLint("ResourceType", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDaysMonthsFlashcardsBinding.inflate(inflater,container,false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity())[BottomNavigationViewModel::class.java]


        bottomNavigationView.visibility = View.GONE


        // setting up listener for back Icon
        binding.backIcon?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_daysMonthsFlashcards_to_flashCards)
        }
        //changing color of progress bar progress
        binding.progressHorizontal.progressTintList = ColorStateList.valueOf(
            ContextCompat.getColor(requireContext()
                ,R.color.float1))

        //changing color of background color of progress bar
        binding.progressHorizontal.progressBackgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(requireContext(),
                R.color.silver))

        // Hashmap of strings that will shown on cardview front and back side
        hashMap["Sunday"] = Triple("Sekmadienis", R.drawable.sunday,R.raw.sleep)
        hashMap["Monday"] = Triple("Pirmadienis", R.drawable.monday,R.raw.sleep)
        hashMap["Tuesday"] = Triple("Antradienis", R.drawable.tuesday,R.raw.sleep)
        hashMap["Wednesday"] = Triple("Trečiadienis", R.drawable.wednesday,R.raw.sleep)
        hashMap["Thursday"] = Triple("Ketvirtadienis", R.drawable.thursday,R.raw.sleep)
        hashMap["Friday"] = Triple("penktadienis", R.drawable.friday,R.raw.sleep)
        hashMap["Saturday"] = Triple("Šeštadienis", R.drawable.saturday,R.raw.sleep)
        hashMap["January"] = Triple("Sausio mėnesi", R.drawable.january,R.raw.sleep)
        hashMap["February"] = Triple("Vasario mėnesi", R.drawable.february,R.raw.sleep)
        hashMap["March"] = Triple("Kovas", R.drawable.march,R.raw.sleep)

        hashMap["April"] = Triple("Balandis", R.drawable.april,R.raw.sleep)
        hashMap["May"] = Triple("Gegužė", R.drawable.may,R.raw.sleep)
        hashMap["June"] = Triple("Birželis", R.drawable.june,R.raw.sleep)
        hashMap["July"] = Triple("Liepa", R.drawable.july,R.raw.sleep)
        hashMap["August"] = Triple("Rugpjūtis", R.drawable.august,R.raw.sleep)
        hashMap["September"] = Triple("Rugsėjis", R.drawable.september,R.raw.sleep)
        hashMap["October"] = Triple("Spalis", R.drawable.october,R.raw.sleep)
        hashMap["November"] = Triple("lapkritis", R.drawable.november,R.raw.sleep)
        hashMap["December"] = Triple("Gruodis", R.drawable.december,R.raw.sleep)
        hashMap["Spring"] = Triple("pavasaris", R.drawable.sleep,R.raw.sleep)

        hashMap["Summer"] = Triple("vasara", R.drawable.summer,R.raw.sleep)
        hashMap["autumn"] = Triple("ruduo", R.drawable.autumn,R.raw.sleep)
        hashMap["winter"] = Triple("žiema", R.drawable.winter,R.raw.sleep)
        hashMap["day"] = Triple("diena", R.drawable.day,R.raw.sleep)
        hashMap["Month"] = Triple("Mėnuo", R.drawable.month,R.raw.sleep)
        hashMap["Year"] = Triple("metai", R.drawable.year,R.raw.sleep)
        hashMap["Season"] = Triple("sezonas", R.drawable.season,R.raw.sleep)
        hashMap["decade"] = Triple("dešimtmetį", R.drawable.decade,R.raw.sleep)
        hashMap["Midnight"] = Triple("vidurnaktis", R.drawable.midnight,R.raw.sleep)
        hashMap["half a day"] = Triple("pusę dienos", R.drawable.halfaday,R.raw.sleep)

        hashMap["before sleep"] = Triple("prieš miegą", R.drawable.beforesleep,R.raw.sleep)
        hashMap["evening"] = Triple("vakaras", R.drawable.evening,R.raw.sleep)
        hashMap["Morning"] = Triple("rytas", R.drawable.morning,R.raw.sleep)
        hashMap["Noon"] = Triple("vidurdienis", R.drawable.noon,R.raw.sleep)
        hashMap["late"] = Triple("vėlai", R.drawable.late,R.raw.sleep)
        hashMap["early"] = Triple("anksti", R.drawable.early,R.raw.sleep)
        hashMap["in the afternoon"] = Triple("po pietų", R.drawable.inafternoon,R.raw.sleep)
        hashMap["Today"] = Triple("šiandien", R.drawable.today,R.raw.sleep)
        hashMap["tomorrow"] = Triple("rytoj", R.drawable.tomorrow,R.raw.sleep)
        hashMap["week"] = Triple("savaitė", R.drawable.week,R.raw.sleep)

        hashMap["once"] = Triple("vieną kartą", R.drawable.once,R.raw.sleep)
        hashMap["twice"] = Triple("du kartus", R.drawable.twice,R.raw.sleep)
        hashMap["weekend"] = Triple("savaitgalis", R.drawable.weekend,R.raw.sleep)
        hashMap["workday"] = Triple("darbo diena", R.drawable.workday,R.raw.sleep)
        hashMap["period"] = Triple("laikotarpį", R.drawable.period,R.raw.sleep)



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
            findNavController().navigate(R.id.action_daysMonthsFlashcards_to_toLearnFlashCards)
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
                    cardViewQuestions.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green1))

                } else {
                    currentTripleIndex = (currentTripleIndex + 1) % hashMap.size
                    textCardFront.visibility = View.VISIBLE
                    textCardBack.visibility = View.VISIBLE
                    imageFlashCard.visibility = View.VISIBLE
                    cardViewQuestions.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.orange1))
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