package com.example.visuallithuanian.ui.activities.fragments
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlowersFragment : Fragment() {

    lateinit var viewModel: BottomNavigationViewModel

    lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_flowers, container, false)

        bottomNavigationView = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity())[BottomNavigationViewModel::class.java]
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
            findNavController().navigate(R.id.action_flowersFragment_to_flashCards)
        }

        val exampleList = generateFlowersList()

        // settingup ImageAdapter
        val adapter = ImageAdapter(exampleList)
        recyclerView?.adapter = adapter

        return view
    }

    private fun generateFlowersList(): List<ImageInfo> {

        return listOf(
            ImageInfo(
                R.drawable.rose,"Rose","Rožė",emptyList(), R.drawable.mic,
                R.raw.potato,
                "Rose is a beautiful flower","Rožė yra graži gėlė"),
            ImageInfo(
                R.drawable.daffodil,"Daffodil","Narcizai",emptyList(),
                R.drawable.mic,
                R.raw.cabbage,
                "The daffodil blooms brightly in the spring","Narcizas ryškiai žydi pavasarį."),
            ImageInfo(
                R.drawable.sunflower,"Sunflower","Saulėgrąžos",emptyList(),
                R.drawable.mic,
                R.raw.zuccini,
                "The sunflower always turns towards the sun.","Saulėgrąža visada atsisuka į saulę"),
            ImageInfo(
                R.drawable.peony,"Peony","bijūnas",emptyList(),
                R.drawable.mic,
                R.raw.carrot,
                "The peony is blooming in the garden.","Bijūnas žydi sode"),
            ImageInfo(
                R.drawable.tulips,"Tulip","Tulpė",emptyList(),
                R.drawable.mic,
                R.raw.beet,
                "The tulip petals are vibrant and colorful.","Tulpės žiedlapiai yra ryškūs ir spalvoti"),
            ImageInfo(
                R.drawable.daisies,"Daisy","ramunė",emptyList(),
                R.drawable.mic,
                R.raw.onion,
                "The daisy's simplicity is its true beauty.","Ramunės paprastumas yra jos tikrasis grožis."),
        )

    }


}