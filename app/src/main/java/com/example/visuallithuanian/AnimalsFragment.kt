package com.example.visuallithuanian


import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
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
        // setting up Toolbar and it's icon
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        val back_icon = view.findViewById<ImageView>(R.id.back_icon)

        // setting up listener
        back_icon.setOnClickListener {
            activity?.onBackPressed()
        }

        // setting up recyclerview
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewAnimals)
        recyclerView.layoutManager = LinearLayoutManager(context)


        val exampleList = generateExampleList()

        // settingup ImageAdapter
        val adapter = ImageAdapter(exampleList)
        recyclerView.adapter = adapter

        return view
    }

    //Adding a list of example of elements, animals for example
    // Created a list aato store the images and it's other properties
    private fun generateExampleList(): List<ImageInfo> {
        return listOf(
            ImageInfo(R.drawable.fox,"Fox","Lapė"),
            ImageInfo(R.drawable.wolf,"Wolf","Vilkas"),
            ImageInfo(R.drawable.lion,"Lion","Liūtas"),
            ImageInfo(R.drawable.tiger,"Tiger","Tigras"),
            ImageInfo(R.drawable.squir,"Squirrel","Voverė")
        )

    }











}