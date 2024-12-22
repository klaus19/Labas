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
import com.example.visuallithuanian.databinding.FragmentFoodIngrediantsBinding
import com.example.visuallithuanian.model.MediumProgressPreferencesHelper
import com.example.visuallithuanian.model.PreferencesHelper
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.ToLearnViewModel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


class FoodIngrediants : Fragment() {
    lateinit var binding: FragmentFoodIngrediantsBinding
    lateinit var viewModel: BottomNavigationViewModel

    lateinit var bottomNavigationView: BottomNavigationView
    private val counterViewModel: ToLearnViewModel by viewModels()
    private val hashMap = HashMap<String, Triple<String, Int, Int>>()

    private var currentTripleIndex = 0
    private lateinit var currentTriple: Map.Entry<String, Triple<String, Int, Int>>

    var isFront = true
    private val totalTriples = 45 // change the value to the actual number of entries in your hashMap
    private lateinit var preferencesHelper: PreferencesHelper
    private lateinit var mediumProgressPreferencesHelper: MediumProgressPreferencesHelper
    // declaring viewmodel
    private val cardViewModel: FlashCardViewmodel by viewModels {
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodIngrediantsBinding.inflate(inflater,container,false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity())[BottomNavigationViewModel::class.java]

        bottomNavigationView.visibility = View.GONE

        preferencesHelper = PreferencesHelper(requireContext())
        mediumProgressPreferencesHelper = MediumProgressPreferencesHelper((requireContext()))
        // Restore saved progress and counter
        val savedCounter = preferencesHelper.getCounter()
        val savedProgress = mediumProgressPreferencesHelper.getProgressFood()
        counterViewModel.setCounter(savedCounter) // Assuming ToLearnViewModel has a method to set counter

        // setting up listener for back Icon
        binding.backIcon?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_foodIngrediants_to_sentenceFragment)
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

        hashMap["steak"] = Triple("kepsnys", R.drawable.steak, R.raw.steak)
        hashMap["snack"] = Triple("užkandis", R.drawable.snack, R.raw.snack)
        hashMap["sausage"] = Triple("dešra", R.drawable.sausages, R.raw.sausage)
        hashMap["pear"] = Triple("kriaušė", R.drawable.pear, R.raw.pear)
        hashMap["still water"] = Triple("Negazuotas vanduo", R.drawable.water1, R.raw.stillwater)
        hashMap["Watermelon"] = Triple("arbūzas", R.drawable.watermelon, R.raw.watermelon)
        hashMap["Salad"] = Triple("salotos", R.drawable.salad, R.raw.salad)
        hashMap["Carrot"] = Triple("morką", R.drawable.carrot, R.raw.carrot)
        hashMap["Ham"] = Triple("kumpis", R.drawable.ham, R.raw.ham)
        hashMap["Beef"] = Triple("jautiena", R.drawable.beef, R.raw.beef)
        hashMap["Potato"] = Triple("bulvė", R.drawable.potato, R.raw.potato)
        hashMap["crumbs"] = Triple("trupiniai", R.drawable.crumbs, R.raw.crumbs)
        hashMap["Apples"] = Triple("obuoliai", R.drawable.apple1, R.raw.apples)
        hashMap["marmalade"] = Triple("marmeladas", R.drawable.marmalade, R.raw.marmalade)
        hashMap["Pineapple"] = Triple("ananasas", R.drawable.pineapple, R.raw.pineapple)
        hashMap["Tea"] = Triple("arbata", R.drawable.tea1, R.raw.tea)
        hashMap["Bread"] = Triple("duona", R.drawable.bread1, R.raw.bread1)
        hashMap["cheese"] = Triple("sūris", R.drawable.cheese, R.raw.cheese)
        hashMap["yogurt"] = Triple("jogurtas", R.drawable.yogurt, R.raw.yogurt)
        hashMap["salt"] = Triple("druska", R.drawable.salt, R.raw.salt)
        hashMap["butter"] = Triple("sviestas", R.drawable.butter, R.raw.butter)
        hashMap["Strawberry"] = Triple("Braškė", R.drawable.strawberry, R.raw.strawberry)
        hashMap["Orange"] = Triple("apelsinas", R.drawable.orange, R.raw.orangefruit)
        hashMap["lime"] = Triple("žalioji citrina", R.drawable.lime, R.raw.lime)
        hashMap["lemon"] = Triple("citrina", R.drawable.lemon, R.raw.lemon)
        hashMap["mustard"] = Triple("garstyčios", R.drawable.mustard, R.raw.mustard)
        hashMap["avocado"] = Triple("avokadas", R.drawable.avocado, R.raw.avacado)
        hashMap["Onion"] = Triple("svogūnas", R.drawable.onion, R.raw.onion)
        hashMap["mushroom"] = Triple("grybas", R.drawable.mushrooms, R.raw.mushroom)
        hashMap["cherry"] = Triple("vyšnia", R.drawable.cherry, R.raw.cherry)
        hashMap["cauliflower"] = Triple("žiedinis kopūstas", R.drawable.cauliflower, R.raw.cauliflower)
        hashMap["salami"] = Triple("saliamis", R.drawable.salami, R.raw.salami)
        hashMap["juice"] = Triple("sultys", R.drawable.juices, R.raw.juice)
        hashMap["Pasta"] = Triple("Makaronai", R.drawable.pasta, R.raw.pasta)
        hashMap["Chicken"] = Triple("vištiena", R.drawable.chicken, R.raw.chicken)
        hashMap["Sauce"] = Triple("padažas", R.drawable.sauces, R.raw.sauce)
        hashMap["grapes"] = Triple("vynuogės", R.drawable.grape, R.raw.grapes)
        hashMap["pork"] = Triple("kiauliena", R.drawable.pork, R.raw.pork)
        hashMap["rice"] = Triple("ryžiai", R.drawable.rice, R.raw.rice)
        hashMap["spaghetti"] = Triple("spagečiai", R.drawable.spaghetti, R.raw.sphageeti)
        hashMap["broccoli"] = Triple("brokolis", R.drawable.broccoli, R.raw.brocoli)
        hashMap["milk"] = Triple("pienas", R.drawable.milk, R.raw.milk)
        hashMap["wine"] = Triple("vynas", R.drawable.wine, R.raw.wine)
        hashMap["meat"] = Triple("mėsa", R.drawable.meat, R.raw.meat)



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

        //Navigating from one fragment to another
        binding.gifImageView.setOnClickListener {
            findNavController().navigate(R.id.action_foodIngrediants_to_toLearnFlashCards)
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
                mediumProgressPreferencesHelper.saveProgressFood(progress) // Save progress

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