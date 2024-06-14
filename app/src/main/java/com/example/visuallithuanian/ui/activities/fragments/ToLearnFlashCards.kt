package com.example.visuallithuanian.ui.activities.fragments



import OverlappingLayoutManager
import ToLearnAdapter
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
import com.example.visuallithuanian.R
import com.example.visuallithuanian.constants.ImageStore
import com.example.visuallithuanian.databinding.FragmentToLearnFlashCardsBinding
import com.example.visuallithuanian.model.PreferencesHelper
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


class ToLearnFlashCards : Fragment() {

    private lateinit var binding: FragmentToLearnFlashCardsBinding
    private lateinit var layoutManager: OverlappingLayoutManager
    private lateinit var preferencesHelper: PreferencesHelper

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

        //Taking the bOTTOMNavigation view instance from Activity into Fragment
        bottomNav = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        bottomNav.visibility = View.VISIBLE

        binding.backIcon.setOnClickListener {
            findNavController().navigate(R.id.action_toLearnFlashCards_to_flashCards)

        }

        binding.recyclerview.layoutManager = OverlappingLayoutManager(requireContext())
        layoutManager = OverlappingLayoutManager(requireContext())



        val adapter = ToLearnAdapter { cardPair ->
            cardViewmodel.deleteCards(cardPair)

        }
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.adapter = adapter
        binding.recyclerview.itemAnimator = null

        //Swipe Gesture

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.RIGHT
        ) {

            private val SWIPE_FACTOR = 0f
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

                // Apply rotation transformation to the card view
                itemView.pivotX = itemView.width.toFloat()
                itemView.pivotY = itemView.height.toFloat() / 2
                //   itemView.rotation = rotation
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
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
                // Handle swipe left to delete the card

                cardViewmodel.deleteCards(cardPair)

            }
        })
        itemTouchHelper.attachToRecyclerView(binding.recyclerview)

        val itemTouchHelper1 = ItemTouchHelper(object :ItemTouchHelper.SimpleCallback(
            0,ItemTouchHelper.LEFT
        ){
            val SWIPEFACTOR = 0f
            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
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

                ImageStore.addImageResource(cardPair.imageSrc,cardPair.front,cardPair.back,cardPair.voiceclip)
                cardViewmodel.deleteCards(cardPair)
            }
        })
        itemTouchHelper1.attachToRecyclerView(binding.recyclerview)




        //Observe  the data changes for the items added
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