package com.example.visuallithuanian.ui.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.ImageAdapter
import com.example.visuallithuanian.data.ImageInfo
import com.example.visuallithuanian.databinding.FragmentSeasonsBinding
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SeasonsFragment : Fragment() {

    //View Binding
    private var _binding:FragmentSeasonsBinding?=null
    private val binding get() = _binding!!

    lateinit var viewModel: BottomNavigationViewModel

    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentSeasonsBinding.inflate(inflater, container, false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity())[BottomNavigationViewModel::class.java]


        // Added a functionality where the bottomnavigation view will get invisible while scrolling and
        // appear after scrolling is stopped
        // recyclerView?.addOnScrollListener(BottomNavigationScrollListener(viewModel))
        // setting up recyclerview

        binding.recyclerViewAnimals.layoutManager = LinearLayoutManager(context)


        // setting up listener for back Icon
        binding.backIcon?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_seasonsFragment_to_flashCards)
        }

        val exampleList = generateSeasonsList()

        // settingup ImageAdapter
        val adapter = ImageAdapter(exampleList)
        binding.recyclerViewAnimals.adapter = adapter
        return binding.root

    }

    private fun generateSeasonsList(): List<ImageInfo> {

        return listOf(
            ImageInfo(
                R.drawable.summer,"Summer","Vasara",emptyList(), R.drawable.mic,
                R.raw.summer,
                "Summer is warm and sunny",
                "Vasara yra šilta ir saulėta"),
            ImageInfo(
                R.drawable.autumn,"Autumn","Ruduo",emptyList(), R.drawable.mic,
                R.raw.autumn,
                "Autumn leaves turn colorful",
                "Rudens lapai tampa spalvingi"),
            ImageInfo(
                R.drawable.winter,"Winter","Žiema",emptyList(), R.drawable.mic,
                R.raw.winter,
                "Winter is cold and snowy",
                "Žiema yra šalta ir snieginga"),
            ImageInfo(
                R.drawable.spring,"Spring","Pavasaris",emptyList(), R.drawable.mic,
                R.raw.spring,
                "Spring brings new flowers.",
                "Pavasaris atneša naujų gėlių."),
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}