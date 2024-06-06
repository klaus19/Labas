package com.example.visuallithuanian

import android.view.inputmethod.InputMethodManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.visuallithuanian.databinding.FragmentSentenceBinding
import android.util.Log

class SentenceFragment : Fragment() {

    private lateinit var binding: FragmentSentenceBinding
    private lateinit var sharedPreferences: SharedPreferences

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
        }

        return binding.root
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

        val editor = sharedPreferences.edit()
        editor.putString("english_word", englishWord)
        editor.putString("lithuanian_word", lithuanianWord)
        editor.putString("english_phrase", englishPhrase)
        editor.putString("lithuanian_phrase", lithuanianPhrase)
        editor.apply()

        Toast.makeText(requireContext(), "Data saved", Toast.LENGTH_SHORT).show()

        // Log the saved data
        Log.d("SharedPreferences", "English Word: $englishWord")
        Log.d("SharedPreferences", "Lithuanian Word: $lithuanianWord")
        Log.d("SharedPreferences", "English Phrase: $englishPhrase")
        Log.d("SharedPreferences", "Lithuanian Phrase: $lithuanianPhrase")

        // Clear the EditText fields
        clearEditTextFields()
    }

    private fun clearEditTextFields() {
        binding.editWordEnglish.text.clear()
        binding.editWordLithuanian.text.clear()
        binding.englishPhrase.text.clear()
        binding.lithuanianPhrase.text.clear()
    }

}
