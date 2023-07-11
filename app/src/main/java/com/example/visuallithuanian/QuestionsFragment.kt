package com.example.visuallithuanian

import android.animation.*
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
import com.example.visuallithuanian.databinding.FragmentQuestionsBinding
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.ToLearnViewModel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class QuestionsFragment : Fragment() {
    lateinit var binding:FragmentQuestionsBinding
    lateinit var viewModel: BottomNavigationViewModel

    lateinit var bottomNavigationView: BottomNavigationView
    private val counterViewModel: ToLearnViewModel by viewModels()

    private val flashCardList = listOf<FlashcardPair>(
        FlashcardPair("What", "Kas", R.drawable.what),
        FlashcardPair("When", "Kai", R.drawable.whennn),
        FlashcardPair("Where", "Kur", R.drawable.whereee),
        FlashcardPair("Who", "Kas", R.drawable.who),
        FlashcardPair("Whom", "Kam", R.drawable.whom),
        FlashcardPair("Why", "Kodėl", R.drawable.why),
        FlashcardPair("How", "Kaip", R.drawable.how),
        FlashcardPair("Which", "Kuris/kuri", R.drawable.which),
        FlashcardPair("Whose", "Kieno", R.drawable.whose),
        FlashcardPair("I", "aš", R.drawable.ii),
        FlashcardPair("you (singular)", "tu/jūs (informal/formal)", R.drawable.you),
        FlashcardPair("he", "jis", R.drawable.he),
        FlashcardPair("she", "ji", R.drawable.she),
        FlashcardPair("we", "mes", R.drawable.we),
        FlashcardPair("you (plural)", "jūs", R.drawable.you),
        FlashcardPair("they", "jie", R.drawable.they)
    )
    var isFront=true
    private val totalPairs =flashCardList.size // change the value to the actual number of entries in your hashMap

    // declaring viewmodel
    private val cardViewModel: FlashCardViewmodel by viewModels {
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }
    private var currentPairIndex =0
    private lateinit var currentPair:FlashcardPair

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionsBinding.inflate(inflater, container, false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity())[BottomNavigationViewModel::class.java]


        bottomNavigationView.visibility = View.GONE

        // setting up listener for back Icon
        binding.backIcon?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_questionsFragment_to_flashCards)
        }

        //changing color of progress bar progress
        binding.progressHorizontal.progressTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext()
            ,R.color.blue_card))

        //changing color of background color of progress bar
        binding.progressHorizontal.progressBackgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),
            R.color.silver))



        counterViewModel.counter.observe(requireActivity()){count->
            binding.textCounter.text = count.toString()
        }
        val front_animation = AnimatorInflater.loadAnimator(context, R.anim.front_animator) as AnimatorSet
        val back_animation = AnimatorInflater.loadAnimator(context,R.anim.back_animator)as AnimatorSet

        currentPair = flashCardList[currentPairIndex]
        binding.textCardFront.text = currentPair.front
        binding.textCardBack.text = currentPair.back
        binding.imagecardsHelper.setImageResource(currentPair.imageSrc)

        // onclick listener on the image
        binding.imageFlashCard.setOnClickListener {
            val clickedColor = ContextCompat.getColor(requireContext(), R.color.white)
            binding.imageFlashCard.setBackgroundColor(clickedColor)

            counterViewModel.incrementCounter()
            // increment currentPairIndex and get the next pair
            currentPairIndex++
            if (currentPairIndex >= flashCardList.size) {
                // if we have reached the end of the hashmap, start again from the beginning
                currentPairIndex = 0
            }
            currentPair = flashCardList[currentPairIndex]



            val pair = FlashcardPair(currentPair.front, currentPair.back,currentPair.imageSrc)
            cardViewModel.insertCards(pair)
            //Toast.makeText(requireContext(),"saved data", Toast.LENGTH_SHORT).show()
            Log.d("Main","$pair")

            // update the UI with the new pair
            binding.textCardFront.text = currentPair.front
            binding.textCardBack.text =currentPair.back
            binding.imagecardsHelper.setImageResource(currentPair.imageSrc)



        }

        binding.cardLearning.setOnClickListener {

            findNavController().navigate(R.id.action_questionsFragment_to_toLearnFlashCards)

        }

        //onclick listener for the Flip button
        with(binding) {
            btnFlip.setOnClickListener {
                val progress = ((currentPairIndex + 1) * 100) / totalPairs
                binding.progressHorizontal.progress = progress

                val originalColor = ContextCompat.getColor(requireContext(), R.color.pink)
                binding.imageFlashCard.setBackgroundColor(originalColor)

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
                    imageFlashCard.visibility = View.GONE
                    cardViewQuestions.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.card_purple))

                } else {
                    currentPairIndex = (currentPairIndex + 1) % flashCardList.size
                    textCardFront.visibility = View.VISIBLE
                    textCardBack.visibility = View.GONE
                    imageFlashCard.visibility = View.VISIBLE
                    cardViewQuestions.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.pink))
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
                currentPair = flashCardList[currentPairIndex]
                binding.textCardFront.text = currentPair.front
                binding.textCardBack.text = currentPair.back
                binding.imagecardsHelper.setImageResource(currentPair.imageSrc)
            }
        }

        binding.cardLearning.setOnClickListener {
            findNavController().navigate(R.id.action_questionsFragment_to_toLearnFlashCards)
        }

        return binding.root
    }
}