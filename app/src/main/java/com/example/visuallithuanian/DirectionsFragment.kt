package com.example.visuallithuanian

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
import com.example.visuallithuanian.adapter.ImageAdapter
import com.example.visuallithuanian.data.ImageInfo
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class DirectionsFragment : Fragment() {

     lateinit var viewModel: BottomNavigationViewModel
     lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_directions, container, false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity()).get(BottomNavigationViewModel::class.java)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewAnimals)

        // Added a functionality where the bottomnavigation view will get invisible while scrolling and
        // appear after scrolling is stopped
        // recyclerView?.addOnScrollListener(BottomNavigationScrollListener(viewModel))
        // setting up recyclerview

        recyclerView?.layoutManager = LinearLayoutManager(context)

        val back_icon = view.findViewById<ImageView>(R.id.back_icon)

        // setting up listener
        back_icon.setOnClickListener {
            activity?.onBackPressed()
        }

        //setting up FloatingActionButton
        val floatingButton = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)

        floatingButton.setOnClickListener {
            findNavController().navigate(R.id.action_directionsFragment_to_flashCards)
        }


        val exampleList = generateDirectionsList()

        // settingup ImageAdapter
        val adapter = ImageAdapter(exampleList)
        recyclerView?.adapter = adapter
        return view
    }

    private fun generateDirectionsList(): List<ImageInfo> {

        return listOf(
            ImageInfo(R.drawable.north,"North","Šiaurės",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(R.drawable.south,"South","Pietų",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(R.drawable.west,"West","Vakarų",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(R.drawable.east,"East","Rytų",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(R.drawable.northwest,"North West","Šiaurės vakarai",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(R.drawable.northeast,"North East","Šiaurės rytai",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(R.drawable.southwest,"South West","Pietvakariai",listOf(R.drawable.purp), R.drawable.mic,
                "",""),
            ImageInfo(R.drawable.southeast,"South East","Pietryčiai",listOf(R.drawable.purp), R.drawable.mic,
                "",""),



        )

    }


}