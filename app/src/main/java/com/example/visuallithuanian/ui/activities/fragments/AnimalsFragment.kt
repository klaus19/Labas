package com.example.visuallithuanian.ui.activities.fragments


import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.ImageAdapter
import com.example.visuallithuanian.base.BottomNavigationScrollListener
import com.example.visuallithuanian.data.ImageInfo
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


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

        // Added a functionality where the bottomnavigation view will get invisible while scrolling and
        // appear after scrolling is stopped
         recyclerView?.addOnScrollListener(BottomNavigationScrollListener(viewModel))
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
            ImageInfo(
                R.drawable.fox,"Red Fox","Raudona lapė",listOf(R.drawable.purp), R.drawable.mic,
                "A red fox ran across the field","Raudona lapė bėgo per lauką"),
            ImageInfo(
                R.drawable.wolf,"Wolf","Vilkas", listOf(R.drawable.purp), R.drawable.mic,
                "The wolf howled towards the moon" , "Vilkas šaukė į mėnulį"),
            ImageInfo(
                R.drawable.lion,"Lion","Liūtas", listOf(R.drawable.purp), R.drawable.mic,
                "The lion roared loudly", "Liūtas garsiai riaumojo"),
            ImageInfo(
                R.drawable.tiger,"Tiger","Tigras", listOf(R.drawable.purp),
                R.drawable.mic,"The tiger was walking","Tigras vaikščiojo"),
            ImageInfo(
                R.drawable.squir,"Squirrel","Voverė", listOf(R.drawable.purp),
                R.drawable.mic,"The squirrel climbed up the tree","Voveraitė kopė į medį"),
            ImageInfo(
                R.drawable.moose,"Moose","Briedis", listOf(R.drawable.purp),
                R.drawable.mic,"The moose was standing in the pond","Briedis stovėjo ežere"),
            ImageInfo(
                R.drawable.deer,"Deer","elnias", listOf(R.drawable.purp),
                R.drawable.mic,"The deer was grazing in the meadow","Elnias žiūrėjo pievoje "),
            ImageInfo(
                R.drawable.beaver,"Beaver","Bebras", listOf(R.drawable.purp),
                R.drawable.mic,"The beaver was swimming in the river","Bebras plaukiojo upėje"),
            ImageInfo(
                R.drawable.bison,"Bison","bizonų", listOf(R.drawable.purp),
                R.drawable.mic,"The bison roamed the prairie","Bizonas klajojo per prerijas"),
            ImageInfo(
                R.drawable.frog,"Frog","Varlė", listOf(R.drawable.purp),
                R.drawable.mic,"The frog jumped into the pond","Varlė šoko į ežerą"),
            ImageInfo(
                R.drawable.fish,"Fish","Žuvis", listOf(R.drawable.purp),
                R.drawable.mic,"The fish swam within the stream","Žuvis plaukiojo srovėje"),
            ImageInfo(
                R.drawable.cat,"Cat","Katė", listOf(R.drawable.purp),
                R.drawable.mic,"The cat was sleeping on the windowsill","Katė miegojo ant palangės"),
            ImageInfo(
                R.drawable.dog,"Dog","šuo", listOf(R.drawable.purp),
                R.drawable.mic,"The dog barked at the mailman","Šuo lojo ant pašto darbuotoją"),
            ImageInfo(
                R.drawable.rabbit,"Rabbit","Triušis", listOf(R.drawable.purp),
                R.drawable.mic,"The rabbit was hopping across the garden","Triušis šokinėjo per sodą"),
            ImageInfo(
                R.drawable.snake,"Snake","Gyvatė", listOf(R.drawable.purp),
                R.drawable.mic,"The snake was moving","Gyvatė judėjo"),
            ImageInfo(
                R.drawable.boar,"Wild Boar","Šernas", listOf(R.drawable.purp),
                R.drawable.mic,"The wild boar was searching for food","Šernas ieškojo maisto"),
            ImageInfo(
                R.drawable.polarbear,"Polar Bear","Baltoji meška", listOf(R.drawable.purp),
                R.drawable.mic,"The polar bear hunted the seal on the ice",
                "Baltoji meška  medžiojo ruonius ant ledo"),
            ImageInfo(
                R.drawable.bear,"Bear","Turėti", listOf(R.drawable.purp), R.drawable.mic,
                "The bear was searching for honey inside the forest","Meška ieškojo medaus girioje")
        )

    }











}