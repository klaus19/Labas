package com.example.visuallithuanian.custom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.visuallithuanian.databinding.DialogCustomButtonsBinding

class ProgressDialog:DialogFragment() {

    private lateinit var binding: DialogCustomButtonsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogCustomButtonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve arguments
        val counterToLearn = arguments?.getInt("counterToLearn") ?: 0
        val counterLearned = arguments?.getInt("counterLearned") ?: 0

        // Set text values
        binding.textToLearnCount.text = counterToLearn.toString()
        binding.textLearntCount.text = counterLearned.toString()
    }

    override fun onStart() {
        super.onStart()
        // Set dialog width and height
        val dialogWidth = ViewGroup.LayoutParams.MATCH_PARENT
        val dialogHeight = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.setLayout(dialogWidth, dialogHeight)
    }

    companion object {
        fun newInstance(counterToLearn: Int, counterLearned: Int): ProgressDialog {
            val dialog = ProgressDialog()
            val args = Bundle().apply {
                putInt("counterToLearn", counterToLearn)
                putInt("counterLearned", counterLearned)
            }
            dialog.arguments = args
            return dialog
        }
    }
}