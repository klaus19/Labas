package com.example.visuallithuanian.ui.activities.fragments

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Canvas
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private lateinit var preferencesHelper: PreferencesHelper
    private lateinit var sharedPreferences: SharedPreferences
    private var learnedCounter = 0
    private var toLearnCounter = 0

    private lateinit var bottomNav: BottomNavigationView
    private val cardViewModel: FlashCardViewmodel by viewModels {
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentToLearnFlashCardsBinding.inflate(inflater, container, false)
        initView()
        setupRecyclerView()
        observeViewModel()
        return binding.root
    }

    private fun initView() {
        preferencesHelper = PreferencesHelper(requireContext())
        sharedPreferences = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

        // Load counters from SharedPreferences
        learnedCounter = sharedPreferences.getInt("counterLearned", 0)
        toLearnCounter = sharedPreferences.getInt("counterToLearn", 0)

        bottomNav = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        bottomNav.visibility = View.VISIBLE

        binding.backIcon.setOnClickListener {
            findNavController().navigate(R.id.action_toLearnFlashCards_to_flashCards)
        }

        Glide.with(this).asGif().load(R.drawable.happyface).into(binding.gifImageView)
    }

    private fun setupRecyclerView() {
        val layoutManager = OverlappingLayoutManager(requireContext())
        binding.recyclerview.layoutManager = layoutManager

        val adapter = ToLearnAdapter { cardPair ->
            cardViewModel.deleteCards(cardPair)
        }

        binding.recyclerview.adapter = adapter
        binding.recyclerview.itemAnimator = null

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                handleSwipe(viewHolder.adapterPosition, direction, adapter)
            }

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
        })
        itemTouchHelper.attachToRecyclerView(binding.recyclerview)
    }

    private fun handleSwipe(position: Int, direction: Int, adapter: ToLearnAdapter) {
        val cardPair = adapter.currentList[position]

        when (direction) {
            ItemTouchHelper.RIGHT -> {
                ImageStore.addImageResource(cardPair.imageSrc,cardPair.front,cardPair.back,cardPair.voiceclip)
                ImageStore.saveToPreferences(requireContext())
                preferencesHelper.addSavedItemCard(cardPair)
                cardViewModel.deleteCards(cardPair)
                learnedCounter++
                saveCounter("counterLearned", learnedCounter)
            }
            ItemTouchHelper.LEFT -> {
                ImageStore.addImageResource(cardPair.imageSrc, cardPair.front, cardPair.back, cardPair.voiceclip)
                ImageStore.saveToPreferences(requireContext())
                adapter.moveItemToEnd(position)
                preferencesHelper.addSavedItem(position.toString())
                toLearnCounter++
                saveCounter("counterToLearn", toLearnCounter)
            }
        }
    }

    private fun saveCounter(key: String, value: Int) {
        sharedPreferences.edit().apply {
            putInt(key, value)
            apply()
        }
    }

    private fun observeViewModel() {
        cardViewModel.allWords.observe(viewLifecycleOwner) { cardPairs ->
            (binding.recyclerview.adapter as? ToLearnAdapter)?.submitList(cardPairs)
            binding.emptyImage.visibility = if (cardPairs.isEmpty()) View.VISIBLE else View.GONE
            binding.emptyCardText.visibility = if (cardPairs.isEmpty()) View.VISIBLE else View.GONE
        }
    }
}
