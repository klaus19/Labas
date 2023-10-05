package com.example.visuallithuanian

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
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
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.ToLearnViewModel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class DailyBasic : Fragment() {
    lateinit var binding: FragmentDailyBasicBinding
    lateinit var viewModel: BottomNavigationViewModel

    lateinit var bottomNavigationView: BottomNavigationView
    private val counterViewModel: ToLearnViewModel by viewModels()

    private val hashMap = HashMap<String,Triple<String,Int,Int>>()

    private var currentTripleIndex =0
    private lateinit var currentTriple:Map.Entry<String,Triple<String,Int,Int>>

    var isFront=true
    private val totalTriples = 47 // change the value to the actual number of entries in your hashMap

    // declaring viewmodel
    private val cardViewModel: FlashCardViewmodel by viewModels {
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }


    @SuppressLint("ResourceType", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDailyBasicBinding.inflate(inflater,container,false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity())[BottomNavigationViewModel::class.java]


        bottomNavigationView.visibility = View.GONE


        // setting up listener for back Icon
        binding.backIcon?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_dailyBasic_to_flashCards)
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
        hashMap["sleep"] = Triple("miegoti", R.drawable.sleep,R.raw.potato)
        hashMap["to shop"] = Triple("apsipirkti",R.drawable.shopping,R.raw.potato)
        hashMap["to watch a movie"] = Triple("Žiūrėti filmą",R.drawable.cinemascreen,R.raw.potato)
        hashMap["I go shopping"] = Triple("aš einu apsipirkti", R.drawable.goshopping,R.raw.potato)
        hashMap["What is it?"] = Triple("Kas tai?",R.drawable.what1,R.raw.potato)
        hashMap["this is English newspaper"] = Triple("tai yra angliškas laikraštis",R.drawable.newspaper,R.raw.potato)
        hashMap["Japanese food"] = Triple("Japonų maistas", R.drawable.japanesefood,R.raw.potato)
        hashMap["hot coffee"] = Triple("karšta kava",R.drawable.hotcoffee,R.raw.potato)
        hashMap["I learn Lithuanian"] = Triple("Aš mokausi lietuvių kalbos",R.drawable.languages,R.raw.potato)
        hashMap["to eat"] = Triple("valgyti", R.drawable.eat,R.raw.potato)
        hashMap["I eat japanese food"] = Triple("Aš valgau japonišką maistą",R.drawable.eat,R.raw.potato)
        hashMap["She drinks hot coffee"] = Triple("Ji geria karštą kavą",R.drawable.coffee1,R.raw.potato)
        hashMap["Would you like some tea?"] = Triple("Ar norėtumete arbatos?",R.drawable.tea1,R.raw.potato)
        hashMap["Can you repeat it again?"] = Triple("Ar galite pakartoti dar kartą?", R.drawable.repeat,R.raw.potato)
        hashMap["Of course"] = Triple("Žinoma",R.drawable.ofcourse,R.raw.potato)
        hashMap["rice"] = Triple("ryžiai",R.drawable.rice,R.raw.potato)
        hashMap["soup"] = Triple("sriuba",R.drawable.soup1,R.raw.potato)
        hashMap["Bread"] = Triple("Duona", R.drawable.bread1,R.raw.potato)
        hashMap["water"] = Triple("vanduo",R.drawable.water1,R.raw.potato)
        hashMap["What are you reading?"] = Triple("Ką skaitote?",R.drawable.whatreading,R.raw.potato)
        hashMap["to cost"] = Triple("kainuoti",R.drawable.cost,R.raw.potato)
        hashMap["a cup of coffee"] = Triple("puodelis kavos",R.drawable.cupcoffee,R.raw.potato)
        hashMap["apple"] = Triple("obuolys", R.drawable.apple1,R.raw.potato)
        hashMap["a glass"] = Triple("stiklinė",R.drawable.glass1,R.raw.potato)
        hashMap["What do you buy?"] = Triple("Ką perkate?",R.drawable.buy11,R.raw.potato)
        hashMap["parents"] = Triple("tėvai",R.drawable.parents1,R.raw.potato)
        hashMap["classmate"] = Triple("klasiokas", R.drawable.classmate,R.raw.potato)
        hashMap["my friends"] = Triple("Mano draugai",R.drawable.friends1,R.raw.potato)
        hashMap["a little"] = Triple("šiek tiek",R.drawable.little,R.raw.potato)
        hashMap["Let's go"] = Triple("eikime!",R.drawable.letsgo,R.raw.potato)
        hashMap["more"] = Triple("daugiau",R.drawable.more,R.raw.potato)
        hashMap["key"] = Triple("raktas",R.drawable.key,R.raw.potato)
        hashMap["what are you doing?"] = Triple("ką darote?",R.drawable.todo,R.raw.potato)
        hashMap["hand"] = Triple("ranka",R.drawable.hand,R.raw.potato)
        hashMap["to stop"] = Triple("nustoti",R.drawable.stopsign,R.raw.potato)

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
            findNavController().navigate(R.id.action_dailyBasic_to_toLearnFlashCards)
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