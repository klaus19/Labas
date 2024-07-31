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
import com.example.visuallithuanian.constants.ImageStore
import com.example.visuallithuanian.model.PreferencesHelper
import kotlin.properties.Delegates

class PractiseAdapter(
    private var imageResources: MutableList<Int>,
    private var imageNames1: MutableList<Triple<String, String, Int>>,
    btnShuffle: AppCompatButton,
    private var recyclerView1: RecyclerView,
    private val preferencesHelper: PreferencesHelper,
    private val onCorrectPair: (Int) -> Unit,
    private val onDataChanged: () -> Unit,
    private val textCounterFire: TextView,
    private val textCounterPurpleDiamond: TextView,
    private val updateTextCountCallback: (Int) -> Unit,
    private val updateTextCountPurple: (Int) -> Unit
) : RecyclerView.Adapter<PractiseAdapter.PractiseViewHolder>() {

    private var selectedImageResource = -1
    private var selectedImageName = ""
    private var previousSelectedImageResource = -1
    private var previousSelectedImageName = ""
    private var currentCount = 0
    private var currentCountPurple = 0

    init {
        btnShuffle.setOnClickListener {
            shuffleCards()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun shuffleCards() {
        val randomPairs = ImageStore.getRandomPairs(4)

        imageResources.clear()
        imageNames1.clear()

        imageResources.addAll(randomPairs.map { it.first })
        imageNames1.addAll(randomPairs.map { it.second })

        imageResources.shuffle()
        imageNames1.shuffle()

        selectedImageResource = -1
        selectedImageName = ""
        previousSelectedImageResource = -1
        previousSelectedImageName = ""

        resetCardImageBackgroundToWhite()
        notifyDataSetChanged()
        onDataChanged()
    }

    fun initsetRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView1 = recyclerView
    }

    private fun resetCardImageBackgroundToWhite() {
        for (position in 0 until imageResources.size) {
            val holder = recyclerView1.findViewHolderForAdapterPosition(position) as? PractiseViewHolder
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
                notifyDataSetChanged()
            }
        }

        val nameColor = if (imageNames1[position].first == selectedImageName) {
            if (ImageStore.imagesNamesMap[selectedImageResource]?.first == selectedImageName) {
                holder.cardTextPractise.setBackgroundColor(GREEN_COLOR)
                preferencesHelper.incrementCounter()
                preferencesHelper.incrementPurpleCount()
                onCorrectPair(selectedImageResource)
                onDataChanged()

                currentCount = textCounterFire.text.toString().toIntOrNull() ?: 0
                val newCount = currentCount + 1
                updateTextCountCallback(newCount)

                // Check if the currentCount is a multiple of 10
                if (newCount % 10 == 0) {
                    currentCountPurple = textCounterPurpleDiamond.text.toString().toIntOrNull() ?: 0
                    val newPurpleCount = currentCountPurple + 2
                    updateTextCountPurple(newPurpleCount)
                }

                Color.GREEN
            } else {
                Color.RED
            }
        } else {
            holder.cardTextPractise.setBackgroundColor(Color.WHITE)
            Color.WHITE
        }
        holder.cardTextPractise.setBackgroundColor(nameColor)
    }

    class PractiseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardImagePractise: CardView = itemView.findViewById(R.id.cardImagePractise)
        val cardTextPractise: CardView = itemView.findViewById(R.id.cardTextPractise)
        val imageViewPractise: ImageView = itemView.findViewById(R.id.imageViewPractise)
        val textViewPractise: TextView = itemView.findViewById(R.id.textViewPractise)
        val textViewPractise1: TextView = itemView.findViewById(R.id.textViewPractise1)
    }
}
