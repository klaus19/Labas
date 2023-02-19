package com.example.visuallithuanian.data

import androidx.room.Entity


data class ImageInfo(
    val imageId:Int, val name1:String, val name2:String, var list:List<Int>?,
    val voice:Int?, val audioId:Int?,val english:String,val lithuanian:String)

