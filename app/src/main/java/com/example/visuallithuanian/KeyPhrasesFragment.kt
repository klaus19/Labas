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
import com.example.visuallithuanian.databinding.FragmentComputerTechnologyBinding
import com.example.visuallithuanian.databinding.FragmentKeyPhrasesBinding
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.ToLearnViewModel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


class KeyPhrasesFragment : Fragment() {
    lateinit var binding:FragmentKeyPhrasesBinding
    lateinit var viewModel: BottomNavigationViewModel

    lateinit var bottomNavigationView: BottomNavigationView
    private val counterViewModel: ToLearnViewModel by viewModels()

    private val hashMap = HashMap<String,Triple<String,Int,Int>>()

    private var currentTripleIndex =0
    private lateinit var currentTriple:Map.Entry<String,Triple<String,Int,Int>>

    var isFront=true
    private val totalTriples = 69 // change the value to the actual number of entries in your hashMap

    // declaring viewmodel
    private val cardViewModel: FlashCardViewmodel by viewModels {
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }


    @SuppressLint("ResourceType", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKeyPhrasesBinding.inflate(inflater,container,false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity())[BottomNavigationViewModel::class.java]


        bottomNavigationView.visibility = View.GONE


        // setting up listener for back Icon
        binding.backIcon?.setOnClickListener {
            activity?.onBackPressed()
        }
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_computerTechnologyFragment_to_flashCards)
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
        hashMap["I need ..."] = Triple("Man reikia ...", R.drawable.need,R.raw.need)
        hashMap["Go right"] = Triple("Eik į dešinę", R.drawable.goright,R.raw.right)
        hashMap["I missed the bus."] = Triple("Praleidau autobusą.", R.drawable.missedbus,R.raw.bus)
        hashMap["When is check-out?"] = Triple("Kada yra išsiregistravimas?", R.drawable.checkout,R.raw.checkout)
        hashMap["embassy"] = Triple("ambasada", R.drawable.embassy,R.raw.embassy)
        hashMap["I want to buy souvenirs."] = Triple("Noriu nusipirkti suvenyrų.", R.drawable.souvenir,R.raw.souvenirs)
        hashMap["double bed"] = Triple("dvigulė lova", R.drawable.doublebed,R.raw.doublebed)
        hashMap["What does this mean?"] = Triple("Ką tai reiškia?", R.drawable.mean,R.raw.mean)
        hashMap["We need a table for two."] = Triple("Mums reikia stalo dviems.", R.drawable.twotable,R.raw.tablefortwo)
        hashMap["pay by card"] = Triple("atsiskaityti kortele", R.drawable.paycard,R.raw.payby)

        hashMap["Can you show me on the map?"] = Triple("Ar galite man parodyti žemėlapyje?", R.drawable.map1,R.raw.map)
        hashMap["I need a doctor."] = Triple("Man reikia daktaro.", R.drawable.needdoctor,R.raw.doctor)
        hashMap["Where is the elevator?"] = Triple("Kur yra liftas?", R.drawable.elevator,R.raw.elevator)
        hashMap["Goodnight"] = Triple("Labanakt", R.drawable.goodnight,R.raw.goodnight)
        hashMap["We meet at the train station."] = Triple("Susitinkame traukinių stotyje.", R.drawable.trainstation,R.raw.trainstation)
        hashMap["Help!"] = Triple("Pagalba!", R.drawable.help,R.raw.help)
        hashMap["double room"] = Triple("dvivietis kambarys", R.drawable.doubleroom,R.raw.doubleroom)
        hashMap["Please"] = Triple("Prašau", R.drawable.please,R.raw.please)
        hashMap["Where is the nearest pharmacy?"] = Triple("Kur yra artimiausia vaistinė?", R.drawable.pharmacy,R.raw.pharmacy)
        hashMap["good evening"] = Triple("Labas vakaras", R.drawable.evening,R.raw.goodevening)

