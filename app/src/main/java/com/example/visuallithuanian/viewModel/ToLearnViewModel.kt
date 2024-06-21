package com.example.visuallithuanian.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ToLearnViewModel: ViewModel() {

    private val _counter = MutableLiveData<Int>(0)
    val counter: LiveData<Int> = _counter

    private val _learnedCounter = MutableLiveData<Int>()
    val learnedCounter: LiveData<Int> = _learnedCounter

    fun incrementCounter(){
        _counter.value = _counter.value?.plus(1)
    }

    fun setCounter(value:Int){
        _counter.value = value
    }

    fun decrementCounter() {
        val currentValue = _counter.value ?: 0
        if (currentValue > 0) {
            _counter.value = currentValue - 1
        }
    }

    // MutableLiveData for decrement counter and increment learned counter
    private val _decrementCounter = MutableLiveData<Int>()
    val decrementCounter: LiveData<Int> get() = _decrementCounter

    private val _incrementLearnedCounter = MutableLiveData<Int>()
    val incrementLearnedCounter: LiveData<Int> get() = _incrementLearnedCounter
    fun setLearnedCounter(value: Int) {
        _learnedCounter.value = value
    }

    fun incrementLearnedCounter() {
        _learnedCounter.value = (_learnedCounter.value ?: 0) + 1
    }

    fun decrementLearnedCounter() {
        _learnedCounter.value = (_learnedCounter.value ?: 0) - 1
    }
}