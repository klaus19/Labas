package com.example.visuallithuanian.`interface`

import com.example.visuallithuanian.repository.FlashcardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface FlashcardModules {

    @Binds
    fun provideFlashcardRepository(repository: FlashcardRepository):FlashcardRepository


}