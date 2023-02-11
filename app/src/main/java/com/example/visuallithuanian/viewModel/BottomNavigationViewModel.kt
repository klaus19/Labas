package com.example.visuallithuanian.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BottomNavigationViewModel:ViewModel() {

    private val _bottomNavigationVisibility = MutableLiveData<Boolean>()
    val bottomNavigationVisibility:LiveData<Boolean>
          get() = _bottomNavigationVisibility

    fun setBottomNavigationVisibility(isVisible: Boolean) {

        _bottomNavigationVisibility.value=isVisible
    }

}