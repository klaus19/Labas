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
import com.example.visuallithuanian.databinding.FragmentColorshapeBinding
import com.example.visuallithuanian.databinding.FragmentDaysMonthsFlashcardsBinding
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.ToLearnViewModel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


class ColorshapeFragment : Fragment() {
    lateinit var binding: FragmentColorshapeBinding
    lateinit var viewModel: BottomNavigationViewModel

    lateinit var bottomNavigationView: BottomNavigationView
    private val counterViewModel: ToLearnViewModel by viewModels()

    private val hashMap = HashMap<String,Triple<String,Int,Int>>()

    private var currentTripleIndex =0
    private lateinit var currentTriple:Map.Entry<String,Triple<String,Int,Int>>

    var isFront=true
    private val totalTriples = 20 // change the value to the actual number of entries in your hashMap

    // declaring viewmodel
    private val cardViewModel: FlashCardViewmodel by viewModels {
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }


    @SuppressLint("ResourceType", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentColorshapeBinding.inflate(inflater,container,false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity())[BottomNavigationViewModel::class.java]


        bottomNavigationView.visibility = View.GONE


        // setting up listener for back Icon
        binding.backIcon?.setOnClickListener {
            activity?.onBackPressed()
        }
//        binding.floatingActionButton.setOnClickListener {
//            findNavController().navigate(R.id.action_daysMonthsFlashcards_to_flashCards)
//        }
        //changing color of progress bar progress
        binding.progressHorizontal.progressTintList = ColorStateList.valueOf(
            ContextCompat.getColor(requireContext()
                ,R.color.float1))

        //changing color of background color of progress bar
        binding.progressHorizontal.progressBackgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(requireContext(),
                R.color.silver))

        // Hashmap of strings that will shown on cardview front and back side
        hashMap["white"] = Triple("baltas", R.drawable.white,R.raw.sunday)
        hashMap["black"] = Triple("juodas", R.drawable.black,R.raw.sunday)
        hashMap["red"] = Triple("raudonas", R.drawable.red,R.raw.sunday)
        hashMap["green"] = Triple("žalias", R.drawable.green,R.raw.sunday)
        hashMap["orange"] = Triple("oranžinis", R.drawable.orange1,R.raw.sunday)
        hashMap["yellow"] = Triple("geltonas", R.drawable.yellow,R.raw.sunday)
        hashMap["blue"] = Triple("mėlynas", R.drawable.blue,R.raw.sunday)
        hashMap["pink"] = Triple("rožinis", R.drawable.pink,R.raw.sunday)
        hashMap["stripped"] = Triple("dryžuotas", R.drawable.stripped,R.raw.sunday)
        hashMap["round"] = Triple("apvalus", R.drawable.round,R.raw.sunday)

        hashMap["squared"] = Triple("kvadratas", R.drawable.square,R.raw.sunday)
        hashMap["triangular"] = Triple("trikampis", R.drawable.triangular,R.raw.sunday)
        hashMap["rectangular"] = Triple("stačiakampis", R.drawable.rectangular,R.raw.sunday)
        hashMap["spheric"] = Triple("sferinis", R.drawable.sphere,R.raw.sunday)
        hashMap["cubic"] = Triple("kubinis", R.drawable.cubic,R.raw.sunday)
        hashMap["brown cat"] = Triple("ruda katė", R.drawable.browncat,R.raw.sunday)
        hashMap["brown eyes"] = Triple("rudas akis", R.drawable.browneyes,R.raw.sunday)
        hashMap["round face"] = Triple("apvalus veidas", R.drawable.roundface,R.raw.sunday)
        hashMap["blue eyes"] = Triple("mėlynas akis", R.drawable.blueeyes,R.raw.sunday)
        hashMap["grey mouse"] = Triple("pilka pelė", R.drawable.greymouse,R.raw.sunday)




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
            findNavController().navigate(R.id.action_colorshapeFragment_to_toLearnFlashCards)
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
                    cardViewQuestions.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.new_design_text_color))

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