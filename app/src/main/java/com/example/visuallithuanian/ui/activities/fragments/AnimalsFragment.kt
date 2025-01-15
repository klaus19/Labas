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
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AnimalsFragment : Fragment() {


    lateinit var viewModel: BottomNavigationViewModel
    lateinit var bottomNav:BottomNavigationView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_animals, container, false)

        bottomNav = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
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
                R.drawable.fox,"Red Fox","Raudona lapė",listOf(R.drawable.purp), R.drawable.mic,R.raw.redfox,
                "A red fox ran across the field","Raudona lapė bėgo per lauką"),
            ImageInfo(
                R.drawable.wolf,"Wolf","Vilkas", listOf(R.drawable.purp), R.drawable.mic,R.raw.wolf,
                "The wolf howled towards the moon" , "Vilkas šaukė į mėnulį"),
            ImageInfo(
                R.drawable.squir,"Squirrel","Voverė", listOf(R.drawable.purp),
                R.drawable.mic,R.raw.squireel,"The squirrel climbed up the tree","Voveraitė kopė į medį"),
            ImageInfo(
                R.drawable.moose,"Moose","Briedis", listOf(R.drawable.purp),
                R.drawable.mic,R.raw.moose,"The moose was standing in the pond","Briedis stovėjo ežere"),
            ImageInfo(
                R.drawable.beaver,"Beaver","Bebras", listOf(R.drawable.purp),
                R.drawable.mic,R.raw.beaver,"The beaver was swimming in the river","Bebras plaukiojo upėje"),
            ImageInfo(
                R.drawable.bison,"Bison","bizonas", listOf(R.drawable.purp),
                R.drawable.mic,R.raw.bison,"The bison roamed the prairie","Bizonas klajojo per prerijas"),
            ImageInfo(
                R.drawable.frog,"Frog","Varlė", listOf(R.drawable.purp),
                R.drawable.mic,R.raw.frog,"The frog jumped into the pond","Varlė šoko į ežerą"),

            ImageInfo(
                R.drawable.cat,"Cat","Katė", listOf(R.drawable.purp),
                R.drawable.mic,R.raw.cat,"The cat was sleeping on the windowsill","Katė miegojo ant palangės"),
            ImageInfo(
                R.drawable.dog,"Dog","šuo", listOf(R.drawable.purp),
                R.drawable.mic,R.raw.dog,"The dog barked at the mailman","Šuo lojo ant pašto darbuotoją"),
            ImageInfo(
                R.drawable.boar,"Wild Boar","Šernas", listOf(R.drawable.purp),
                R.drawable.mic,R.raw.wildboar,"The wild boar was searching for food","Šernas ieškojo maisto"),
            ImageInfo(
                R.drawable.polarbear,"Polar Bear","Baltoji meška", listOf(R.drawable.purp),
                R.drawable.mic,R.raw.polarbear,"The polar bear hunted the seal on the ice",
                "Baltoji meška  medžiojo ruonius ant ledo"),
            ImageInfo(
                R.drawable.bear,"Bear","Meška", listOf(R.drawable.purp), R.drawable.mic,R.raw.bear,
                "The bear was searching for honey inside the forest","Meška ieškojo medaus girioje"),
            ImageInfo(
                R.drawable.horse,"Horse","Arklys", listOf(R.drawable.purp), R.drawable.mic,R.raw.horse,
                "The horse is running","Arklys bėga"),
            ImageInfo(
                R.drawable.snake,"Snake","Gyvatė", listOf(R.drawable.purp),
                R.drawable.mic,R.raw.snake,"The snake was moving","Gyvatė judėjo"),
            ImageInfo(
                R.drawable.rabbit,"Rabbit","Triušis", listOf(R.drawable.purp),
                R.drawable.mic,R.raw.rabbit,"The rabbit was hopping across the garden","Triušis šokinėjo per sodą"),
            ImageInfo(
                R.drawable.fish,"Fish","Žuvis", listOf(R.drawable.purp),
                R.drawable.mic,R.raw.fish,"The fish swam within the stream","Žuvis plaukiojo srovėje"),
            ImageInfo(
                R.drawable.cow,"Cow","Karvė", listOf(R.drawable.purp), R.drawable.mic,R.raw.cow,
                "The cow gives milk","Karvė duoda pieno"),
            ImageInfo(
                R.drawable.deer,"Deer","elnias", listOf(R.drawable.purp),
                R.drawable.mic,R.raw.deer,"The deer was grazing in the meadow","Elnias žiūrėjo pievoje "),
            ImageInfo(
                R.drawable.lion,"Lion","Liūtas", listOf(R.drawable.purp), R.drawable.mic,R.raw.lion,
                "The lion roared loudly", "Liūtas garsiai riaumojo"),
            ImageInfo(
                R.drawable.tiger,"Tiger","Tigras", listOf(R.drawable.purp),
                R.drawable.mic,R.raw.tiger,"The tiger was walking","Tigras vaikščiojo"),

            )

    }











}