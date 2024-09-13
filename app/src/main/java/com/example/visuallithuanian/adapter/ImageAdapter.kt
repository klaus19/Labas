package com.example.visuallithuanian.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.data.ImageInfo


class ImageAdapter(private val imageList:List<ImageInfo>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageClip:ImageView = itemView.findViewById(com.example.visuallithuanian.R.id.imageAudio)
        val imageView: ImageView = itemView.findViewById(com.example.visuallithuanian.R.id.imageView)
        val textView1: TextView = itemView.findViewById(com.example.visuallithuanian.R.id.name1TextView)
        val textView2: TextView = itemView.findViewById(com.example.visuallithuanian.R.id.name2TextView)
        val englishTextView: TextView = itemView.findViewById(com.example.visuallithuanian.R.id.hiddenTextViewEnglish)
        val lithuanianTextView: TextView = itemView.findViewById(com.example.visuallithuanian.R.id.hiddenTextViewLithuanian)
        var mediaPlayer: MediaPlayer? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.example.visuallithuanian.R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = imageList[position]
        holder.imageView.setImageResource(currentItem.imageId)
        currentItem.voice?.let { holder.imageClip.setImageResource(it) }
        holder.textView1.text = currentItem.name1
        holder.textView2.text = currentItem.name2
        holder.englishTextView.text = currentItem.english
        holder.lithuanianTextView.text = currentItem.lithuanian

        val imageAudio  = holder.itemView.findViewById<ImageView>(com.example.visuallithuanian.R.id.imageAudio)
        val imageIcon =
            holder.itemView.findViewById<ImageView>(com.example.visuallithuanian.R.id.gemCount)
        val cardAnimals =
            holder.itemView.findViewById<CardView>(com.example.visuallithuanian.R.id.cardAnimals)
        val textHiddenEnglish =
            holder.itemView.findViewById<TextView>(com.example.visuallithuanian.R.id.hiddenTextViewEnglish)

        //Code for the emoji bouncy animation
        val txtLithuanian = holder.itemView.findViewById<TextView>(com.example.visuallithuanian.R.id.hiddenTextViewLithuanian)

         //Code where the cardview moves to left and Textview gets shown behind it.
        imageIcon.setOnClickListener {

            val anim = ObjectAnimator.ofFloat(cardAnimals, "translationX", -1350f)
            anim.duration = 3000
            anim.start()
            anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    val anim = ObjectAnimator.ofFloat(cardAnimals, "translationX", 0f)
                    anim.duration = 4000
                    anim.start()
                    anim.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            textHiddenEnglish.visibility = View.VISIBLE
                            txtLithuanian.visibility = View.VISIBLE
                        }
                    })
                }
            })
        }
        val audioId = currentItem.audioId
        val mediaPlayer = audioId.run {
            MediaPlayer.create(holder.itemView.context, this)
        } ?: throw IllegalArgumentException("audioId cannot be null")
        holder.mediaPlayer = mediaPlayer

        imageAudio?.setOnClickListener {
           mediaPlayer.start()
        }


    }
    override fun getItemCount() = imageList.size

}
