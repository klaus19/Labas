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
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.visuallithuanian.R
import com.example.visuallithuanian.database.FlashcardPair
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
            findNavController().navigate(R.id.action_keyPhrasesFragment_to_sentenceFragment)
        }
        //changing color of progress bar progress
        binding.progressHorizontal.progressTintList = ColorStateList.valueOf(
            ContextCompat.getColor(requireContext()
                , R.color.float1
            ))

        //changing color of background color of progress bar
        binding.progressHorizontal.progressBackgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(requireContext(),
                R.color.silver
            ))

        // Hashmap of strings that will shown on cardview front and back side
        hashMap["I need ..."] = Triple("Man reikia ...", R.drawable.need, R.raw.need)
        hashMap["Go right"] = Triple("Eik į dešinę", R.drawable.goright, R.raw.right)
        hashMap["I missed the bus."] = Triple("Praleidau autobusą.",
            R.drawable.missedbus,
            R.raw.bus
        )
        hashMap["When is check-out?"] = Triple("Kada yra išsiregistravimas?",
            R.drawable.checkout,
            R.raw.checkout
        )
        hashMap["embassy"] = Triple("ambasada", R.drawable.embassy, R.raw.embassy)
        hashMap["I want to buy souvenirs."] = Triple("Noriu nusipirkti suvenyrų.",
            R.drawable.souvenir,
            R.raw.souvenirs
        )
        hashMap["double bed"] = Triple("dvigulė lova", R.drawable.doublebed, R.raw.doublebed)
        hashMap["What does this mean?"] = Triple("Ką tai reiškia?", R.drawable.mean, R.raw.mean)
        hashMap["We need a table for two."] = Triple("Mums reikia stalo dviems.",
            R.drawable.twotable,
            R.raw.tablefortwo
        )
        hashMap["pay by card"] = Triple("atsiskaityti kortele", R.drawable.paycard, R.raw.payby)

        hashMap["Can you show me on the map?"] = Triple("Ar galite man parodyti žemėlapyje?",
            R.drawable.map1,
            R.raw.map
        )
        hashMap["I need a doctor."] = Triple("Man reikia daktaro.",
            R.drawable.needdoctor,
            R.raw.doctor
        )
        hashMap["Where is the elevator?"] = Triple("Kur yra liftas?",
            R.drawable.elevator,
            R.raw.elevator
        )
        hashMap["Goodnight"] = Triple("Labanakt", R.drawable.goodnight, R.raw.goodnight)
        hashMap["We meet at the train station."] = Triple("Susitinkame traukinių stotyje.",
            R.drawable.trainstation,
            R.raw.trainstation
        )
        hashMap["Help!"] = Triple("Pagalba!", R.drawable.help, R.raw.help)
        hashMap["double room"] = Triple("dvivietis kambarys",
            R.drawable.doubleroom,
            R.raw.doubleroom
        )
        hashMap["Please"] = Triple("Prašau", R.drawable.please, R.raw.please)
        hashMap["Where is the nearest pharmacy?"] = Triple("Kur yra artimiausia vaistinė?",
            R.drawable.pharmacy,
            R.raw.pharmacy
        )
        hashMap["good evening"] = Triple("Labas vakaras", R.drawable.evening, R.raw.goodevening)

        hashMap["good day"] = Triple("Laba diena", R.drawable.goodday, R.raw.labadiena)
        hashMap["Where is the bus stop?"] = Triple("Kur yra autobusų stotelė?",
            R.drawable.busstop,
            R.raw.kuryraautobusustotele
        )
        hashMap["How much it costs?"] = Triple("Kiek tai kainuoja?",
            R.drawable.howmuchcost,
            R.raw.kiektaikainuoja
        )
        hashMap["Go left"] = Triple("Eik į kairę", R.drawable.left1, R.raw.eikikaire)
        hashMap["I do not understand"] = Triple("Nesuprantu.",
            R.drawable.dontunderstand,
            R.raw.nesuprantu
        )
        hashMap["See you soon"] = Triple("Greitai pasimatysime",
            R.drawable.seeyou,
            R.raw.greitaipasimatysime
        )
        hashMap["See you there"] = Triple("Iki pasimatymo!", R.drawable.there, R.raw.ikipasimatymo)
        hashMap["No"] = Triple("Ne", R.drawable.no, R.raw.ne)
        hashMap["separate beds"] = Triple("atskiros lovos",
            R.drawable.separatebeds,
            R.raw.atskiroslovos
        )
        hashMap["Call an ambulance."] = Triple("Iškvieskite greitąją pagalbą.",
            R.drawable.ambulance,
            R.raw.iskvieskitegreitajapagalba
        )

        hashMap["Can you repeat that?"] = Triple("Ar galite tai pakartoti?",
            R.drawable.repeat,
            R.raw.argalitetaipakartoti
        )
        hashMap["Can I pay?"] = Triple("Ar galiu sumokėti?",
            R.drawable.canpay,
            R.raw.argaliusumoketi
        )
        hashMap["What is your name?"] = Triple("Koks tavo vardas?",
            R.drawable.name,
            R.raw.kokstavovardas
        )
        hashMap["Go straight"] = Triple("Eiti tiesiai", R.drawable.straight, R.raw.eiktiesiai)
        hashMap["Forgive me"] = Triple("Atleisk", R.drawable.forgiveme, R.raw.atleisk)
        hashMap["Can you write it down?"] = Triple("Ar galite tai užrašyti?",
            R.drawable.write,
            R.raw.argalitetaiuzrasyti
        )
        hashMap["What do you recommend?"] = Triple("Ką rekomenduojate?",
            R.drawable.recommend,
            R.raw.karekomenduojate
        )
        hashMap["Yes"] = Triple("Taip", R.drawable.yes, R.raw.taip)
        hashMap["Hello"] = Triple("Sveiki", R.drawable.hello1, R.raw.sveiki)
        hashMap["Entrance"] = Triple("Įėjimas", R.drawable.entrance, R.raw.iejimas)

        hashMap["Please slow down"] = Triple("Prašau lėčiau.", R.drawable.slowdown, R.raw.download)
        hashMap["In cash"] = Triple("grynais", R.drawable.cash, R.raw.download)
        hashMap["Is there a good restaurant nearby?"] = Triple("Ar netoli yra geras restoranas?",
            R.drawable.restaurant,
            R.raw.download
        )
        hashMap["Nice to meet you"] = Triple("Malonu tave matyti",
            R.drawable.meetyou,
            R.raw.download
        )
        hashMap["coins"] = Triple("monetų", R.drawable.coins, R.raw.download)
        hashMap["airport"] = Triple("oro uostas", R.drawable.airport, R.raw.download)
        hashMap["Where is the ATM?"] = Triple("Kur yra bankomatas?", R.drawable.atm, R.raw.download)
        hashMap["Do you know where ... is?"] = Triple("Ar žinote, kur yra ...?",
            R.drawable.doyouknowhere,
            R.raw.download
        )
        hashMap["train station"] = Triple("traukinių stotis",
            R.drawable.trainstation1,
            R.raw.download
        )
        hashMap["at the police station"] = Triple("policijos nuovadoje",
            R.drawable.policestation,
            R.raw.download
        )

        hashMap["single room"] = Triple("vienvietis kambarys",
            R.drawable.singleroom,
            R.raw.download
        )
        hashMap["passport"] = Triple("pasas", R.drawable.airport, R.raw.download)
        hashMap["Where is the toilet?"] = Triple("Kur yra tualetas?",
            R.drawable.toilet,
            R.raw.download
        )
        hashMap["Reception"] = Triple("Registratūra", R.drawable.receiption, R.raw.download)
        hashMap["How do I get to ....?"] = Triple("Kaip man nusigauti į ....?",
            R.drawable.howget,
            R.raw.download
        )
        hashMap["Thank you"] = Triple("Ačiū", R.drawable.thankyou, R.raw.download)
        hashMap["Delicious"] = Triple("Skanaus.", R.drawable.delicious, R.raw.download)
        hashMap["My name is"] = Triple("Mano vardas yra", R.drawable.myname, R.raw.download)
        hashMap["boarding pass"] = Triple("įlaipinimo bilietas",
            R.drawable.boardingpass,
            R.raw.download
        )
        hashMap["Do you speak English?"] = Triple("Ar tu kalbi angliškai?",
            R.drawable.english,
            R.raw.download
        )

        hashMap["bank note"] = Triple("banko kupiūra", R.drawable.banknote, R.raw.download)
        hashMap["exit"] = Triple("išėjimas", R.drawable.exit1, R.raw.download)
        hashMap["Nice to meet you"] = Triple("Malonu susipažinti",
            R.drawable.nicetomeetyou,
            R.raw.download
        )
        hashMap["I do not speak english."] = Triple("Aš nemoku anglų kalbos.",
            R.drawable.english,
            R.raw.download
        )
        hashMap["When does the bus leave?"] = Triple("Kada išvyksta autobusas?",
            R.drawable.busstop,
            R.raw.download
        )
        hashMap["How are you?"] = Triple("Kaip laikaisi?", R.drawable.howru, R.raw.download)
        hashMap["How to say...."] = Triple("Kaip sakyti....", R.drawable.howtosay, R.raw.download)
        hashMap["Can you call a taxi?"] = Triple("Ar galite išsikviesti taksi?",
            R.drawable.taxi,
            R.raw.download
        )
        hashMap["map"] = Triple("Žemėlapis", R.drawable.map1, R.raw.download)
        hashMap["I am allergic to nuts."] = Triple("Esu alergiška riešutams.",
            R.drawable.nuts,
            R.raw.download
        )
        hashMap["I don't know"] = Triple("Nežinau", R.drawable.idontknow, R.raw.download)
        hashMap["barter shop"] = Triple("mainų parduotuvė", R.drawable.bartershop, R.raw.download)
        hashMap["bus station"] = Triple("autobusų stotis", R.drawable.busstation, R.raw.download)
        hashMap["Can I borrow ...?"] = Triple("Ar galiu pasiskolinti ...?",
            R.drawable.borrow,
            R.raw.download
        )
        hashMap["When does breakfast start?"] = Triple("Kada prasideda pusryčiai?",
            R.drawable.breakfast,
            R.raw.download
        )
        hashMap["I'm sorry"] = Triple("Atsiprašau", R.drawable.sorry, R.raw.download)
        hashMap["Enjoy your stay."] = Triple("Mėgaukitės viešnage.",
            R.drawable.enjoyurstay,
            R.raw.download
        )
        hashMap["How long does it take to get to ....?"] = Triple("Per kiek laiko reikia patekti į ....?",
            R.drawable.howlong,
            R.raw.download
        )
        hashMap["Can I get your ID card?"] = Triple("Ar galiu gauti jūsų asmens tapatybės kortelę?",
            R.drawable.idcard1,
            R.raw.download
        )



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
            binding.textCounterLearn.text = count.toString()
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
            imageLeft.setOnClickListener {
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
                    cardViewQuestions.setCardBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.new_design_text_color
                    ))

                } else {
                    currentTripleIndex = (currentTripleIndex + 1) % hashMap.size
                    textCardFront.visibility = View.VISIBLE
                    textCardBack.visibility = View.VISIBLE
                    imageFlashCard.visibility = View.VISIBLE
                    cardViewQuestions.setCardBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.orange1
                    ))
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