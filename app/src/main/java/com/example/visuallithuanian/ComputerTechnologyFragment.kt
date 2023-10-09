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
import com.example.visuallithuanian.databinding.FragmentDailyBasicBinding
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.ToLearnViewModel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class ComputerTechnologyFragment : Fragment() {


    lateinit var binding: FragmentComputerTechnologyBinding
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
        binding = FragmentComputerTechnologyBinding.inflate(inflater,container,false)

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

        hashMap["Computer"] = Triple("Kompiuteris", R.drawable.computer1,R.raw.computer)
        hashMap["screen"] = Triple("ekranas", R.drawable.screen,R.raw.screen)
        hashMap["slot"] = Triple("lizdas", R.drawable.slot,R.raw.socket)
        hashMap["Programmer"] = Triple("Programuotojas", R.drawable.programmer,R.raw.programmer)
        hashMap["Interface"] = Triple("Sąsaja", R.drawable.interface1,R.raw.interface1)
        hashMap["sent an email"] = Triple("Išsiuntė elektroninį laišką.", R.drawable.sentemail,R.raw.sentanemail)
        hashMap["account"] = Triple("sąskaitą", R.drawable.account1,R.raw.account)
        hashMap["network"] = Triple("tinklo", R.drawable.network,R.raw.network)
        hashMap["retrieve"] = Triple("atkurti", R.drawable.retrieve,R.raw.retrieve)
        hashMap["a backup copy"] = Triple("atsarginę kopiją", R.drawable.backupcopy,R.raw.backupcopy)
        hashMap["record"] = Triple("įrašas", R.drawable.record,R.raw.record)
        hashMap["Log out"] = Triple("Atsijungti", R.drawable.logout,R.raw.logout)
        hashMap["to save"] = Triple("sutaupyti", R.drawable.tosave,R.raw.tosave)
        hashMap["set-up"] = Triple("sąranka", R.drawable.setup,R.raw.setup)
        hashMap["computer mouse"] = Triple("Kompiuterio pelė", R.drawable.computermouse,R.raw.computermouse)


        hashMap["awake"] = Triple("budrus", R.drawable.awake,R.raw.sleep)
        hashMap["Download"] = Triple("parsisiųsti", R.drawable.download,R.raw.sleep)
        hashMap["Browse"] = Triple("naršyti", R.drawable.browse,R.raw.sleep)
        hashMap["Comment"] = Triple("komentuoti", R.drawable.comment,R.raw.sleep)
        hashMap["Log In"] = Triple("Prisijungti", R.drawable.login,R.raw.sleep)
        hashMap["Social Networks"] = Triple("Socialiniai tinklai", R.drawable.socialnetworks,R.raw.sleep)
        hashMap["Keyboard"] = Triple("klaviatūra", R.drawable.keyboard,R.raw.sleep)
        hashMap["Laptop"] = Triple("Nešiojamas kompiuteris", R.drawable.laptop,R.raw.sleep)
        hashMap["Share"] = Triple("Dalintis", R.drawable.share,R.raw.sleep)
        hashMap["Desktop Computer"] = Triple("Stalinis kompiuteris", R.drawable.desktopcomputer,R.raw.sleep)
        hashMap["Click"] = Triple("Spustelėkite", R.drawable.click,R.raw.sleep)
        hashMap["Enable"] = Triple("Įjungti", R.drawable.enable,R.raw.sleep)
        hashMap["Report"] = Triple("Pranešimas", R.drawable.report,R.raw.sleep)
        hashMap["Message"] = Triple("pranešimą", R.drawable.message,R.raw.sleep)
        hashMap["creep downwards"] = Triple("slinkti žemyn", R.drawable.creepdownwards,R.raw.sleep)
        hashMap["Application"] = Triple("Taikymas", R.drawable.application,R.raw.sleep)
        hashMap["scroll up"] = Triple("slinkite aukštyn", R.drawable.scrollup,R.raw.sleep)
        hashMap["install"] = Triple("diegti", R.drawable.install,R.raw.sleep)
        hashMap["Error"] = Triple("klaida", R.drawable.error,R.raw.sleep)
        hashMap["antivirus"] = Triple("antivirusas", R.drawable.antivirus,R.raw.sleep)
        hashMap["Connect to the internet"] = Triple("prisijungti prie interneto", R.drawable.connect2internet,R.raw.sleep)

        hashMap["data"] = Triple("duomenis", R.drawable.data,R.raw.sleep)
        hashMap["Font"] = Triple("šriftas", R.drawable.font,R.raw.sleep)
        hashMap["Video card"] = Triple("Vaizdo plokštė", R.drawable.videocard,R.raw.sleep)
        hashMap["hard disk drive"] = Triple("kietasis diskas", R.drawable.harddisk,R.raw.sleep)
        hashMap["an icon for"] = Triple("piktograma", R.drawable.iconfor,R.raw.sleep)
        hashMap["internet"] = Triple("internetas", R.drawable.internet,R.raw.sleep)
        hashMap["Progress"] = Triple("progresas", R.drawable.progress,R.raw.sleep)
        hashMap["Operating System"] = Triple("Operacinė sistema", R.drawable.operationalsystem,R.raw.sleep)
        hashMap["Follow"] = Triple("sekite", R.drawable.follow,R.raw.sleep)
        hashMap["delete"] = Triple("Ištrinti", R.drawable.delete,R.raw.sleep)
        hashMap["Upload to"] = Triple("įkelti", R.drawable.upload,R.raw.sleep)
        hashMap["Pixels"] = Triple("pikselių", R.drawable.pixels,R.raw.sleep)
        hashMap["Programme"] = Triple("Programa", R.drawable.programme,R.raw.sleep)
        hashMap["send request"] = Triple("siųsti užklausą", R.drawable.sendrequest,R.raw.sleep)
        hashMap["headphones"] = Triple("ausines", R.drawable.headphones,R.raw.sleep)
        hashMap["Password"] = Triple("Slaptažodis", R.drawable.password,R.raw.sleep)
        hashMap["Virus"] = Triple("virusas", R.drawable.virus,R.raw.sleep)
        hashMap["update"] = Triple("atnaujinti", R.drawable.update,R.raw.sleep)
        hashMap["reduce"] = Triple("sumažinti", R.drawable.reduce,R.raw.sleep)
        hashMap["increase"] = Triple("padidinti", R.drawable.increase,R.raw.sleep)


        hashMap["relocation"] = Triple("perkėlimas", R.drawable.relocation,R.raw.sleep)
        hashMap["Voice message"] = Triple("Balso pranešimas", R.drawable.voicemessage,R.raw.sleep)
        hashMap["microphone"] = Triple("mikrofonas", R.drawable.microphone,R.raw.sleep)
        hashMap["Printed from"] = Triple("spausdinti", R.drawable.printedfrom,R.raw.sleep)
        hashMap["reload"] = Triple("perkrauti", R.drawable.reload,R.raw.sleep)
        hashMap["page"] = Triple("puslapis", R.drawable.page,R.raw.sleep)
        hashMap["Spam"] = Triple("šlamštas", R.drawable.spam,R.raw.sleep)
        hashMap["advertising"] = Triple("reklama", R.drawable.advertising,R.raw.sleep)
        hashMap["unblock"] = Triple("atblokuoti", R.drawable.unblock,R.raw.sleep)
        hashMap["transmission"] = Triple("užkrato pernešimas", R.drawable.transmission,R.raw.sleep)
        hashMap["antenna"] = Triple("antena", R.drawable.antenna,R.raw.sleep)
        hashMap["Controller"] = Triple("valdiklis", R.drawable.controller,R.raw.sleep)



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