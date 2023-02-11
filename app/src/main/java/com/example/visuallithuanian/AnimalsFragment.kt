package com.example.visuallithuanian


import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.adapter.ImageAdapter
import com.example.visuallithuanian.base.BottomNavigationScrollListener
import com.example.visuallithuanian.data.ImageInfo
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*


class AnimalsFragment : Fragment() {

    private lateinit var viewModel: BottomNavigationViewModel
    private lateinit var bottomNav:BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_animals, container, false)

        bottomNav = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        viewModel = ViewModelProvider(requireActivity()).get(BottomNavigationViewModel::class.java)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewAnimals)

        viewModel.bottomNavigationVisibility.observe(viewLifecycleOwner, Observer { visibility ->
            bottomNav.visibility = if (visibility) View.VISIBLE else View.GONE
        })

         recyclerView?.addOnScrollListener(BottomNavigationScrollListener(viewModel))
        // setting up recyclerview

        recyclerView?.layoutManager = LinearLayoutManager(context)

        // Added a functionality where the bottomnavigation view will get invisible while scrolling and
        // appear after scrolling is stopped
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)


            }

        })

        // setting up Toolbar and it's icon
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        val back_icon = view.findViewById<ImageView>(R.id.back_icon)

        // setting up listener
        back_icon.setOnClickListener {
            activity?.onBackPressed()
        }


        //setting up FloatingActionButton
        val floatingButton = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)

        floatingButton.setOnClickListener {
            findNavController().navigate(R.id.action_animalsFragment_to_flashCards)
        }


        val exampleList = generateExampleList()

        // settingup ImageAdapter
        val adapter = ImageAdapter(exampleList)
        recyclerView?.adapter = adapter

        return view
    }

    //Adding a list of example of elements, animals for example
    // Created a list aato store the images and it's other properties
    private fun generateExampleList(): List<ImageInfo> {
        return listOf(
            ImageInfo(R.drawable.fox,"Red Fox","Raudona lapė",listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.wolf,"Wolf","Vilkas", listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.lion,"Lion","Liūtas", listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.tiger,"Tiger","Tigras", listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.squir,"Squirrel","Voverė", listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.moose,"Moose","Briedis", listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.deer,"Deer","elnias", listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.beaver,"Beaver","bebras", listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.bison,"Bison","bizonų", listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.frog,"Frog","Pelkės varlė", listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.fish,"Fish","Žuvis", listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.cat,"Cat","Katė", listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.dog,"Dog","šuo", listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.rabbit,"Rabbit","Triušis", listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.snake,"Snake","Gyvatė", listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.boar,"Wild Boar","Šernas", listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.polarbear,"Polar Bear","Baltoji meška", listOf(R.drawable.purp),R.drawable.mic),
            ImageInfo(R.drawable.bear,"Bear","Turėti", listOf(R.drawable.purp),R.drawable.mic)
        )

    }











}