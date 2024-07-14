package com.example.visuallithuanian.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.visuallithuanian.R

class SpinnerAdapter(private val context: Context, private val languages: List<Language>):BaseAdapter() {

    data class Language(val name: String, val flagResId: Int)
    override fun getCount(): Int = languages.size

    override fun getItem(position: Int): Any = languages[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false)
        val flag = view.findViewById<ImageView>(R.id.imageViewFlag)
        val language = view.findViewById<TextView>(R.id.textViewLanguage)

        val currentLanguage = languages[position]
        flag.setImageResource(currentLanguage.flagResId)
        language.text = currentLanguage.name

        return view
    }
}