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
import com.example.visuallithuanian.databinding.FragmentWorkPlaceLanguageBinding
import com.example.visuallithuanian.model.EasyPreferencesHelper
import com.example.visuallithuanian.model.PreferencesHelper
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
    private val hashMap = HashMap<String, Triple<String, Int, Int>>()

    private var currentTripleIndex = 0
    private lateinit var currentTriple: Map.Entry<String, Triple<String, Int, Int>>

    var isFront=true
    private val totalTriples = 36 // change the value to the actual number of entries in your hashMap
    private lateinit var preferencesHelper: PreferencesHelper
    private lateinit var easyPreferencesHelper: EasyPreferencesHelper
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

        preferencesHelper = PreferencesHelper(requireContext())
        easyPreferencesHelper = EasyPreferencesHelper(requireContext())
        // Restore saved progress and counter
        val savedCounter = preferencesHelper.getCounter()
        val savedProgress = easyPreferencesHelper.getProgressWorkplacelanaguage()
        counterViewModel.setCounter(savedCounter) // Assuming ToLearnViewModel has a method to set counter

        // setting up listener for back Icon
        binding.backIcon?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_workPlaceLanguage_to_sentenceFragment)
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
            )
        )
        hashMap["colleague"] = Triple("kolega / kolegė", R.drawable.colleague1, R.raw.sleep)
        hashMap["employee"] = Triple("darbuotojas / darbotoja", R.drawable.employee1, R.raw.sleep)
        hashMap["client"] = Triple("klientas / klientė", R.drawable.client, R.raw.sleep)
        hashMap["busy"] = Triple("užsiemęs (m) / užsiemusi (f)", R.drawable.busy1, R.raw.sleep)
        hashMap["business trip"] = Triple("komandiruotė", R.drawable.businesstrip, R.raw.sleep)
        hashMap["meeting"] = Triple("susirinkimas/susitinkimas", R.drawable.meeting1, R.raw.sleep)
        hashMap["cabinet"] = Triple("kabinetas", R.drawable.cabinet, R.raw.sleep)
        hashMap["to begin"] = Triple("prasidėti", R.drawable.begin,R.raw.sleep)
        hashMap["to finish"] = Triple("pasibaigti",R.drawable.end,R.raw.sleep)
        hashMap["to start"] = Triple("pradėti",R.drawable.start1, R.raw.sleep)
        hashMap["to finish"] = Triple("baigti",R.drawable.finish1,R.raw.sleep)
        hashMap["to finish work"] = Triple("baigti darbą", R.drawable.finishwork, R.raw.sleep)
        hashMap["lunch break"] = Triple("pietų pertrauka", R.drawable.lunchbreak, R.raw.sleep)
        hashMap["to have vacation"] = Triple("atostogauti", R.drawable.vacation1,R.raw.sleep)
        hashMap["holidays"] = Triple("atostogos", R.drawable.vacation1, R.raw.sleep)
        hashMap["to print"] = Triple("išspausdinti",R.drawable.print,R.raw.sleep)
        hashMap["Document"] = Triple("dokumentas", R.drawable.document, R.raw.sleep)
        hashMap["copy"] = Triple("kopija", R.drawable.copy, R.raw.sleep)
        hashMap["to inform"] = Triple("perduoti", R.drawable.inform, R.raw.sleep)
        hashMap["to give"] = Triple("paduoti",R.drawable.togive,R.raw.sleep)
        hashMap["to say"] = Triple("pasakyti",R.drawable.say1,R.raw.sleep)
        hashMap["to take"] = Triple("nunešti",R.drawable.take,R.raw.sleep)
        hashMap["to write"] = Triple("rašyti",R.drawable.write2,R.raw.sleep)
        hashMap["to send"] = Triple("išsiųsti",R.drawable.send1,R.raw.sleep)
        hashMap["to get/receive"] = Triple("gauti",R.drawable.toreceive,R.raw.sleep)
        hashMap["email"] = Triple("elektroninis laiškas", R.drawable.email, R.raw.sleep)
        hashMap["sign"] = Triple("pasirašyti",R.drawable.signed,R.raw.sleep)
        hashMap["Signature"] = Triple("parašas", R.drawable.signature, R.raw.sleep)
        hashMap["Contract"] = Triple("sutartis", R.drawable.contract, R.raw.sleep)
        hashMap["to wait"] = Triple("palaukti", R.drawable.wait1, R.raw.sleep)
        hashMap["to ask, asks, asked"] = Triple("paklausti",R.drawable.toask1, R.raw.sleep)
        hashMap["to call"] = Triple("paskambinti", R.drawable.tocall,R.raw.sleep)
        hashMap["paper"] = Triple("popierius", R.drawable.paper, R.raw.sleep)
        hashMap["sheet of paper"] = Triple("popieriaus lapas", R.drawable.sheetofpaper, R.raw.sleep)
        hashMap["computer"] = Triple("kompiuteris", R.drawable.computer1, R.raw.sleep)
        hashMap["printer"] = Triple("spausdintuvas", R.drawable.printer, R.raw.sleep)


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
            findNavController().navigate(R.id.action_workPlaceLanguage_to_toLearnFlashCards)
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
               easyPreferencesHelper.saveProgressWorkplacelanguage(progress)// Save progress

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