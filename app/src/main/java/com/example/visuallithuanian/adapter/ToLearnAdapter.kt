package com.example.visuallithuanian.adapter

import android.animation.ObjectAnimator
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.database.FlashcardPair

class ToLearnAdapter(
    private val onDeleteListener: (FlashcardPair) -> Unit,
) : ListAdapter<FlashcardPair, ToLearnAdapter.WordViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.front, current.back, current.imageSrc)
        holder.itemView.setBackgroundResource(R.color.white1)
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordEnglish: TextView = itemView.findViewById(R.id.textView1English)
        private val wordLithuanian: TextView = itemView.findViewById(R.id.textView2Lithuanian)
        private val imageHelper = itemView.findViewById<ImageView>(R.id.imageCardHelper)
        private val relativeLeft = itemView.findViewById<RelativeLayout>(R.id.relativeLeft)
        private val relativeRight = itemView.findViewById<RelativeLayout>(R.id.relativeRight)

        private var clickCount = 0

        fun bind(text: String?, text1: String?, imageSource: Int) {
            wordEnglish.text = text
            wordLithuanian.text = text1
            imageHelper.setImageResource(imageSource)

            itemView.findViewById<CardView>(R.id.card_view).setOnClickListener {
                if (clickCount == 0) {
                    // Rotate the card view slowly
                    val rotationAnimator = ObjectAnimator.ofFloat(itemView, View.ROTATION, 0f, 360f)
                    rotationAnimator.apply {
                        duration = 1000 // Adjust duration as needed
                        start()
                    }
                    imageHelper.visibility = View.VISIBLE
                    wordLithuanian.visibility = View.VISIBLE
                } else {
                    toggleRelativeLayoutVisibility()
                }
                clickCount++
            }
        }

        private fun toggleRelativeLayoutVisibility() {
            if (relativeLeft.visibility == View.VISIBLE && relativeRight.visibility == View.VISIBLE) {
                relativeLeft.visibility = View.INVISIBLE
                relativeRight.visibility = View.INVISIBLE
                wordLithuanian.visibility = View.INVISIBLE
            } else {
                relativeLeft.visibility = View.VISIBLE
                relativeRight.visibility = View.VISIBLE
                wordLithuanian.visibility = View.VISIBLE
            }
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