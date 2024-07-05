package com.example.visuallithuanian.ui.activities.fragments

import android.graphics.Canvas
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.ToLearnAdapter
import com.example.visuallithuanian.constants.ImageStore
import com.example.visuallithuanian.custom.OverlappingLayoutManager
import com.example.visuallithuanian.databinding.FragmentToLearnFlashCardsBinding
import com.example.visuallithuanian.model.PreferencesHelper
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.ToLearnViewModel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class ToLearnFlashCards : Fragment() {

    private lateinit var binding: FragmentToLearnFlashCardsBinding
    private lateinit var preferencesHelper: PreferencesHelper
    private val counterViewModel: ToLearnViewModel by viewModels()

    private val sharedPrefFile = "com.example.visuallithuanian.PREFERENCE_FILE_KEY"
    private val counterKey = "counter"
    private val learnedCounterKey = "learned_counter"

    lateinit var bottomNav: BottomNavigationView
    val cardViewmodel: FlashCardViewmodel by viewModels {
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentToLearnFlashCardsBinding.inflate(layoutInflater, container, false)

        preferencesHelper = PreferencesHelper(requireContext())



        // Taking the BottomNavigationView instance from Activity into Fragment
        bottomNav = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        bottomNav.visibility = View.VISIBLE

        binding.backIcon.setOnClickListener {
            findNavController().navigate(R.id.action_toLearnFlashCards_to_flashCards)
        }

        val layoutManager = OverlappingLayoutManager(requireContext())
        binding.recyclerview.layoutManager = layoutManager

        val adapter = ToLearnAdapter { cardPair ->
            cardViewmodel.deleteCards(cardPair)
        }
        binding.recyclerview.adapter = adapter
        binding.recyclerview.itemAnimator = null


        // Swipe Gesture
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
                if (dX > 0) {
                    itemView.pivotX = itemView.width.toFloat()
                    itemView.pivotY = itemView.height.toFloat() / 2
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val cardPair = adapter.currentList[position]

                when (direction) {
                    ItemTouchHelper.RIGHT -> {
                        cardViewmodel.deleteCards(cardPair)
                        preferencesHelper.addSavedItem(position.toString())
                        incrementCounterLearned()
                    }
                    ItemTouchHelper.LEFT -> {
                        ImageStore.addImageResource(cardPair.imageSrc, cardPair.front, cardPair.back, cardPair.voiceclip)
                        ImageStore.saveToPreferences(requireContext())
                        adapter.moveItemToEnd(position)
                        cardViewmodel.deleteCards(cardPair)
                        preferencesHelper.addSavedItem(position.toString())
                        counterViewModel.incrementCounter()
                        incrementCounter()
                    }
                }
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.recyclerview)


        // Observe data changes for the items added
        cardViewmodel.allWords.observe(viewLifecycleOwner) { cardPairs ->
            adapter.submitList(cardPairs)
            if (cardPairs.isEmpty()) {
                binding.emptyImage.visibility = View.VISIBLE
                binding.emptyCardText.visibility = View.VISIBLE
            } else {
                binding.emptyImage.visibility = View.GONE
                binding.emptyCardText.visibility = View.GONE
            }
        }

//
//        binding.cardLearning.setOnClickListener {
//            findNavController().navigate(R.id.action_toLearnFlashCards_to_practiseFragment)
//        }

        return binding.root
    }

    private fun incrementCounterLearned() {
        val sharedPreferences = requireContext().getSharedPreferences(sharedPrefFile, AppCompatActivity.MODE_PRIVATE)
        val currentCounter = sharedPreferences.getInt(learnedCounterKey, 0)
        val newCounter = currentCounter + 1
        with(sharedPreferences.edit()) {
            putInt(learnedCounterKey, newCounter)
            apply()
        }
    }

    private fun decrementLearnCounter() {
        val sharedPreferences = requireContext().getSharedPreferences(sharedPrefFile,AppCompatActivity.MODE_PRIVATE)
        val currentCounter = sharedPreferences.getInt(learnedCounterKey,0)
        val newCounter = currentCounter-1
        with(sharedPreferences.edit()){
            putInt(learnedCounterKey,newCounter).apply()
        }
    }

    private fun incrementCounter() {
        val sharedPreferences = requireContext().getSharedPreferences(sharedPrefFile, AppCompatActivity.MODE_PRIVATE)
        val currentCounter = sharedPreferences.getInt(counterKey, 0)
        val newCounter = currentCounter + 1
        with(sharedPreferences.edit()) {
            putInt(counterKey, newCounter)
            apply()
        }
    }


}