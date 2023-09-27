package com.example.visuallithuanian

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.res.ColorStateList
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
import com.example.visuallithuanian.databinding.FragmentDailyBasicBinding
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.ToLearnViewModel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class DailyBasic : Fragment() {
    lateinit var binding: FragmentDailyBasicBinding
    lateinit var viewModel: BottomNavigationViewModel

    lateinit var bottomNavigationView: BottomNavigationView
    private val counterViewModel: ToLearnViewModel by viewModels()

    private val hashMap = HashMap<String,Pair<String,Int>>()

    private var currentPairIndex =0
    private lateinit var currentPair:Map.Entry<String,Pair<String,Int>>

    var isFront=true
    private val totalPairs = 16 // change the value to the actual number of entries in your hashMap

    // declaring viewmodel
    private val cardViewModel: FlashCardViewmodel by viewModels {
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }


    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDailyBasicBinding.inflate(inflater,container,false)

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
        hashMap["sleep"] = Pair("miegoti", R.drawable.sleep)
        hashMap["to shop"] = Pair("apsipirkti",R.drawable.shopping)
        hashMap["to watch a movie"] = Pair("Žiūrėti filmą",R.drawable.cinemascreen)
        hashMap["I go shopping"] = Pair("aš einu apsipirkti", R.drawable.goshopping)
        hashMap["What is it?"] = Pair("Kas tai?",R.drawable.what1)
        hashMap["this is English newspaper"] = Pair("tai yra angliškas laikraštis",R.drawable.newspaper)
        hashMap["Japanese food"] = Pair("Japonų maistas", R.drawable.japanesefood)
        hashMap["hot coffee"] = Pair("karšta kava",R.drawable.hotcoffee)
        hashMap["I learn Lithuanian"] = Pair("Aš mokausi lietuvių kalbos",R.drawable.languages)
        hashMap["to eat"] = Pair("valgyti", R.drawable.eat)
        hashMap["I eat japanese food"] = Pair("Aš valgau japonišką maistą",R.drawable.eat)
        hashMap["She drinks hot coffee"] = Pair("Ji geria karštą kavą",R.drawable.coffee1)
        hashMap["Would you like some tea?"] = Pair("Ar norėtumete arbatos?",R.drawable.tea1)
        hashMap["Can you repeat it again?"] = Pair("Ar galite pakartoti dar kartą?", R.drawable.repeat)
        hashMap["Of course"] = Pair("Žinoma",R.drawable.ofcourse)
        hashMap["rice"] = Pair("ryžiai",R.drawable.rice)
        hashMap["soup"] = Pair("sriuba",R.drawable.soup1)
        hashMap["Bread"] = Pair("Duona", R.drawable.bread1)
        hashMap["water"] = Pair("vanduo",R.drawable.water1)
        hashMap["What are you reading?"] = Pair("Ką skaitote?",R.drawable.whatreading)
        hashMap["to cost"] = Pair("kainuoti",R.drawable.cost)
        hashMap["a cup of coffee"] = Pair("puodelis kavos",R.drawable.cupcoffee)
        hashMap["apple"] = Pair("obuolys", R.drawable.apple1)
        hashMap["a glass"] = Pair("stiklinė",R.drawable.glass1)
        hashMap["What do you buy?"] = Pair("Ką perkate?",R.drawable.buy11)
        hashMap["parents"] = Pair("tėvai",R.drawable.parents1)
        hashMap["classmate"] = Pair("klasiokas", R.drawable.classmate)
        hashMap["my friends"] = Pair("Mano draugai",R.drawable.friends1)
        hashMap["a little"] = Pair("šiek tiek",R.drawable.little)
        hashMap["Let's go"] = Pair("eikime!",R.drawable.letsgo)
        hashMap["more"] = Pair("daugiau",R.drawable.more)
        hashMap["key"] = Pair("raktas",R.drawable.key)
        hashMap["what are you doing?"] = Pair("ką darote?",R.drawable.todo)
        hashMap["hand"] = Pair("ranka",R.drawable.hand)
        hashMap["to stop"] = Pair("nustoti",R.drawable.stopsign)




        counterViewModel.counter.observe(requireActivity()){count->
            binding.textCounter.text = count.toString()
        }
        val front_animation = AnimatorInflater.loadAnimator(context, R.anim.front_animator) as AnimatorSet
        val back_animation = AnimatorInflater.loadAnimator(context,R.anim.back_animator)as AnimatorSet

        currentPair = hashMap.entries.elementAt(currentPairIndex)
        binding.textCardFront.text = currentPair.key
        binding.textCardBack.text = currentPair.value.first
        binding.imagecardsHelper.setImageResource(currentPair.value.second)

        // onclick listener on the image
        binding.imageFlashCard.setOnClickListener {
            binding.imageFlashCard.visibility = View.GONE
            binding.imageFlashCardSaveWhite.visibility = View.VISIBLE

            counterViewModel.incrementCounter()
            // increment currentPairIndex and get the next pair
            currentPairIndex++
            if (currentPairIndex >= hashMap.size) {
                // if we have reached the end of the hashmap, start again from the beginning
                currentPairIndex = 0
            }
            val front = binding.textCardFront.text.toString()
            val back = binding.textCardBack.text.toString()
            val imageHelper = currentPair.value.second

            val pair = FlashcardPair(front, back, imageHelper)
            cardViewModel.insertCards(pair)
            //Toast.makeText(requireContext(),"saved data", Toast.LENGTH_SHORT).show()
            Log.d("Main","$pair")
            currentPair = hashMap.entries.elementAt(currentPairIndex)

        }
        //On Event of clicking on the image
        binding.imageFlashCardSaveWhite.setOnClickListener {
            with(binding){
                imageFlashCardSaveWhite.visibility = View.GONE
                imageFlashCard.visibility = View.VISIBLE

                if (currentPairIndex >= 0 && currentPairIndex < hashMap.size) {
                    // Remove the item at the current index from your data structure (e.g., HashMap)
                    val removedPair = hashMap.entries.elementAt(currentPairIndex)
                    hashMap.remove(removedPair.key)

                    // Decrease the counter
                    counterViewModel.decrementCounter()
                    val front = binding.textCardFront.text.toString()
                    val back = binding.textCardBack.text.toString()
                    val imageHelper = currentPair.value.second

                    val pair = FlashcardPair(front, back, imageHelper)
                    cardViewModel.deleteCards(pair)
                    //Toast.makeText(requireContext(),"saved data", Toast.LENGTH_SHORT).show()
                    Log.d("Main","$pair")
                    currentPair = hashMap.entries.elementAt(currentPairIndex)

                }
            }
        }

        binding.cardLearning.setOnClickListener {

            findNavController().navigate(R.id.action_dailyBasic_to_toLearnFlashCards)

        }

        //onclick listener for the Flip button
        with(binding) {
            btnFlip.setOnClickListener {
                    imageFlashCardSaveWhite.visibility = View.GONE
                    imageFlashCard.visibility = View.VISIBLE


                val progress = ((currentPairIndex + 1) * 100) / totalPairs
                binding.progressHorizontal.progress = progress

               // val originalColor = ContextCompat.getColor(requireContext(), R.color.pink)
              //  binding.imageFlashCard.setBackgroundColor(originalColor)

                // initialize currentPairIndex to 0 if it hasn't been initialized yet
                if (currentPairIndex < 0) {
                    currentPairIndex = 0
                }
                if (isFront) {
                    front_animation.setTarget(textCardFront)
                    back_animation.setTarget(textCardBack)
                    front_animation.start()
                    back_animation.start()
                    isFront = false
                    textCardBack.visibility = View.VISIBLE
                    textCardFront.visibility = View.GONE
                    imageFlashCard.visibility = View.VISIBLE
                    cardViewQuestions.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green1))

                } else {
                    currentPairIndex = (currentPairIndex + 1) % hashMap.size
                    textCardFront.visibility = View.VISIBLE
                    textCardBack.visibility = View.GONE
                    imageFlashCard.visibility = View.VISIBLE
                    cardViewQuestions.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.orange1))
                    front_animation.setTarget(textCardBack)
                    back_animation.setTarget(textCardFront)
                    back_animation.start()
                    front_animation.start()
                    isFront = true
                }

                // add the following code to make the button shake
                val shake: ObjectAnimator = ObjectAnimator.ofFloat(binding.btnFlip, "translationX", -10f, 10f)
                shake.duration = 100
                shake.repeatCount = 5
                shake.repeatMode = ObjectAnimator.REVERSE
                shake.start()
                // retrieve the current pair from the hashMap
                currentPair = hashMap.entries.elementAt(currentPairIndex)
                binding.textCardFront.text = currentPair.key
                binding.textCardBack.text = currentPair.value.first
                binding.imagecardsHelper.setImageResource(currentPair.value.second!!)
            }
        }
        return binding.root

    }



}