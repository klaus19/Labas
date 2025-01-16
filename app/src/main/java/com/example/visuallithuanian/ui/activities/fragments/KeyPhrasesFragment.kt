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
import com.example.visuallithuanian.databinding.FragmentKeyPhrasesBinding
import com.example.visuallithuanian.model.EasyPreferencesHelper
import com.example.visuallithuanian.model.PreferencesHelper
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
    private val hashMap = HashMap<String, Triple<String, Int, Int>>()

    private var currentTripleIndex = 0
    private lateinit var currentTriple: Map.Entry<String, Triple<String, Int, Int>>

    var isFront=true
    private val totalTriples = 69 // change the value to the actual number of entries in your hashMap
    private lateinit var preferencesHelper: PreferencesHelper
    private lateinit var easyPreferencesHelper: EasyPreferencesHelper
    // declaring viewmodel
    private val cardViewModel: FlashCardViewmodel by viewModels {
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKeyPhrasesBinding.inflate(inflater,container,false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity())[BottomNavigationViewModel::class.java]

        bottomNavigationView.visibility = View.GONE

        preferencesHelper = PreferencesHelper(requireContext())
        easyPreferencesHelper = EasyPreferencesHelper(requireContext())
        // Restore saved progress and counter
        val savedCounter = preferencesHelper.getCounter()
        val savedProgress = easyPreferencesHelper.getProgressKeyPhrases()
        counterViewModel.setCounter(savedCounter) // Assuming ToLearnViewModel has a method to set counter

        // setting up listener for back Icon
        binding.backIcon?.setOnClickListener {
            activity?.onBackPressed()
        }
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_keyPhrasesFragment_to_sentenceFragment)
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

        hashMap["Where is the elevator?"] = Triple("Kur yra liftas?",
            R.drawable.elevator,
            R.raw.elevator
        )
        hashMap["Goodnight"] = Triple("Labanakt", R.drawable.goodnight, R.raw.goodnight)

        hashMap["Help!"] = Triple("Pagalba!", R.drawable.help, R.raw.help)
        hashMap["double room"] = Triple("dvivietis kambarys",
            R.drawable.doubleroom,
            R.raw.doubleroom
        )
        hashMap["Please"] = Triple("Prašau", R.drawable.please, R.raw.please)

        hashMap["good evening"] = Triple("Labas vakaras", R.drawable.evening, R.raw.goodevening)

        hashMap["good day"] = Triple("Laba diena", R.drawable.goodday, R.raw.labadiena)
        hashMap["Where is the bus stop?"] = Triple("Kur yra autobusų stotelė?",
            R.drawable.busstop,
            R.raw.kuryraautobusustotele
        )
        hashMap["How much does it costs?"] = Triple("Kiek tai kainuoja?",
            R.drawable.howmuchcost,
            R.raw.howmuchdoesitcoast
        )
        hashMap["Go left"] = Triple("Eik į kairę", R.drawable.left1, R.raw.eikikaire)
        hashMap["I do not understand"] = Triple("Nesuprantu",
            R.drawable.dontunderstand,
            R.raw.idonotunderstand
        )
        hashMap["Where is the nearest pharmacy?"] = Triple("Kur yra artimiausia vaistinė?",
            R.drawable.pharmacy,
            R.raw.whereisthenearestpharmacy
        )
        hashMap["I need a doctor."] = Triple("Man reikia daktaro.",
            R.drawable.needdoctor,
            R.raw.ineedadoctor
        )
        hashMap["We meet at the train station."] = Triple("Susitinkame traukinių stotyje.",
            R.drawable.trainstation,
            R.raw.wemeetattrainstation
        )
        hashMap["separate beds"] = Triple("atskiros lovos",
            R.drawable.separatebeds,
            R.raw.separatebeds
        )
        hashMap["Please slow down"] = Triple("Prašau lėčiau.", R.drawable.slowdown, R.raw.pleaseslowdown)
        hashMap["In cash"] = Triple("grynais", R.drawable.cash, R.raw.incash)
        hashMap["See you soon"] = Triple("Greitai pasimatysime",
            R.drawable.seeyou,
            R.raw.greitaipasimatysime
        )
        hashMap["See you there"] = Triple("Iki pasimatymo!", R.drawable.there, R.raw.ikipasimatymo)
        hashMap["No"] = Triple("Ne", R.drawable.no, R.raw.ne)

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

        hashMap["Is there a good restaurant nearby?"] = Triple("Ar netoli yra geras restoranas?",
            R.drawable.restaurant,
            R.raw.restaurant
        )
        hashMap["coins"] = Triple("monetų", R.drawable.coins, R.raw.coins)
        hashMap["airport"] = Triple("oro uostas", R.drawable.airport, R.raw.airport)
        hashMap["Where is the ATM?"] = Triple("Kur yra bankomatas?", R.drawable.atm, R.raw.whereistheatm)
        hashMap["Do you know where ... is?"] = Triple("Ar žinote, kur yra ...?",
            R.drawable.doyouknowhere,
            R.raw.doyouknowhereis
        )
        hashMap["train station"] = Triple("traukinių stotis",
            R.drawable.trainstation1,
            R.raw.trainstation
        )
        hashMap["at the police station"] = Triple("policijos nuovadoje",
            R.drawable.policestation,
            R.raw.atthepolicestation
        )

        hashMap["single room"] = Triple("vienvietis kambarys",
            R.drawable.singleroom,
            R.raw.single
        )
        hashMap["passport"] = Triple("pasas", R.drawable.passport, R.raw.passport)
        hashMap["Where is the toilet?"] = Triple("Kur yra tualetas?",
            R.drawable.toilet,
            R.raw.whereisthetoilet
        )
        hashMap["Reception"] = Triple("Registratūra", R.drawable.receiption, R.raw.receiption)
        
        hashMap["Thank you"] = Triple("Ačiū", R.drawable.thankyou, R.raw.thankyou)
        hashMap["Delicious"] = Triple("Skanus", R.drawable.delicious, R.raw.delicious)
        hashMap["My name is"] = Triple("Mano vardas yra", R.drawable.myname, R.raw.mynameeis)
        hashMap["boarding pass"] = Triple("įlaipinimo bilietas",
            R.drawable.boardingpass,
            R.raw.boardingpass
        )
        hashMap["Do you speak English?"] = Triple("Ar tu kalbi angliškai?",
            R.drawable.english,
            R.raw.doyouspeakenglish
        )

        hashMap["bank note"] = Triple("banko kupiūra", R.drawable.banknote, R.raw.banknote)
        hashMap["exit"] = Triple("išėjimas", R.drawable.exit1, R.raw.exit)
        hashMap["Nice to meet you"] = Triple("Malonu susipažinti ",
            R.drawable.nicetomeetyou,
            R.raw.nicetomeetyou
        )
        hashMap["I do not speak english."] = Triple("Aš nemoku anglų kalbos.",
            R.drawable.english,
            R.raw.idonotspeakenglish
        )
        hashMap["When does the bus leave?"] = Triple("Kada išvyksta autobusas?",
            R.drawable.busstop,
            R.raw.whendoesthebusleave
        )
        hashMap["How are you?"] = Triple("Kaip laikaisi?", R.drawable.howru, R.raw.howareyou)
        hashMap["How to say...."] = Triple("Kaip pasakyti....", R.drawable.howtosay, R.raw.howtosay)
        hashMap["Can you call a taxi?"] = Triple("Ar galite išsikviesti taksi?",
            R.drawable.taxi,
            R.raw.canyoucallataxi
        )
        hashMap["map"] = Triple("Žemėlapis", R.drawable.map1, R.raw.map)
        hashMap["I am allergic to nuts."] = Triple("Esu alergiška riešutams.",
            R.drawable.nuts,
            R.raw.iamallergictonuts
        )
        hashMap["I don't know"] = Triple("Nežinau", R.drawable.idontknow, R.raw.idontknow)
        hashMap["barter shop"] = Triple("mainų parduotuvė", R.drawable.bartershop, R.raw.bartershop)
        hashMap["bus station"] = Triple("autobusų stotis", R.drawable.busstation, R.raw.bustation)
        hashMap["Can I borrow ...?"] = Triple("Ar galiu pasiskolinti ...?",
            R.drawable.borrow,
            R.raw.caniborrow
        )
        hashMap["When does breakfast start?"] = Triple("Kada prasideda pusryčiai?",
            R.drawable.breakfast,
            R.raw.whendoesbreakfaststart
        )
        hashMap["I'm sorry"] = Triple("Atsiprašau", R.drawable.sorry, R.raw.iamsorry)
        hashMap["Enjoy your stay."] = Triple("Mėgaukitės viešnage.",
            R.drawable.enjoyurstay,
            R.raw.enjoyyourstay
        )
        hashMap["Can I get your ID card?"] = Triple("Ar galiu gauti jūsų asmens tapatybės kortelę?",
            R.drawable.idcard1,
            R.raw.canigetyouridcard
        )



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

        //Displaying GIF image on the screen
        Glide.with(this).asGif().load(R.drawable.finger1).into(binding.gifImageView)

        binding.gifImageView.setOnClickListener {
            findNavController().navigate(R.id.action_keyPhrasesFragment_to_toLearnFlashCards)
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
                easyPreferencesHelper.saveProgressKeyPhrases(progress)// Save progress

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