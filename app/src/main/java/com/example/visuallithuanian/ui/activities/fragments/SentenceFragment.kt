package com.example.visuallithuanian.ui.activities.fragments

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
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

        setupEditTextListeners()

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

        if (englishWord.isEmpty() || lithuanianWord.isEmpty() || englishPhrase.isEmpty() || lithuanianPhrase.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

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

    private fun setupEditTextListeners() {
        binding.editWordLithuanian.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                checkAndPromptLithuanianKeyboard()
            }
        }

        binding.lithuanianPhrase.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                checkAndPromptLithuanianKeyboard()
            }
        }
    }

    private fun checkAndPromptLithuanianKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val inputMethodList = imm.enabledInputMethodList
        val lithuanianKeyboardInstalled = inputMethodList.any { it.packageName.contains("lithuanian", ignoreCase = true) }

        if (!lithuanianKeyboardInstalled) {
            showKeyboardDownloadDialog()
        }
    }

    private fun showKeyboardDownloadDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Lithuanian Keyboard Not Installed")
        builder.setMessage("It looks like you don't have a Lithuanian keyboard installed. Would you like to download one from the Play Store?")
        builder.setPositiveButton("Yes") { _, _ ->
            val appPackageName = "com.google.android.inputmethod.latin" // Example package name for Gboard which supports multiple languages
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
            } catch (e: android.content.ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
            }
        }
        builder.setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
        builder.show()
    }

    data class Phrase(
        val englishWord: String,
        val lithuanianWord: String,
        val englishPhrase: String,
        val lithuanianPhrase: String
    )
}
