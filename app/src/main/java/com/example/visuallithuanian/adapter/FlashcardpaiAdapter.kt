package com.example.visuallithuanian.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.database.FlashcardPair

class FlashcardpaiAdapter(private val onDeleteListener:((FlashcardPair)->Unit)) : ListAdapter<FlashcardPair,FlashcardpaiAdapter.WordViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        val current1 = getItem(position)
        holder.bind(current.front,current1.back)

        // Set different colors for alternate rows
        val colorRes = if (position % 2 == 0) {
            R.color.even // Color resource for even rows
        } else {
            R.color.pink// Color resource for odd rows
        }
        holder.itemView.setBackgroundColor(holder.itemView.context.getColor(colorRes))

    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordItemView: TextView = itemView.findViewById(R.id.textView)
        private val deftext: TextView = itemView.findViewById(R.id.text1)

        fun bind(text: String?,text1: String?) {
            wordItemView.text = text
            deftext.text=text1
        }

        companion object {
            fun create(parent: ViewGroup): WordViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycle_view, parent, false)
                return WordViewHolder(view)
            }
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<FlashcardPair>() {
        override fun areItemsTheSame(oldItem:FlashcardPair, newItem:FlashcardPair): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem:FlashcardPair, newItem:FlashcardPair): Boolean {
            return oldItem.front == newItem.front && oldItem.back==newItem.back
        }
    }
}