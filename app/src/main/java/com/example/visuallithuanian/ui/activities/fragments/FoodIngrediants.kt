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
import com.example.visuallithuanian.databinding.FragmentFoodIngrediantsBinding
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

    private val sharedPrefFile = "com.example.visuallithuanian.PREFERENCE_FILE_KEY"
    lateinit var bottomNavigationView: BottomNavigationView
    private val counterViewModel: ToLearnViewModel by viewModels()
    private val hashMap = HashMap<String, Triple<String, Int, Int>>()

    private var currentTripleIndex = 0
    private lateinit var currentTriple: Map.Entry<String, Triple<String, Int, Int>>

    var isFront = true
    private val totalTriples = 45 // change the value to the actual number of entries in your hashMap
    private lateinit var preferencesHelper: PreferencesHelper
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
        // Restore saved progress and counter
        val savedCounter = preferencesHelper.getCounter()
        val savedProgress = preferencesHelper.getProgress()
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
                R.color.orange1
            )
        )

        // changing color of background color of progress bar
        binding.progressHorizontal.progressBackgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                requireContext(),
                R.color.silver
            )
        )
        hashMap["mushroom"] = Triple("pievagrybis", R.drawable.mushrooms, R.raw.sleep)
        hashMap["steak"] = Triple("kepsnys", R.drawable.steak, R.raw.sleep)
        hashMap["snack"] = Triple("užkandis", R.drawable.snack, R.raw.sleep)
        hashMap["sausage"] = Triple("dešra", R.drawable.sausages, R.raw.sleep)
        hashMap["pear"] = Triple("kriaušė", R.drawable.pear, R.raw.sleep)
        hashMap["still water"] = Triple("Negazuotas vanduo", R.drawable.water1, R.raw.sleep)
        hashMap["Watermelon"] = Triple("arbūzas", R.drawable.watermelon, R.raw.sleep)
        hashMap["Salad"] = Triple("salotos", R.drawable.salad, R.raw.sleep)
        hashMap["Carrot"] = Triple("morką", R.drawable.carrot, R.raw.sleep)
        hashMap["Ham"] = Triple("kumpis", R.drawable.ham, R.raw.sleep)
        hashMap["Beef"] = Triple("jautiena", R.drawable.beef, R.raw.sleep)
        hashMap["Potato"] = Triple("bulvė", R.drawable.potato, R.raw.sleep)
        hashMap["crumbs"] = Triple("dribsniai", R.drawable.crumbs, R.raw.sleep)
        hashMap["Apples"] = Triple("obuolių", R.drawable.apple1, R.raw.sleep)
        hashMap["marmalade"] = Triple("marmeladas", R.drawable.marmalade, R.raw.sleep)
        hashMap["Pineapple"] = Triple("ananasas", R.drawable.pineapple, R.raw.sleep)
        hashMap["Tea"] = Triple("arbata", R.drawable.tea1, R.raw.sleep)
        hashMap["Bread"] = Triple("duona", R.drawable.bread1, R.raw.sleep)
        hashMap["cheese"] = Triple("sūris", R.drawable.cheese, R.raw.sleep)
        hashMap["yogurt"] = Triple("jogurtas", R.drawable.yogurt, R.raw.sleep)
        hashMap["salt"] = Triple("druska", R.drawable.salt, R.raw.sleep)
        hashMap["butter"] = Triple("sviestas", R.drawable.butter, R.raw.sleep)
        hashMap["Strawberry"] = Triple("Braškė", R.drawable.strawberry, R.raw.sleep)
        hashMap["Orange"] = Triple("apelsinas", R.drawable.orange, R.raw.sleep)
        hashMap["lime"] = Triple("kalkės", R.drawable.lime, R.raw.sleep)
        hashMap["lemon"] = Triple("citrina", R.drawable.lemon, R.raw.sleep)
        hashMap["mustard"] = Triple("garstyčios", R.drawable.mustard, R.raw.sleep)
        hashMap["avocado"] = Triple("avokadas", R.drawable.avocado, R.raw.sleep)
        hashMap["Onion"] = Triple("svogūnas", R.drawable.onion, R.raw.sleep)
        hashMap["mushroom"] = Triple("grybas", R.drawable.mushrooms, R.raw.sleep)
        hashMap["cherry"] = Triple("vyšnia", R.drawable.cherry, R.raw.sleep)
        hashMap["cauliflower"] = Triple("žiediniai kopūstai", R.drawable.cauliflower, R.raw.sleep)
        hashMap["salami"] = Triple("saliamis", R.drawable.salami, R.raw.sleep)
        hashMap["juice"] = Triple("sultys", R.drawable.juices, R.raw.sleep)
        hashMap["Pasta"] = Triple("Makaronai", R.drawable.pasta, R.raw.sleep)
        hashMap["Chicken"] = Triple("vištiena", R.drawable.chicken, R.raw.sleep)
        hashMap["Sauce"] = Triple("padažas", R.drawable.sauces, R.raw.sleep)
        hashMap["grapes"] = Triple("vynuoges", R.drawable.grape, R.raw.sleep)
        hashMap["pork"] = Triple("kiauliena", R.drawable.pork, R.raw.sleep)
        hashMap["rice"] = Triple("ryžiai", R.drawable.rice, R.raw.sleep)
        hashMap["spaghetti"] = Triple("spagečiai", R.drawable.spaghetti, R.raw.sleep)
        hashMap["broccoli"] = Triple("brokoliai", R.drawable.broccoli, R.raw.sleep)
        hashMap["milk"] = Triple("pienas", R.drawable.milk, R.raw.sleep)
        hashMap["wine"] = Triple("vynas", R.drawable.wine, R.raw.sleep)
        hashMap["meat"] = Triple("mėsa", R.drawable.meat, R.raw.sleep)



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
                saveProgress(progress) // Save progress

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

    private fun saveProgress(progress: Int) {
        val sharedPreferences = requireActivity().getSharedPreferences(sharedPrefFile, AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("progress", progress)
        editor.apply()
    }

    private fun getSavedProgress(): Int {
        val sharedPreferences = requireActivity().getSharedPreferences(sharedPrefFile, AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences.getInt("progress", 0)
    }
}