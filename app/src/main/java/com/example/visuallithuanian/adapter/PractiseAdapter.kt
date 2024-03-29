package com.example.visuallithuanian.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.Utils.shuffleList
import com.example.visuallithuanian.constants.ImageStore


class PractiseAdapter(
    private var imageResources: MutableList<Int>,
    private var imageNames1: MutableList<Pair<String,String>>,
    btnShuffle: AppCompatButton,
    recyclerViewPractise: RecyclerView,
) : RecyclerView.Adapter<PractiseAdapter.PractiseViewHolder>() {

    lateinit var recyclerView: RecyclerView
    private var anyCardIsGreen = false
    private var selectedImageResource = -1
    private var selectedImageName = ""
    private var previousSelectedImageResource = -1
    private var previousSelectedImageName = ""

    init {

        btnShuffle.setOnClickListener {
            shuffleCards()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun shuffleCards() {
        // Shuffle the card data (images and names)
        // Reset the selected image and name

        val shuffledKeys = ImageStore.imagesNamesMap.keys.shuffled()

        val selectedPairs = shuffledKeys.take(4).associateWith { ImageStore.imagesNamesMap[it] }

        imageResources = selectedPairs.keys.toMutableList()
        imageNames1 = selectedPairs.values.mapNotNull { it }.toMutableList()


        selectedImageResource = -1
        selectedImageName = ""
        previousSelectedImageResource = -1
        previousSelectedImageName = ""


        // Reset the background color of cardImage views to white
        resetCardImageBackgroundToWhite()


        notifyDataSetChanged()
    }

    fun initsetRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }

    private fun resetCardImageBackgroundToWhite() {
        // Iterate through the card views and reset the background color of cardImage to white
        for (position in 0 until imageResources.size) {

            val holder = recyclerView.findViewHolderForAdapterPosition(position) as? PractiseViewHolder
            holder?.cardImagePractise?.setCardBackgroundColor(Color.WHITE)
        }
    }

    companion object {
        val GREEN_COLOR = Color.parseColor("#ABEBC6")
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PractiseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_practise_cards, parent, false)

        return PractiseViewHolder(view)
    }

    override fun getItemCount(): Int = imageResources.size

    override fun onBindViewHolder(holder: PractiseViewHolder, position: Int) {
        val imageResource = imageResources[position]
        val imageName1 = imageNames1[position]


        holder.imageViewPractise.setImageResource(imageResource)
        holder.textViewPractise.text = imageName1.first
        holder.textViewPractise1.text = imageName1.second

        holder.cardImagePractise.setOnClickListener {
            selectedImageResource = imageResource
            previousSelectedImageResource = selectedImageResource
            selectedImageName = ""
            notifyDataSetChanged()
            holder.cardImagePractise.setCardBackgroundColor(GREEN_COLOR)
        }
        holder.cardTextPractise.setOnClickListener {
            if (selectedImageResource == -1) {
                Toast.makeText(
                    it.context,
                    "Please select an image card first.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                selectedImageName = imageNames1[position].first
                previousSelectedImageName = selectedImageName
            }
            notifyDataSetChanged()
        }
        val nameColor = if(imageNames1[position].first ==selectedImageName){
            if(ImageStore.imagesNamesMap[selectedImageResource]?.first==selectedImageName){
                Toast.makeText(
                    holder.itemView.context,
                    "Correct name selected!",
                    Toast.LENGTH_SHORT
                ).show()
                anyCardIsGreen=true
                holder.cardTextPractise.setBackgroundColor(GREEN_COLOR) // Set green background for the name card
                Color.GREEN
            } else {
                Toast.makeText(
                    holder.itemView.context,
                    "Wrong name selected!",
                    Toast.LENGTH_SHORT
                ).show()
                Color.RED
            }
        } else {
              holder.cardTextPractise.setBackgroundColor(Color.WHITE)
              Color.WHITE
        }
        holder.cardTextPractise.setBackgroundColor(nameColor)

        // Check if the card is green and set anyCardIsGreen accordingly

    }
    class PractiseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardImagePractise: CardView = itemView.findViewById(R.id.cardImagePractise)
        val cardTextPractise: CardView = itemView.findViewById(R.id.cardTextPractise)
        val imageViewPractise: ImageView = itemView.findViewById(R.id.imageViewPractise)
        val textViewPractise: TextView = itemView.findViewById(R.id.textViewPractise)
        val textViewPractise1: TextView = itemView.findViewById(R.id.textViewPractise1)

    }
}
