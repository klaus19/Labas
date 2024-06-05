package com.example.visuallithuanian.utils


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class BootCompletedReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED || intent.action == Intent.ACTION_MY_PACKAGE_REPLACED) {
            // Reschedule the notification work
            val workRequest = PeriodicWorkRequestBuilder<Notification1>(24, TimeUnit.HOURS)
                .setInitialDelay(1, TimeUnit.HOURS) // Set the delay as required
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "notification_work",
                ExistingPeriodicWorkPolicy.REPLACE,
                workRequest
            )
        }
    }
}

