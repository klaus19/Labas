package com.example.visuallithuanian.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.constants.ImageStore


class PractiseAdapter(
    private val imageResources: List<Int>,
    private val imageNames: List<String>
) : RecyclerView.Adapter<PractiseAdapter.PractiseViewHolder>() {

    private var selectedImageResource = -1
    private var selectedImageName = ""
    private var previousSelectedImageResource = -1
    private var previousSelectedImageName = ""

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
        val imageName = imageNames[position]

        holder.imageViewPractise.setImageResource(imageResource)
        holder.textViewPractise.text = imageName

     //   val backgroundColor = if (_isCardColorChangeable && selectedImageResource == imageResource) {
       //     Color.GREEN
        //} else {
          //  Color.WHITE
        //}
     //   holder.cardImagePractise.setCardBackgroundColor(backgroundColor)

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
                selectedImageName = imageNames[position]
                previousSelectedImageName = selectedImageName
            }
            notifyDataSetChanged()
        }

        val nameColor = if(imageNames[position]==selectedImageName){
            if(ImageStore.imagesNamesMap[selectedImageResource]==selectedImageName){
                Toast.makeText(
                    holder.itemView.context,
                    "Correct name selected!",
                    Toast.LENGTH_SHORT
                ).show()
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
              Color.WHITE
        }
        holder.textViewPractise.setBackgroundColor(nameColor)
    }

    class PractiseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardImagePractise: CardView = itemView.findViewById(R.id.cardImagePractise)
        val cardTextPractise: CardView = itemView.findViewById(R.id.cardTextPractise)
        val imageViewPractise: ImageView = itemView.findViewById(R.id.imageViewPractise)
        val textViewPractise: TextView = itemView.findViewById(R.id.textViewPractise)
    }
}