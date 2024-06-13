package com.example.visuallithuanian.viewModel

import androidx.lifecycle.*
import com.example.visuallithuanian.data.FlashCardInfo
import com.example.visuallithuanian.database.FlashcardPair
import com.example.visuallithuanian.repository.FlashcardRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class FlashCardViewmodel(private val repository:FlashcardRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<FlashcardPair>> = repository.allWords.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertCards(pair:FlashcardPair) = viewModelScope.launch {
        repository.insert(pair)
    }

    fun deleteCards(pair:FlashcardPair) = viewModelScope.launch {
        repository.deleteCard(pair)
    }
}

class WordViewModelFactory(private val repository:FlashcardRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlashCardViewmodel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FlashCardViewmodel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}