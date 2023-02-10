package com.example.visuallithuanian.adapter

import android.R
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.data.ImageInfo


class ImageAdapter(private val imageList: List<ImageInfo>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(com.example.visuallithuanian.R.id.imageView)
        val textView1: TextView = itemView.findViewById(com.example.visuallithuanian.R.id.name1TextView)
        val textView2: TextView = itemView.findViewById(com.example.visuallithuanian.R.id.name2TextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.example.visuallithuanian.R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = imageList[position]
        holder.imageView.setImageResource(currentItem.imageId)
        holder.textView1.text = currentItem.name1
        holder.textView2.text = currentItem.name2

        val imageIcon =
            holder.itemView.findViewById<ImageView>(com.example.visuallithuanian.R.id.gemCount)
        val cardAnimals =
            holder.itemView.findViewById<CardView>(com.example.visuallithuanian.R.id.cardAnimals)
        val textHidden =
            holder.itemView.findViewById<TextView>(com.example.visuallithuanian.R.id.hiddenTextView)

        //Code for the emoji bouncy animation
        val imageEmoji = holder.itemView.findViewById<ImageView>(com.example.visuallithuanian.R.id.emojiImageView)

         //Code where the cardview moves to left and Textview gets shown behind it.
        imageIcon.setOnClickListener {
            val anim = ObjectAnimator.ofFloat(cardAnimals, "translationX", -500f)
            anim.duration = 1000
            anim.start()
            anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    val anim = ObjectAnimator.ofFloat(cardAnimals, "translationX", 0f)
                    anim.duration = 1000
                    anim.start()
                    anim.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            textHidden.visibility = View.VISIBLE
                        }
                    })
                }
            })
        }

    }
    override fun getItemCount() = imageList.size
}
