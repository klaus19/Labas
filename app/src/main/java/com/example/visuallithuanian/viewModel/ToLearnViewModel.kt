package com.example.visuallithuanian.viewModel

import androidx.lifecycle.ViewModel


class ToLearnViewModel: ViewModel() {

    var count = 0
    fun addWordCount(){
        count++
    }

    override fun onCleared() {
        super.onCleared()
    }
}