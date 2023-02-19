package com.example.visuallithuanian.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Build
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.data.ImageInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.Text


class ImageAdapter(private val imageList:List<ImageInfo>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView1: TextView = itemView.findViewById(R.id.name1TextView)
        val textView2: TextView = itemView.findViewById(R.id.name2TextView)
        val englishTextView: TextView = itemView.findViewById(R.id.hiddenTextViewEnglish)
        val lithuanianTextView: TextView = itemView.findViewById(R.id.hiddenTextViewLithuanian)
        val pluralEnglish:TextView = itemView.findViewById(R.id.pluralEnglish)
        val pluralLithuanian:TextView = itemView.findViewById(R.id.pluralLithuanian)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = imageList[position]
        holder.imageView.setImageResource(currentItem.imageId)
        holder.textView1.text = currentItem.name1
        holder.textView2.text = currentItem.name2
        holder.englishTextView.text = currentItem.english
        holder.lithuanianTextView.text = currentItem.lithuanian
        holder.pluralEnglish.text = currentItem.pluralEnglish
        holder.pluralLithuanian.text = currentItem.pluralLithuanian

        val imageIcon =
            holder.itemView.findViewById<ImageView>(R.id.gemCount)
        val cardAnimals =
            holder.itemView.findViewById<CardView>(R.id.cardAnimals)
        val textHiddenEnglish =
            holder.itemView.findViewById<TextView>(R.id.hiddenTextViewEnglish)

        //Code for the emoji bouncy animation
        val txtLithuanian = holder.itemView.findViewById<TextView>(R.id.hiddenTextViewLithuanian)

         //Code where the cardview moves to left and Textview gets shown behind it.
        imageIcon.setOnClickListener {

            val anim = ObjectAnimator.ofFloat(cardAnimals, "translationX", -1350f)
            anim.duration = 2000
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
    }
    override fun getItemCount() = imageList.size
}
