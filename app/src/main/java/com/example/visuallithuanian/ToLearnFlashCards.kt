package com.example.visuallithuanian

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.adapter.FlashcardpaiAdapter
import com.example.visuallithuanian.custom.OverlappingLayoutManager
import com.example.visuallithuanian.databinding.FragmentToLearnFlashCardsBinding
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


class ToLearnFlashCards : Fragment() {

    private lateinit var binding:FragmentToLearnFlashCardsBinding
    lateinit var bottomNav:BottomNavigationView
    val cardViewmodel:  FlashCardViewmodel by viewModels{
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentToLearnFlashCardsBinding.inflate(layoutInflater,container,false)

        //Taking the bOTTOMNavigation view instance from Activity into Fragment
        bottomNav = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        bottomNav.visibility = View.VISIBLE

        binding.backIcon.setOnClickListener {
           findNavController().navigate(R.id.action_toLearnFlashCards_to_flashCards)
        }

        val adapter = FlashcardpaiAdapter{cardPair->
            cardViewmodel.deleteCards(cardPair)
        }
        binding.recyclerview.adapter = adapter

        val itemTouchHelper = ItemTouchHelper(object :ItemTouchHelper.SimpleCallback(
            0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
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
                cardViewmodel.deleteCards(cardPair)
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.recyclerview)

        binding.recyclerview.layoutManager = OverlappingLayoutManager()
      //  binding.recyclerview.rotation=10f

        //Observe  the data changes for the items added
        cardViewmodel.allWords.observe(requireActivity()) { cardPairs ->

            for (cardpair in cardPairs) {
                adapter.submitList(cardPairs)
            }

        }


        return binding.root
    }

}