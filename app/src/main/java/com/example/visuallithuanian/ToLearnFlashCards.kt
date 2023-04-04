package com.example.visuallithuanian

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.visuallithuanian.adapter.FlashcardpaiAdapter
import com.example.visuallithuanian.databinding.FragmentToLearnFlashCardsBinding
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.WordViewModelFactory


class ToLearnFlashCards : Fragment() {

    private lateinit var binding:FragmentToLearnFlashCardsBinding
    val cardViewmodel:  FlashCardViewmodel by viewModels{
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentToLearnFlashCardsBinding.inflate(layoutInflater,container,false)

        binding.backIcon.setOnClickListener {
           findNavController().navigate(R.id.action_toLearnFlashCards_to_flashCards)
        }

        val adapter = FlashcardpaiAdapter{cardPair->
            cardViewmodel.deleteCards(cardPair)
        }
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

        //Observe  the data changes for the items added
        cardViewmodel.allWords.observe(requireActivity()) { cardPairs ->

            for (cardpair in cardPairs) {
                adapter.submitList(cardPairs)
            }

        }


        return binding.root
    }

}