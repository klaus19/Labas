package com.example.visuallithuanian.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R

class PractiseAdapter(private val imageNamesMap: HashMap<Int, String>) :
    RecyclerView.Adapter<PractiseAdapter.PractiseViewHolder>() {

    private val imagesList: MutableList<Int> = imageNamesMap.keys.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PractiseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_practise_cards, parent, false)
        return PractiseViewHolder(view)
    }

    override fun getItemCount(): Int = imagesList.size

    override fun onBindViewHolder(holder: PractiseViewHolder, position: Int) {
        val imageResource = imagesList[position]
        val imageName = imageNamesMap[imageResource]

        holder.imageViewPractise.setImageResource(imageResource)
        holder.textViewPractise.text = imageName

        holder.cardImagePractise.setOnClickListener {
            onCardClick(it, position)
        }

        holder.cardTextPractise.setOnClickListener {
            onCardClick(it, position)
        }
    }

    class PractiseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardImagePractise: CardView = itemView.findViewById(R.id.cardImagePractise)
        val cardTextPractise: CardView = itemView.findViewById(R.id.cardTextPractise)
        val imageViewPractise: ImageView = itemView.findViewById(R.id.imageViewPractise)
        val textViewPractise: TextView = itemView.findViewById(R.id.textViewPractise)
    }

    private fun onCardClick(view: View, position: Int) {
        // Check the clicked view's ID to identify which cardView was clicked
        when (view.id) {
            R.id.cardImagePractise -> {
                // Handle image card click
                Toast.makeText(view.context, "Image clicked at position $position", Toast.LENGTH_SHORT).show()
            }
            R.id.cardTextPractise -> {
                // Handle text card click
                Toast.makeText(view.context, "Text clicked at position $position", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
