package com.example.visuallithuanian.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.visuallithuanian.R
import com.example.visuallithuanian.ui.activities.fragments.SentenceFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Notification1: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val sharedPreferences = context?.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val json = sharedPreferences?.getString("phrases_list", null)

        if (json != null) {
            val type = object : TypeToken<List<SentenceFragment.Phrase>>() {}.type
            val phrasesList: List<SentenceFragment.Phrase> = Gson().fromJson(json, type)

            if (phrasesList.isNotEmpty()) {
                // Randomly select a phrase
                val randomPhrase = phrasesList.random()

                val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val notificationId = 1
                val channelId = "default_channel_id"
                val channelName = "Default Channel"

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
                    notificationManager.createNotificationChannel(notificationChannel)
                }

                val notificationBuilder = NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(R.drawable.cat1)
                    .setContentTitle("Revision Time")
                    .setContentText("${randomPhrase.englishPhrase}\n${randomPhrase.lithuanianPhrase}")
                    .setAutoCancel(true)
                    .setColor(context.resources.getColor(R.color.orange1)) // Change the color to your desired color
                    .setStyle(NotificationCompat.BigTextStyle().bigText("${randomPhrase.englishPhrase}\n${randomPhrase.lithuanianPhrase}"))

                notificationManager.notify(notificationId, notificationBuilder.build())
            }
        }
    }
}
