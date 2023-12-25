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
import com.example.visuallithuanian.databinding.FragmentHolidayCelebrationsBinding
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.ToLearnViewModel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


class HolidayCelebrations : Fragment() {

    lateinit var binding: FragmentHolidayCelebrationsBinding
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
        binding = FragmentHolidayCelebrationsBinding.inflate(inflater,container,false)

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
        hashMap["celebration"] = Triple("švente", R.drawable.sleep,R.raw.sleep)
        hashMap["to celebrate, celebrates, celebrated what?"] = Triple("švęsti, švenčia, šventė ką?", R.drawable.sleep,R.raw.sleep)
        hashMap["birthday"] = Triple("gimtadienis", R.drawable.sleep,R.raw.sleep)
        hashMap["name day"] = Triple("vardadienis", R.drawable.sleep,R.raw.sleep)
        hashMap["anniversary"] = Triple("jubiliejus", R.drawable.sleep,R.raw.sleep)
        hashMap["to invite, invites, invited what?"] = Triple("kviesti, kviečia, kviektė ką?", R.drawable.sleep,R.raw.sleep)
        hashMap["party"] = Triple("vakarėlis", R.drawable.sleep,R.raw.sleep)
        hashMap["guest"] = Triple("svečias", R.drawable.sleep,R.raw.sleep)
        hashMap["to go visit (as a guest)"] = Triple("eiti į svečius", R.drawable.sleep,R.raw.sleep)
        hashMap["to invite guests"] = Triple("kviesti į svečius", R.drawable.sleep,R.raw.sleep)

        hashMap["to be a guest"] = Triple("būti svečiuose", R.drawable.sleep,R.raw.sleep)
        hashMap["to give"] = Triple("dovanoti", R.drawable.sleep,R.raw.sleep)
        hashMap["to get, gets, got what?"] = Triple("gauti, gauna, gavo ką?", R.drawable.sleep,R.raw.sleep)
        hashMap["gift"] = Triple("dovana", R.drawable.sleep,R.raw.sleep)
        hashMap["flower"] = Triple("gėlė", R.drawable.flowers,R.raw.sleep)
        hashMap["Postcard"] = Triple("atvirukas", R.drawable.sleep,R.raw.sleep)
        hashMap["to send"] = Triple("išsiųsti", R.drawable.sleep,R.raw.sleep)
        hashMap["to write, writes, wrote postcard"] = Triple("užrašyti, užrašo, užrašė atvirukas", R.drawable.sleep,R.raw.sleep)
        hashMap["cake"] = Triple("tortas", R.drawable.sleep,R.raw.sleep)
        hashMap["candles"] = Triple("žvakutė", R.drawable.sleep,R.raw.sleep)
        hashMap["Christmas Eve"] = Triple("Kūčios", R.drawable.sleep,R.raw.sleep)

        hashMap["to congratulate"] = Triple("sveikinti", R.drawable.sleep,R.raw.sleep)
        hashMap["to wish"] = Triple("linkėti,", R.drawable.sleep,R.raw.sleep)
        hashMap["Christmas"] = Triple("Kalėdos", R.drawable.sleep,R.raw.sleep)
        hashMap["christmas tree"] = Triple("eglutė", R.drawable.sleep,R.raw.sleep)
        hashMap["Santa Claus"] = Triple("Kalėdų senelis", R.drawable.sleep,R.raw.sleep)
        hashMap["candle"] = Triple("žvakė", R.drawable.sleep,R.raw.sleep)
        hashMap["New Year"] = Triple("Naujieji Metai", R.drawable.sleep,R.raw.sleep)
        hashMap["to wait"] = Triple("sutikti", R.drawable.sleep,R.raw.sleep)
        hashMap["fireworks"] = Triple("fejerverkai", R.drawable.sleep,R.raw.sleep)
        hashMap["Easter"] = Triple("Velykos", R.drawable.sleep,R.raw.sleep)

        hashMap["Easter egg"] = Triple("margutis", R.drawable.sleep,R.raw.sleep)
        hashMap["to dye"] = Triple("dažyti", R.drawable.sleep,R.raw.sleep)
        hashMap["public holidays"] = Triple("valstybinės šventes", R.drawable.sleep,R.raw.sleep)
        hashMap["flag"] = Triple("vėliava", R.drawable.sleep,R.raw.sleep)
        hashMap["success"] = Triple("sėkme", R.drawable.sleep,R.raw.sleep)
        hashMap["happiness"] = Triple("laimė", R.drawable.sleep,R.raw.sleep)
        hashMap["Joy"] = Triple("džiaugsmas", R.drawable.sleep,R.raw.sleep)
        hashMap["happy"] = Triple("laimingas", R.drawable.sleep,R.raw.sleep)
        hashMap["beloved"] = Triple("mylimas", R.drawable.sleep,R.raw.sleep)
        hashMap["honourable"] = Triple("gerbiamas,", R.drawable.sleep,R.raw.sleep)
        hashMap["colourful"] = Triple("spalvingas", R.drawable.sleep,R.raw.sleep)
        hashMap["Popular"] = Triple("popularus", R.drawable.sleep,R.raw.sleep)



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