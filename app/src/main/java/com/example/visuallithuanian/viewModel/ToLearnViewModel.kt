package com.example.visuallithuanian.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ToLearnViewModel: ViewModel() {

    private val _counter = MutableLiveData<Int>(0)
    val counter: LiveData<Int> = _counter

    fun incrementCounter(){
        _counter.value = _counter.value?.plus(1)
    }
}