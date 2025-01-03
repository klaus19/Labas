package com.example.visuallithuanian.ui.activities.fragments

import android.app.Application
import com.example.visuallithuanian.database.FlashcardDatabase
import com.example.visuallithuanian.repository.FlashcardRepository
import com.google.android.gms.ads.MobileAds
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

    override fun onCreate() {
        super.onCreate()

        // Initialize Mobile Ads SDK
        MobileAds.initialize(this){ initializationStatus ->
            // Log or handle the initialization status if needed
            println("Mobile Ads SDK initialized: $initializationStatus")
        }
    }
}