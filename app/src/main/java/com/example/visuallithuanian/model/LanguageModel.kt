package com.example.visuallithuanian.model

class LanguageModel(var item_name:String,var img_id:Int) {

    @JvmName("getItem_name1")
    fun getItem_name(): String {
        return item_name
    }

    @JvmName("setItem_name1")
    fun setItem_name(item_name: String) {
        this.item_name=item_name
    }

    fun getImgid(): Int {
        return img_id
    }

    fun setImgid(imgid: Int) {
        this.img_id=imgid
    }
}