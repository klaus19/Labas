package com.example.visuallithuanian

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.visuallithuanian.databinding.FragmentSentenceBinding
import com.example.visuallithuanian.utils.Notification1
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SentenceFragment : Fragment() {

    private lateinit var binding: FragmentSentenceBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSentenceBinding.inflate(inflater, container, false)

        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        binding.btnNotify.setOnClickListener {
            saveDataToSharedPreferences()
            hideKeyboard()
            scheduledNotifications()
        }

        binding.backIcon.setOnClickListener {
            activity?.onBackPressed()
        }

        return binding.root
    }

    @SuppressLint("ScheduleExactAlarm")
    private fun scheduledNotifications() {
        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // Schedule notifications at random times throughout the day
        for (i in 1..3) { // Change this to the number of notifications you want per day
            val intent = Intent(requireContext(), Notification1::class.java)
            val pendingIntent = PendingIntent.getBroadcast(requireContext(), i, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

            // Random time in the next 24 hours
            val triggerTime = System.currentTimeMillis() + (Math.random() * 24 * 60 * 60 * 1000).toLong()

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
        }
    }

    private fun hideKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun saveDataToSharedPreferences() {
        val englishWord = binding.editWordEnglish.text.toString()
        val lithuanianWord = binding.editWordLithuanian.text.toString()
        val englishPhrase = binding.englishPhrase.text.toString()
        val lithuanianPhrase = binding.lithuanianPhrase.text.toString()

        val phrase = Phrase(englishWord, lithuanianWord, englishPhrase, lithuanianPhrase)

        val phrasesList = getPhrasesListFromSharedPreferences().toMutableList()
        phrasesList.add(phrase)

        val editor = sharedPreferences.edit()
        editor.putString("phrases_list", gson.toJson(phrasesList))
        editor.apply()

        Toast.makeText(requireContext(), "Data saved", Toast.LENGTH_SHORT).show()

        // Clear the EditText fields
        clearEditTextFields()
    }

    private fun clearEditTextFields() {
        binding.editWordEnglish.text.clear()
        binding.editWordLithuanian.text.clear()
        binding.englishPhrase.text.clear()
        binding.lithuanianPhrase.text.clear()
    }

    private fun getPhrasesListFromSharedPreferences(): List<Phrase> {
        val json = sharedPreferences.getString("phrases_list", null)
        return if (json != null) {
            val type = object : TypeToken<List<Phrase>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }

    data class Phrase(
        val englishWord: String,
        val lithuanianWord: String,
        val englishPhrase: String,
        val lithuanianPhrase: String
    )
}
