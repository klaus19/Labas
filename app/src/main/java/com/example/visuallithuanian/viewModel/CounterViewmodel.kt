package com.example.visuallithuanian.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewmodel: ViewModel() {

    private val _learnCounter = MutableLiveData<Int>()
    val learnCounter: LiveData<Int> get() = _learnCounter

    private val _learntCounter = MutableLiveData<Int>()
    val learntCounter: LiveData<Int> get() = _learntCounter

    init {
        _learnCounter.value = 0
        _learntCounter.value = 0
    }

    fun setLearnCounter(value: Int) {
        _learnCounter.value = value
    }

    fun setLearntCounter(value: Int) {
        _learntCounter.value = value
    }

    fun incrementLearntCounter() {
        _learntCounter.value = (_learntCounter.value ?: 0) + 1
    }

    fun decrementLearnCounter() {
        _learnCounter.value = (_learnCounter.value ?: 0) - 1
    }

}