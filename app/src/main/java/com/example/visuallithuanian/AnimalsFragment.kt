package com.example.visuallithuanian


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.adapter.ImageAdapter
import com.example.visuallithuanian.data.ImageInfo



class AnimalsFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_animals, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewAnimals)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val exampleList = generateExampleList()
        val adapter = ImageAdapter(exampleList)
        recyclerView.adapter = adapter

        return view
    }

    private fun generateExampleList(): List<ImageInfo> {
        return listOf(
            ImageInfo(R.drawable.fox,"Fox","fox"),
            ImageInfo(R.drawable.wolf,"Wolf","WW")
        )

    }











}