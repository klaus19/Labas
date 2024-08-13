package com.example.visuallithuanian.adapter

import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.database.FlashcardPair


class LearnedWordsAdapter :
    ListAdapter<FlashcardPair, LearnedWordsAdapter.ViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_learned_word, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textEnglishWord: TextView = itemView.findViewById(R.id.textViewEnglishWord)
        private val textLithuanianWord: TextView = itemView.findViewById(R.id.textView2LithuanianWord)
        private val imageViewLearnt: ImageView = itemView.findViewById(R.id.imageViewWord)
        private val playButton: ImageView = itemView.findViewById(R.id.playButton)
        private var mediaPlayer: MediaPlayer? = null


        init {
            playButton.setOnClickListener {
                val audioSrc = getItem(adapterPosition).voiceclip
                playAudio(audioSrc)
            }
        }


        private fun playAudio(audioSrc: Int) {
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer().apply {
                setDataSource(itemView.context, Uri.parse("android.resource://${itemView.context.packageName}/$audioSrc"))
                prepareAsync()
                setOnPreparedListener {
                    it.start()
                }
            }
        }

        fun bind(item: FlashcardPair) {
            textEnglishWord.text = item.front
            textLithuanianWord.text = item.back
            imageViewLearnt.setImageResource(item.imageSrc)
        }


    }

    class WordsComparator : DiffUtil.ItemCallback<FlashcardPair>() {
        override fun areItemsTheSame(oldItem: FlashcardPair, newItem: FlashcardPair): Boolean {
            return oldItem.front == newItem.front // Use a unique identifier if available
        }

        override fun areContentsTheSame(oldItem: FlashcardPair, newItem: FlashcardPair): Boolean {
            return oldItem == newItem
        }
    }
}
