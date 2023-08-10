package com.example.visuallithuanian.Utils


fun <T> List<T>.shuffleList(): List<T> {
    val list = this.toMutableList()
    list.shuffle()
    return list
}