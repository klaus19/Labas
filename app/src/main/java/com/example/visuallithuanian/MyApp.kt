package com.example.visuallithuanian

import android.app.Application
import com.example.visuallithuanian.database.FlashcardDatabase
import com.example.visuallithuanian.repository.FlashcardRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class MyApp:Application() {

    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { FlashcardDatabase.getDatabase(this,applicationScope) }
    val repository by lazy {FlashcardRepository(database.cardPairDao()) }
}