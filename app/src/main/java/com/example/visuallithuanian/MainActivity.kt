package com.example.visuallithuanian

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.visuallithuanian.ui.FirstScreen
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    val activityScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityScope.launch {

            delay(1000)
            val intent = Intent(this@MainActivity, FirstScreen::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}
