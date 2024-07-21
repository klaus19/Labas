package com.example.visuallithuanian.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.visuallithuanian.R
import com.example.visuallithuanian.model.LanguageModel

class LanguageAdapter(context: Context, itemsModelArrayList: ArrayList<LanguageModel>):

   ArrayAdapter<LanguageModel?>(context,0, itemsModelArrayList!! as List<LanguageModel?>){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var listItemView = convertView
        if(listItemView==null){
            listItemView = LayoutInflater.from(context).inflate(R.layout.card_item,parent,false)
        }

        val languageModel:LanguageModel? = getItem(position)
       // val textItem = listItemView!!.findViewById<TextView>(R.id.text_Lithuanian)
        val textItem2 = listItemView!!.findViewById<TextView>(R.id.text_English)
        val imageLanguage = listItemView.findViewById<ImageView>(R.id.imageViewCard)

      //  textItem.setText(languageModel?.getItem_name())
        textItem2.text = languageModel?.getItem_English()
        if (languageModel != null) {
            imageLanguage.setImageResource(languageModel.getImgid())
        }
        return listItemView
    }



}