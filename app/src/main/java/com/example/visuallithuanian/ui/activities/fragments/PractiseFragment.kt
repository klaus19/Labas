package com.example.visuallithuanian.ui.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.PractiseAdapter
import com.example.visuallithuanian.databinding.FragmentPractiseBinding
import dagger.hilt.android.AndroidEntryPoint
class PractiseFragment : Fragment() {
     lateinit var binding: FragmentPractiseBinding
    lateinit var recyclerView: RecyclerView
    private val imageNamesMap= HashMap<Int,String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentPractiseBinding.inflate(layoutInflater,container,false)

        binding.backIcon.setOnClickListener {
            findNavController().navigate(R.id.action_practiseFragment_to_userFragment)
        }

        populateImages()

       binding.recyclerViewPractise.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
       binding.recyclerViewPractise.adapter = PractiseAdapter(imageNamesMap)


        return binding.root

    }

    private fun populateImages() {
        // Add image resources to a list
        val imageResources = listOf(
            R.drawable.africa,
            R.drawable.asia,
            R.drawable.europe,
            R.drawable.antartica,
            R.drawable.australia
            // Add more image resources as needed
        )

        // Add image names to a list in random order
        val imageNames = listOf(
            "Africa",
            "Asia",
            "Europe",
            "Antarctica",
            "Australia"
            // Add more image names corresponding to the resources
        ).shuffled()

        // Use the shuffled names to assign each image resource ID a corresponding name in the imageNamesMap
        for (i in imageResources.indices) {
            imageNamesMap[imageResources[i]] = imageNames[i]
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            android.R.id.home->{
                requireActivity().onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Handle card clicks for both card views
    fun onCardClick(view: View,position: Int) {
        // Check the clicked view's ID to identify which cardView was clicked
        when (view.id) {
            R.id.cardImagePractise -> {
                Toast.makeText(requireContext(),"Hello Card Image",Toast.LENGTH_SHORT).show()
            }
            R.id.cardTextPractise -> {
                Toast.makeText(requireContext(),"Hello Card Text",Toast.LENGTH_SHORT).show()
            }
        }
    }


}