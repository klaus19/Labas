package com.example.visuallithuanian

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.visuallithuanian.databinding.FragmentQuestionsBinding
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class QuestionsFragment : Fragment() {

    private var _binding:FragmentQuestionsBinding?=null
    private val binding get() = _binding!!

    lateinit var viewModel: BottomNavigationViewModel

    lateinit var bottomNavigationView: BottomNavigationView

    private val hashMap = HashMap<String,String>()
    private var progress = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionsBinding.inflate(inflater, container, false)

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

        hashMap["key1"] = "value1"
        hashMap["key2"] = "value2"
        hashMap["key3"] = "value3"

        // button on click listener
        binding.btnFlip.setOnClickListener {
            val currentText = binding.textCard.text.toString()
            if (hashMap.containsKey(currentText)){
                binding.textCard.text = hashMap[currentText]
                flipAnimation()
            }else{
                binding.textCard.text = hashMap.keys.first()
            }

        }
        return binding.root
    }

    private fun flipAnimation() {
        val rotationY = PropertyValuesHolder.ofFloat(View.ROTATION_Y, 0f, 180f)
        val flipAnimator = ObjectAnimator.ofPropertyValuesHolder(binding.cardViewQuestions, rotationY)
        flipAnimator.duration = 500 // set the duration of the animation
        flipAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                val rotation = PropertyValuesHolder.ofFloat(View.ROTATION, 180f, 0f)
                val textRotateAnimator = ObjectAnimator.ofPropertyValuesHolder(binding.textCard, rotation)
                textRotateAnimator.duration = 0
                textRotateAnimator.start()

                // Update progress value
                progress++
                binding.progressHorizontal.progress = progress
            }
        })
        flipAnimator.start() // start the animation
    }


}