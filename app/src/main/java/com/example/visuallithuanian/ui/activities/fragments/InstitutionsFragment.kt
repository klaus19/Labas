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
                R.drawable.zoo,"Zoo","Zoo",emptyList(), R.drawable.mic,
                R.raw.potato,
                "The zoo is home to many exotic animals",
                "Zoo yra namai daugeliui egzotinių gyvūnų"),
            ImageInfo(
                R.drawable.park,"Park","Parkas",emptyList(), R.drawable.mic,
                R.raw.potato,
                "The park is full of green trees","Parke yra daug žalių medžių"),
            ImageInfo(
                R.drawable.school,"School","Mokykla",emptyList(), R.drawable.mic,
                R.raw.potato,
                "The school has many classrooms.","Mokykloje yra daug klasių"),
            ImageInfo(
                R.drawable.supermarket,"Supermarket","prekybos centras",emptyList(),
                R.drawable.mic,
                R.raw.potato,
                "The supermarket has a wide selection of products.","Prekybos centras turi platų prekių pasirinkimą."),
            ImageInfo(
                R.drawable.resort,"Resort","Kurortas",emptyList(), R.drawable.mic,
                R.raw.potato,
                "The resort offers beautiful views and relaxation",
                "Kurortas siūlo gražius vaizdus ir poilsį"),
            ImageInfo(
                R.drawable.aquarium,"Aquarium","Akvariumas",emptyList(), R.drawable.mic,
                R.raw.potato,
                "The aquarium has many colorful fish.","Akvariumas turi daug spalvingų žuvų."),
            ImageInfo(
                R.drawable.gymnasium,"Gymnasium","sporto salė",emptyList(),
                R.drawable.mic,
                R.raw.potato,
                "The gymnasium has exercise equipment",
                "Sporto salėje yra treniruoklių"),
            ImageInfo(
                R.drawable.pub,"Pub","Pubas",emptyList(), R.drawable.mic,
                R.raw.potato,
                "The pub is a great place to meet friends",
                "Pubas yra puiki vieta susitikti su draugais"),


            ImageInfo(
                R.drawable.restaurant,"Restaurant","Restoranas",emptyList(),
                R.drawable.mic,
                R.raw.restaurant,
                "The restaurant serves delicious meals.",
                "Restoranas pateikia skanius patiekalus."),
            ImageInfo(
                R.drawable.university,"University","Universitetas",emptyList(),
                R.drawable.mic,
                R.raw.university,
                "The university offers various courses.","Universitetas siūlo įvairius kursus"),
            ImageInfo(
                R.drawable.church,"Church","Bažnyčia",emptyList(), R.drawable.mic,
                R.raw.thechurch,
                "The church is quiet and peaceful.","Bažnyčia yra tylu ir ramų"),
            ImageInfo(
                R.drawable.bank,"Bank","Bankas",emptyList(), R.drawable.mic,
                R.raw.bank,
                "The bank is open from 9 to 5","Bankas dirba nuo 9 iki 17 valandos"),
            ImageInfo(
                R.drawable.museum,"Museum","Muziejus",emptyList(), R.drawable.mic,
                R.raw.museam,
                "The museum displays many interesting exhibits",
                "Muziejus rodo daug įdomių eksponatų."),
            ImageInfo(
                R.drawable.hospital,"Hospital","Ligoninė",emptyList(), R.drawable.mic,
                R.raw.hospitak,
                "The hospital provides excellent care for its patients",
                "Ligoniai gauna puikią priežiūrą ligoninėje"),

        )
    }


}