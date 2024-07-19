package com.example.visuallithuanian.ui.activities.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import com.bumptech.glide.Glide
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.ToLearnAdapter
import com.example.visuallithuanian.constants.ImageStore
import com.example.visuallithuanian.custom.OverlappingLayoutManager
import com.example.visuallithuanian.databinding.FragmentToLearnFlashCardsBinding
import com.example.visuallithuanian.model.PreferencesHelper
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class ToLearnFlashCards : Fragment() {

    private lateinit var binding: FragmentToLearnFlashCardsBinding
    private var learnedCounter = 0
    private var toLearnCounter = 0

    private lateinit var preferencesHelper:PreferencesHelper

    lateinit var bottomNav: BottomNavigationView
    val cardViewmodel: FlashCardViewmodel by viewModels {
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToLearnFlashCardsBinding.inflate(inflater, container, false)

        preferencesHelper = PreferencesHelper(requireContext())

        // Step1
        val sharedPreferences = requireActivity().getSharedPreferences("my_prefs",Context.MODE_PRIVATE)

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

        Glide.with(this).asGif().load(R.drawable.happyface).into(binding.gifImageView)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.itemAnimator = null

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
                        learnedCounter++
                        with(sharedPreferences.edit()){
                            putInt("counterLearned",learnedCounter)
                            apply()
                        }

                    }
                    ItemTouchHelper.LEFT -> {
                        ImageStore.addImageResource(cardPair.imageSrc, cardPair.front, cardPair.back, cardPair.voiceclip)
                        ImageStore.saveToPreferences(requireContext())
                        adapter.moveItemToEnd(position)
                        cardViewmodel.deleteCards(cardPair)
                        preferencesHelper.addSavedItem(position.toString())
                        toLearnCounter++
                        with(sharedPreferences.edit()){
                            putInt("counterToLearn",toLearnCounter)
                            apply()
                        }
                    }
                }
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.recyclerview)

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

        return binding.root
    }


}
