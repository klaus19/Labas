package com.example.visuallithuanian.model

class LanguageModel(var item_name:String,var img_id:Int,var item_english:String) {

    @JvmName("getItem_name1")
    fun getItem_name(): String {
        return item_name
    }

    @JvmName("setItem_name1")
    fun setItem_name(item_name: String) {
        this.item_name=item_name
    }

    @JvmName("getItem_name2")
    fun getItem_English():String{
        return item_english
    }

    @JvmName("setItem_name2")
    fun setItem_English(item_english: String){
        this.item_english=item_english
    }

    fun getImgid(): Int {
        return img_id
    }

    fun setImgid(imgid: Int) {
        this.img_id=imgid
    }
}