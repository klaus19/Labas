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
import com.bumptech.glide.Glide
import com.example.visuallithuanian.R
import com.example.visuallithuanian.database.FlashcardPair
import com.example.visuallithuanian.databinding.FragmentBasicActionsFlashcardBinding
import com.example.visuallithuanian.model.EasyPreferencesHelper
import com.example.visuallithuanian.model.MediumProgressPreferencesHelper
import com.example.visuallithuanian.model.PreferencesHelper
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.ToLearnViewModel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class BasicActionsFlashcard : Fragment() {


    lateinit var binding: FragmentBasicActionsFlashcardBinding
    lateinit var viewModel: BottomNavigationViewModel

    lateinit var bottomNavigationView: BottomNavigationView
    private val counterViewModel: ToLearnViewModel by viewModels()
    private val hashMap = HashMap<String, Triple<String, Int, Int>>()

    private var currentTripleIndex = 0
    private lateinit var currentTriple: Map.Entry<String, Triple<String, Int, Int>>

    var isFront = true
    private val totalTriples = 34 // change the value to the actual number of entries in your hashMap
    private lateinit var preferencesHelper: PreferencesHelper
    private lateinit var easyHelper:EasyPreferencesHelper
    // declaring viewmodel
    private val cardViewModel: FlashCardViewmodel by viewModels {
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }


    @SuppressLint("ResourceType", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBasicActionsFlashcardBinding.inflate(inflater,container,false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity())[BottomNavigationViewModel::class.java]

        bottomNavigationView.visibility = View.GONE

        preferencesHelper = PreferencesHelper(requireContext())
        easyHelper = EasyPreferencesHelper(requireContext())
        // Restore saved progress and counter
        val savedCounter = preferencesHelper.getCounter()
        val savedProgress = easyHelper.getProgressBasicActions()
        counterViewModel.setCounter(savedCounter) // Assuming ToLearnViewModel has a method to set counter

        // setting up listener for back Icon
        binding.backIcon?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_basicActionsFlashcard_to_sentenceFragment)
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

        hashMap["read"] = Triple("skaityti", R.drawable.read, R.raw.read)
        hashMap["to like"] = Triple("patikti", R.drawable.tolike, R.raw.tolike)
        hashMap["count"] = Triple("skaičiuoti",R.drawable.count,R.raw.count)
        hashMap["to live"] = Triple("gyventi", R.drawable.tolive, R.raw.tolive)
        hashMap["to talk"] = Triple("kalbėti", R.drawable.talk, R.raw.talk)
        hashMap["to work"] = Triple("dirbti", R.drawable.work, R.raw.work)
        hashMap["to pay"] = Triple("mokėti", R.drawable.topay, R.raw.topay)
        hashMap["to write"] = Triple("rašyti", R.drawable.write, R.raw.towrite)
        hashMap["to know"] = Triple("žinoti", R.drawable.know, R.raw.know)
        hashMap["to want"] = Triple("norėti", R.drawable.want, R.raw.towant)

        hashMap["to say"] = Triple("sakyti", R.drawable.tosay, R.raw.say)
        hashMap["to do"] = Triple("daryti", R.drawable.todo, R.raw.todo)
        hashMap["to be"] = Triple("būti", R.drawable.tobe, R.raw.tobe)
        hashMap["to bring"] = Triple("atnešti", R.drawable.bring, R.raw.tobring)
        hashMap["push"] = Triple("stumti", R.drawable.push, R.raw.topush)
        hashMap["pull"] = Triple("traukti", R.drawable.pull, R.raw.topull)
        hashMap["to go"] = Triple("eiti", R.drawable.go, R.raw.togo)
        hashMap["to see"] = Triple("pamatyti", R.drawable.tosee, R.raw.tosee)
        hashMap["Can I see it?"] = Triple("ar galiu pamatyti?", R.drawable.canisee, R.raw.canseeit)
        hashMap["to sell"] = Triple("parduoti", R.drawable.tosell, R.raw.tosell)

        hashMap["to repair"] = Triple("sutaisyti", R.drawable.repair, R.raw.repair)
        hashMap["to take"] = Triple("paimti, imk", R.drawable.take, R.raw.take)
        hashMap["I walk and run"] = Triple("aš einu ir bėgu", R.drawable.walk, R.raw.iwalk)
        hashMap["I fly or swim"] = Triple("Aš skrendu arbo plaukiu", R.drawable.fly, R.raw.fly)
        hashMap["to open"] = Triple("atidaryti", R.drawable.open1, R.raw.open)
        hashMap["to close"] = Triple("uždaryti", R.drawable.close, R.raw.close)
        hashMap["depart and arrive"] = Triple("išvykti ir atvykti",
            R.drawable.depart,
            R.raw.departandarrive
        )
        hashMap["on and off"] = Triple("ijungti ir išjungti", R.drawable.onoff, R.raw.onoff)
        hashMap["to give"] = Triple("duoti", R.drawable.togive, R.raw.give)
        hashMap["I am willing and able"] = Triple("aš noriu ir galiu",
            R.drawable.ableto,
            R.raw.willing
        )

        hashMap["to put"] = Triple("padėti", R.drawable.toput, R.raw.put)
        hashMap["to find"] = Triple("rasti", R.drawable.tofind, R.raw.find)
        hashMap["to cook"] = Triple("virti", R.drawable.tocook, R.raw.cook)
        hashMap["to be produced by"] = Triple("gaminti", R.drawable.toproduce, R.raw.produced)



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

        //Displaying GIF image on the screen

        Glide.with(this).asGif().load(R.drawable.finger1).into(binding.gifImageView)

        // Action that happens when clicked on the Gif
        binding.gifImageView.setOnClickListener {
            findNavController().navigate(R.id.action_basicActionsFlashcard_to_toLearnFlashCards)
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
                easyHelper.saveProgressBasicActions(progress)// Save progress

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