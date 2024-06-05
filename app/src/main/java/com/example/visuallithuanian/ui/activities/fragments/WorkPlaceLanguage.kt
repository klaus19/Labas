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
import com.example.visuallithuanian.databinding.FragmentFamilyFlashcardsBinding
import com.example.visuallithuanian.databinding.FragmentWorkPlaceLanguageBinding
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.ToLearnViewModel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


class WorkPlaceLanguage : Fragment() {


    lateinit var binding: FragmentWorkPlaceLanguageBinding
    lateinit var viewModel: BottomNavigationViewModel

    lateinit var bottomNavigationView: BottomNavigationView
    private val counterViewModel: ToLearnViewModel by viewModels()

    private val hashMap = HashMap<String,Triple<String,Int,Int>>()

    private var currentTripleIndex =0
    private lateinit var currentTriple:Map.Entry<String,Triple<String,Int,Int>>

    var isFront=true
    private val totalTriples = 42 // change the value to the actual number of entries in your hashMap

    // declaring viewmodel
    private val cardViewModel: FlashCardViewmodel by viewModels {
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }


    @SuppressLint("ResourceType", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWorkPlaceLanguageBinding.inflate(inflater,container,false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity())[BottomNavigationViewModel::class.java]


        bottomNavigationView.visibility = View.GONE


        // setting up listener for back Icon
        binding.backIcon?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.floatingActionButton.setOnClickListener {
            //findNavController().navigate(R.id.action_wor)
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
        hashMap["work"] = Triple("darbas", R.drawable.work,R.raw.sleep)
        hashMap["rest day / public holiday"] = Triple("poilsio / nedarbo diena", R.drawable.holiday1,R.raw.sleep)
        hashMap["Workplace"] = Triple("darbovietė", R.drawable.workplace1,R.raw.sleep)
        hashMap["firm/company"] = Triple("įmonė", R.drawable.company,R.raw.sleep)
        hashMap["director / manager / CEO"] = Triple("direktorus / direktorė", R.drawable.director,R.raw.sleep)
        hashMap["colleague"] = Triple("kolega / kolegė", R.drawable.colleague1,R.raw.sleep)
        hashMap["employee"] = Triple("darbuotojas / darbotoja", R.drawable.employee1,R.raw.sleep)
        hashMap["client / customer"] = Triple("klientas / klientė", R.drawable.client,R.raw.sleep)
        hashMap["busy"] = Triple("užsiemęs (m) / užsiemusi (f)", R.drawable.busy1,R.raw.sleep)
        hashMap["business trip"] = Triple("komandiruotė", R.drawable.businesstrip,R.raw.sleep)

        hashMap["meeting"] = Triple("susirinkimas/susitinkimas", R.drawable.meeting1,R.raw.sleep)
        hashMap["cabinet"] = Triple("kabinetas", R.drawable.cabinet,R.raw.sleep)
        hashMap["to begin, begins, began"] = Triple("prasidėti, prasideda, prasidėjo", R.drawable.begin,R.raw.sleep)
        hashMap["to finish/to come to an end, finishes, finished"] = Triple("pasibaigti, pasibaiga, pasibaigė", R.drawable.end,R.raw.sleep)
        hashMap["to start, starts, started what?"] = Triple("pradėti, pradeda, pradėjo ką?", R.drawable.start1,R.raw.sleep)
        hashMap["to finish, finishes, finished what?"] = Triple("baigti, baigia, baigė ką?", R.drawable.finish1,R.raw.sleep)
        hashMap["to finish work"] = Triple("baigti darbą", R.drawable.finishwork,R.raw.sleep)
        hashMap["lunch break"] = Triple("pietų pertrauka", R.drawable.lunchbreak,R.raw.sleep)
        hashMap["to have vacation, vacationing, vacationed"] = Triple("atostogauti, atostogauja, atostogavo", R.drawable.vacation1,R.raw.sleep)

        hashMap["holidays, vacation"] = Triple("atostogos", R.drawable.vacation1,R.raw.sleep)
        hashMap["to print, prints, printed what?"] = Triple("išspausdinti, išspausdina, išspausdino ką?", R.drawable.print,R.raw.sleep)
        hashMap["Document"] = Triple("dokumentas", R.drawable.document,R.raw.sleep)
        hashMap["copy"] = Triple("kopija", R.drawable.copy,R.raw.sleep)
        hashMap["to inform, informs, informed what? who?"] = Triple("perduoti, perduoda, perdavė ką? kam?", R.drawable.inform,R.raw.sleep)
        hashMap["to give/hand, gives/hands, gave what? to whom?"] = Triple("paduoti, paduoda, padavė ką? kam?", R.drawable.togive,R.raw.sleep)
        hashMap["to say, says, said what? to whom?"] = Triple("pasakyti, pasako, pasakė ką? kam?", R.drawable.say1,R.raw.sleep)
        hashMap["to take, takes, took what? to whom?"] = Triple("nunešti, nuneša, nunešė ką? kam?", R.drawable.take,R.raw.sleep)
        hashMap["to write, writes, wrote what? to whom?"] = Triple("rašyti, rašo, raše ką? kam?", R.drawable.write2,R.raw.sleep)
        hashMap["to send, sends, sent what?"] = Triple("išsiųsti, išsiunčia, išsiunte ką?", R.drawable.send1,R.raw.sleep)

        hashMap["to get/receive, gets, got what?"] = Triple("gauti, gauna, gavo ką?", R.drawable.toreceive,R.raw.sleep)
        hashMap["email"] = Triple("elektroninis laiškas", R.drawable.email,R.raw.sleep)
        hashMap["sign, signs, signed what?"] = Triple("pasirašyti, pasirašo, pasirašė ką?", R.drawable.signed,R.raw.sleep)
        hashMap["Signature"] = Triple("parašas", R.drawable.signature,R.raw.sleep)
        hashMap["Contract"] = Triple("sutartis", R.drawable.contract,R.raw.sleep)
        hashMap["to wait, waits, waited for what?"] = Triple("palaukti, palaukia, palaukė ko?", R.drawable.wait1,R.raw.sleep)
        hashMap["to ask, asks, asked"] = Triple("paklausti, paklausia, paklausė", R.drawable.toask1,R.raw.sleep)
        hashMap["to call, calls, called who?"] = Triple("paskambinti, paskambina, paskambino kam?", R.drawable.tocall,R.raw.sleep)
        hashMap["paper"] = Triple("popierius", R.drawable.paper,R.raw.sleep)
        hashMap["sheet of paper"] = Triple("popieriaus lapas", R.drawable.sheetofpaper,R.raw.sleep)

        hashMap["computer"] = Triple("kompiuteris", R.drawable.computer1,R.raw.sleep)
        hashMap["printer"] = Triple("spausdintuvas", R.drawable.printer,R.raw.sleep)




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
            findNavController().navigate(R.id.action_workPlaceLanguage_to_toLearnFlashCards)
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