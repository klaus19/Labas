package com.example.visuallithuanian.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ToLearnViewModel: ViewModel() {

    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int> = _counter

    private val _learnedCounter = MutableLiveData<Int>()
    val learnedCounter: LiveData<Int> = _learnedCounter


    init {
        _counter.value=0
        _learnedCounter.value=0
    }

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

      // for learned count
    fun incrementLearnedCounter() {
        _learnedCounter.value = _learnedCounter.value?.plus(1)
    }
    fun setCounterLearned(value: Int){
        _learnedCounter.value = value
    }

    fun decrementCounterLearn(){
          val currentValue = _learnedCounter.value?:0
         if(currentValue>0){
             _learnedCounter.value = currentValue-1
         }
    }


}