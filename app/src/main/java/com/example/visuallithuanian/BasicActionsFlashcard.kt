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
import com.example.visuallithuanian.databinding.FragmentBasicActionsFlashcardBinding
import com.example.visuallithuanian.databinding.FragmentDaysMonthsFlashcardsBinding
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

    private val hashMap = HashMap<String,Triple<String,Int,Int>>()

    private var currentTripleIndex =0
    private lateinit var currentTriple:Map.Entry<String,Triple<String,Int,Int>>

    var isFront=true
    private val totalTriples = 34// change the value to the actual number of entries in your hashMap

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


        binding.backIcon?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_basicActionsFlashcard_to_flashCards)
        }
        //changing color of progress bar progress
        binding.progressHorizontal.progressTintList = ColorStateList.valueOf(
            ContextCompat.getColor(requireContext()
                ,R.color.float1))

        //changing color of background color of progress bar
        binding.progressHorizontal.progressBackgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(requireContext(),
                R.color.silver))

        hashMap["read"] = Triple("skaityti - skaito - skaitė", R.drawable.read,R.raw.read)
        hashMap["to like"] = Triple("patikti - patinka - patiko", R.drawable.tolike,R.raw.tolike)
        hashMap["count"] = Triple("skaičiuoti - scaičiuoja - scaičiavo", R.drawable.count,R.raw.count)
        hashMap["to live"] = Triple("gyventi - gyvena - gyveno", R.drawable.tolive,R.raw.tolive)
        hashMap["to talk"] = Triple("kalbėti - kalba - kalbėjo", R.drawable.talk,R.raw.talk)
        hashMap["to work"] = Triple("dirbti - dirba - dirbo", R.drawable.work,R.raw.work)
        hashMap["to pay"] = Triple("mokėti - moka - mokėjo", R.drawable.topay,R.raw.topay)
        hashMap["to write"] = Triple("rašyti - raso - rasė", R.drawable.write,R.raw.towrite)
        hashMap["to know"] = Triple("žinoti - žino - žinojo", R.drawable.know,R.raw.know)
        hashMap["to want"] = Triple("norėti - nori - norėjo", R.drawable.want,R.raw.towant)

        hashMap["to say"] = Triple("sakyti - sako - sakė", R.drawable.tosay,R.raw.say)
        hashMap["to do"] = Triple("daryti - daro - darė", R.drawable.todo,R.raw.todo)
        hashMap["to be"] = Triple("būti - yra - buvo", R.drawable.tobe,R.raw.tobe)
        hashMap["to bring"] = Triple("atnešti-atneša-atnešė", R.drawable.bring,R.raw.tobring)
        hashMap["push"] = Triple("stumti-stumia-stūmė", R.drawable.push,R.raw.topush)
        hashMap["pull"] = Triple("traukti-traukia-traukė", R.drawable.pull,R.raw.topull)
        hashMap["to go"] = Triple("eiti-eina-ėjo", R.drawable.go,R.raw.togo)
        hashMap["to see"] = Triple("pamatyti-pamato-pamatė", R.drawable.tosee,R.raw.tosee)
        hashMap["Can I see it?"] = Triple("ar galiu pamatyti?", R.drawable.canisee,R.raw.canseeit)
        hashMap["to sell"] = Triple("parduoti-parduoda-pardavė", R.drawable.tosell,R.raw.tosell)

        hashMap["to repair"] = Triple("sutaisyti", R.drawable.repair,R.raw.repair)
        hashMap["to take"] = Triple("paimti, imk", R.drawable.take,R.raw.take)
        hashMap["I walk and run"] = Triple("aš einu ir bėgu", R.drawable.walk,R.raw.iwalk)
        hashMap["I fly or swim"] = Triple("Aš skrendu arbo plaukiu", R.drawable.fly,R.raw.fly)
        hashMap["to open"] = Triple("atidaryti", R.drawable.open1,R.raw.open)
        hashMap["to close"] = Triple("uždaryti", R.drawable.close,R.raw.close)
        hashMap["depart and arrive"] = Triple("išvykti ir atvykti", R.drawable.depart,R.raw.departandarrive)
        hashMap["on and off"] = Triple("ijungti ir išjungti", R.drawable.onoff,R.raw.onoff)
        hashMap["to give"] = Triple("duoti", R.drawable.togive,R.raw.give)
        hashMap["I am willing and able"] = Triple("aš noriu ir galiu", R.drawable.ableto,R.raw.willing)

        hashMap["to put"] = Triple("padėti", R.drawable.toput,R.raw.put)
        hashMap["to find"] = Triple("rasti", R.drawable.tofind,R.raw.find)
        hashMap["to cook"] = Triple("virti", R.drawable.tocook,R.raw.cook)
        hashMap["to be produced by"] = Triple("gaminti", R.drawable.toproduce,R.raw.produced)



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
            findNavController().navigate(R.id.action_basicActionsFlashcard_to_toLearnFlashCards)
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