package com.example.visuallithuanian.ui.activities.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.ImageAdapter
import com.example.visuallithuanian.data.ImageInfo
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class TransportFragment : Fragment() {

    lateinit var viewModel: BottomNavigationViewModel
    lateinit var bottomNavigationView: BottomNavigationView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_transport, container, false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity()).get(BottomNavigationViewModel::class.java)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewAnimals)
        val fb1 = view?.findViewById<FloatingActionButton>(R.id.floatingActionButton)

        recyclerView?.layoutManager = LinearLayoutManager(context)

        val back_icon = view?.findViewById<ImageView>(R.id.back_icon)
        back_icon?.setOnClickListener {
            activity?.onBackPressed()
        }

        val transportList = generateTransportList()
        // settingup ImageAdapter
        val adapter = ImageAdapter(transportList)
        recyclerView?.adapter = adapter

        fb1?.setOnClickListener {
            findNavController().navigate(R.id.action_transportFragment_to_flashCards)
        }

        return view
    }

    private fun generateTransportList(): List<ImageInfo> {

        return listOf(
            ImageInfo(
                R.drawable.bus,"Bus","Autobusas",emptyList(), R.drawable.mic,
                R.raw.bus,
                "The bus is late.",
                "Autobusas vėluoja"),
            ImageInfo(
                R.drawable.car,"Car","Automobilis",emptyList(), R.drawable.mic,
                R.raw.car,
                "The car is fast",
                "Automobilis yra greitas"),
            ImageInfo(
                R.drawable.rail,"Train","Traukinys",emptyList(), R.drawable.mic,
                R.raw.train,
                "The train arrives soon","Traukinys atvyksta netrukus"),
            ImageInfo(
                R.drawable.bicycle,"Bicycle","Dviratis",emptyList(), R.drawable.mic,
                R.raw.bicycle,
                "I ride a bicycle.","Aš važiuoju dviračiu"),
            ImageInfo(
                R.drawable.motorbike,"Motorcycle","Motociklas",emptyList(),
                R.drawable.mic,
                R.raw.motocycle,
                "The motorcycle is loud.","Motociklas yra garsus."),
            ImageInfo(
                R.drawable.boat,"Boat","Valtis",emptyList(), R.drawable.mic,
                R.raw.boat,
                "The boat is on the water.","Valtis yra ant vandens"),
            ImageInfo(
                R.drawable.airplane,"Airplane","Lėktuvas",emptyList(), R.drawable.mic,
                R.raw.airplane,
                "The airplane is flying","Lėktuvas skrenda"),
            ImageInfo(
                R.drawable.helicopter,"Helicopter","Sraigtasparnis",emptyList(),
                R.drawable.mic,
                R.raw.helicoptor,
                "The helicopter is in the sky","Sraigtasparnis yra danguje"),
            ImageInfo(
                R.drawable.truck,"Truck","Sunkvežimis",emptyList(), R.drawable.mic,
                R.raw.truck,
                "The truck is carrying cargo","Vilkikas veža krovin"),
            ImageInfo(
                R.drawable.taxi,"Taxi","Taksi",emptyList(), R.drawable.mic,
                R.raw.taxi,
                "The taxi is waiting outside.","Taksas laukia lauke"),
        )

    }


}