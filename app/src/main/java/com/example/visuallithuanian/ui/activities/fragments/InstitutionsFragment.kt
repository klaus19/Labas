package com.example.visuallithuanian.ui.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.ImageAdapter
import com.example.visuallithuanian.data.ImageInfo
import com.example.visuallithuanian.databinding.FragmentInstitutionsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InstitutionsFragment : Fragment() {

    private var _binding:FragmentInstitutionsBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentInstitutionsBinding.inflate(inflater,container,false)

        binding.recyclerViewAnimals.layoutManager = LinearLayoutManager(context)


        // setting up listener for back Icon
        binding.backIcon?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_institutionsFragment_to_flashCards)
        }

        val exampleList = generateInstitutionsList()

        // settingup ImageAdapter
        val adapter = ImageAdapter(exampleList)
        binding.recyclerViewAnimals.adapter = adapter

        return binding.root
    }

    private fun generateInstitutionsList(): List<ImageInfo> {

        return listOf(
            ImageInfo(
                R.drawable.museum,"Museum","Muziejus",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "",""),
            ImageInfo(
                R.drawable.hospital,"Hospital","Ligoninė",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "",""),
            ImageInfo(
                R.drawable.zoo,"Zoo","Zoologijos sodas",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "",""),
            ImageInfo(
                R.drawable.park,"Park","Parkas",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "",""),
            ImageInfo(
                R.drawable.school,"School","Mokykla",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "",""),
            ImageInfo(
                R.drawable.university,"University","Universitetas",listOf(R.drawable.purp),
                R.drawable.mic,
                R.raw.potato,
                "",""),
            ImageInfo(
                R.drawable.church,"Church","Bažnyčia",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "",""),
            ImageInfo(
                R.drawable.bank,"Bank","Bankas",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "",""),
            ImageInfo(
                R.drawable.supermarket,"Supermarket","prekybos centras",listOf(R.drawable.purp),
                R.drawable.mic,
                R.raw.potato,
                "",""),
            ImageInfo(
                R.drawable.resort,"Resort","Kurortas",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "",""),
            ImageInfo(
                R.drawable.restaurant,"Restaurant","Restoranas",listOf(R.drawable.purp),
                R.drawable.mic,
                R.raw.potato,
                "",""),
            ImageInfo(
                R.drawable.aquarium,"Aquarium","Akvariumas",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "",""),
            ImageInfo(
                R.drawable.gymnasium,"Gymnasium","Gimnazijos",listOf(R.drawable.purp),
                R.drawable.mic,
                R.raw.potato,
                "",""),
            ImageInfo(
                R.drawable.pub,"Pub","Pubas",listOf(R.drawable.purp), R.drawable.mic,
                R.raw.potato,
                "",""),



        )
    }


}