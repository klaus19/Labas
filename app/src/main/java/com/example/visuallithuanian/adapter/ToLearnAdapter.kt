package com.example.visuallithuanian.adapter

import android.animation.ObjectAnimator
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.database.FlashcardPair
import com.example.visuallithuanian.interfaces.RelativeCallback

class ToLearnAdapter(
    private val onDeleteListener: (FlashcardPair) -> Unit,

) : ListAdapter<FlashcardPair, ToLearnAdapter.WordViewHolder>(WordsComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view, parent, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)

    }

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordEnglish: TextView = itemView.findViewById(R.id.textView1English)
        private val wordLithuanian: TextView = itemView.findViewById(R.id.textView2Lithuanian)
        private val imageHelper: ImageView = itemView.findViewById(R.id.imageCardHelper)
        private val relativeLeft: RelativeLayout = itemView.findViewById(R.id.relativeLeft)
        private val relativeRight: RelativeLayout = itemView.findViewById(R.id.relativeRight)
        private val btnPlay: ImageView = itemView.findViewById(R.id.btnPlay)
        private var mediaPlayer: MediaPlayer? = null

        private var clickCount = 0

        init {
            wordLithuanian.visibility = View.GONE
            relativeLeft.visibility = View.GONE
            relativeRight.visibility = View.GONE

            btnPlay.setOnClickListener {
                val audioSrc = getItem(adapterPosition).voiceclip
                playAudio(audioSrc)
            }

            itemView.setOnClickListener {
                toggleDetailsVisibility()
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
            wordEnglish.text = item.front
            wordLithuanian.text = item.back
            imageHelper.setImageResource(item.imageSrc)


            resetViewState()
        }


        private fun toggleDetailsVisibility() {
            if (clickCount % 2 == 0) {
                rotateCard()
                showDetails()
            } else {
                hideDetails()
            }
            clickCount++
        }

        private fun rotateCard() {
            val rotationAnimator = ObjectAnimator.ofFloat(itemView, View.ROTATION_Y, 0f, 360f)
            rotationAnimator.duration = 1000
            rotationAnimator.start()
        }

        private fun showDetails() {
            wordLithuanian.visibility = View.VISIBLE
            relativeLeft.visibility = View.VISIBLE
            relativeRight.visibility = View.VISIBLE
        }

        private fun hideDetails() {
            wordLithuanian.visibility = View.GONE
            relativeLeft.visibility = View.GONE
            relativeRight.visibility = View.GONE
        }

        private fun resetViewState() {
            hideDetails()
            clickCount = 0
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<FlashcardPair>() {
        override fun areItemsTheSame(oldItem: FlashcardPair, newItem: FlashcardPair): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: FlashcardPair, newItem: FlashcardPair): Boolean {
            return oldItem.front == newItem.front && oldItem.back == newItem.back
        }
    }




}