        hashMap["good day"] = Triple("Laba diena", R.drawable.goodday,R.raw.download)
        hashMap["Where is the bus stop?"] = Triple("Kur yra autobusų stotelė?", R.drawable.busstop,R.raw.download)
        hashMap["How much it costs?"] = Triple("Kiek tai kainuoja?", R.drawable.howmuchcost,R.raw.download)
        hashMap["Go left"] = Triple("Eik į kairę", R.drawable.left1,R.raw.download)
        hashMap["I do not understand"] = Triple("Nesuprantu.", R.drawable.dontunderstand,R.raw.download)
        hashMap["See you soon"] = Triple("Greitai pasimatysime", R.drawable.seeyou,R.raw.download)
        hashMap["See you there"] = Triple("Iki pasimatymo!", R.drawable.there,R.raw.download)
        hashMap["No"] = Triple("Ne", R.drawable.no,R.raw.download)
        hashMap["separate beds"] = Triple("atskiros lovos", R.drawable.separatebeds,R.raw.download)
        hashMap["Call an ambulance."] = Triple("Iškvieskite greitąją pagalbą.", R.drawable.ambulance,R.raw.download)

        hashMap["Can you repeat that?"] = Triple("Ar galite tai pakartoti?", R.drawable.repeat,R.raw.download)
        hashMap["Can I pay?"] = Triple("Ar galiu sumokėti?", R.drawable.canpay,R.raw.download)
        hashMap["What is your name?"] = Triple("Koks tavo vardas?", R.drawable.name,R.raw.download)
        hashMap["Go straight"] = Triple("Eiti tiesiai", R.drawable.straight,R.raw.download)
        hashMap["Forgive me"] = Triple("Atleisk", R.drawable.forgiveme,R.raw.download)
        hashMap["Can you write it down?"] = Triple("Ar galite tai užrašyti?", R.drawable.write,R.raw.download)
        hashMap["What do you recommend?"] = Triple("Ką rekomenduojate?", R.drawable.recommend,R.raw.download)
        hashMap["Yes"] = Triple("Taip", R.drawable.yes,R.raw.download)
        hashMap["Hello"] = Triple("Sveiki", R.drawable.hello1,R.raw.download)
        hashMap["Entrance"] = Triple("Įėjimas", R.drawable.entrance,R.raw.download)

        hashMap["Please slow down"] = Triple("Prašau lėčiau.", R.drawable.slowdown,R.raw.download)
        hashMap["In cash"] = Triple("grynais", R.drawable.cash,R.raw.download)
        hashMap[""] = Triple("", R.drawable.download,R.raw.download)
        hashMap[""] = Triple("", R.drawable.download,R.raw.download)
        hashMap[""] = Triple("", R.drawable.download,R.raw.download)
        hashMap[""] = Triple("", R.drawable.download,R.raw.download)
        hashMap[""] = Triple("", R.drawable.download,R.raw.download)
        hashMap[""] = Triple("", R.drawable.download,R.raw.download)
        hashMap[""] = Triple("", R.drawable.download,R.raw.download)
        hashMap[""] = Triple("", R.drawable.download,R.raw.download)

        hashMap[""] = Triple("", R.drawable.download,R.raw.download)
        hashMap[""] = Triple("", R.drawable.download,R.raw.download)
        hashMap[""] = Triple("", R.drawable.download,R.raw.download)
        hashMap[""] = Triple("", R.drawable.download,R.raw.download)
        hashMap[""] = Triple("", R.drawable.download,R.raw.download)
        hashMap[""] = Triple("", R.drawable.download,R.raw.download)
        hashMap[""] = Triple("", R.drawable.download,R.raw.download)
        hashMap[""] = Triple("", R.drawable.download,R.raw.download)
        hashMap[""] = Triple("", R.drawable.download,R.raw.download)
        hashMap[""] = Triple("", R.drawable.download,R.raw.download)


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
            findNavController().navigate(R.id.action_keyPhrasesFragment_to_toLearnFlashCards)
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