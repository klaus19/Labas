package com.example.visuallithuanian.utils


import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.visuallithuanian.R

class Notification1(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val sharedPreferences = applicationContext.getSharedPreferences("PhrasesData", Context.MODE_PRIVATE)

        val englishWord = sharedPreferences.getString("englishWord", "No data")
        val lithuanianWord = sharedPreferences.getString("lithuanianWord", "No data")
        val englishPhrase = sharedPreferences.getString("englishPhrase", "No data")
        val lithuanianPhrase = sharedPreferences.getString("lithuanianPhrase", "No data")

        val notificationText = "English: $englishWord, Lithuanian: $lithuanianWord, English Phrase: $englishPhrase, Lithuanian Phrase: $lithuanianPhrase"

        sendNotification(notificationText)

        return Result.success()
    }

    private fun sendNotification(notificationText: String) {
        val channelId = "phrases_channel_id"
        val notificationId = 1

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Phrases Channel"
            val descriptionText = "Channel for phrases notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.cat1)
            .setContentTitle("Learn Lithuanian")
            .setContentText(notificationText)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(applicationContext)) {
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            notify(notificationId, builder.build())
        }
    }
}
