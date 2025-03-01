package com.example.visuallithuanian.ui.activities.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.Html
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.visuallithuanian.R
import com.example.visuallithuanian.custom.AvatarDialogFragment
import com.example.visuallithuanian.custom.ProgressDialog
import com.example.visuallithuanian.databinding.FragmentSettingsBinding
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.text.method.LinkMovementMethodCompat

class SettingsFragment : Fragment(), AvatarDialogFragment.AvatarSelectionListener {

    private lateinit var binding: FragmentSettingsBinding
    lateinit var bottomNav: BottomNavigationView
    private lateinit var sharedPreferences1: SharedPreferences

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)

        // Taking the BottomNavigation view instance from Activity into Fragment
        bottomNav = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        bottomNav.visibility = View.VISIBLE

        binding.imageAvatar.setOnClickListener {
            val dialog = AvatarDialogFragment()
            dialog.setAvatarSelectionListener(this)
            dialog.show(childFragmentManager, "AvatarDialogFragment")
        }

        // Retrieve the saved avatar resource ID from SharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("settings_prefs", Context.MODE_PRIVATE)
        val avatarResId = sharedPreferences.getInt("selected_avatar", R.drawable.avatar) // default avatar if none is selected
        binding.imageAvatar.setImageResource(avatarResId)

        // Navigating to Notification Fragment
        binding.cardviewNotify.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_notificationsFragment)
        }

        binding.cardviewAbout.setOnClickListener {
            showAboutDialog()
        }

        sharedPreferences1 = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

        binding.cardviewProgress.setOnClickListener {
            // Retrieve counters from SharedPreferences
            val counterToLearn = sharedPreferences1.getInt("counterToLearn", 0)
            val counterLearned = sharedPreferences1.getInt("counterLearned", 0)

            // Create a dialog with both click listeners
            val dialog = ProgressDialog.newInstance(
                counterToLearn,
                counterLearned,
                onButtonLearntClick = {
                    // Handle the buttonLearnt click here
                    findNavController().navigate(R.id.action_settingsFragment_to_fragmentLearnedWords)
                },
                onButtonToLearnClick = {
                    // Handle the buttonToLearn click here
                    findNavController().navigate(R.id.action_settingsFragment_to_fragmentToLearnWords)
                }
            )
            dialog.show(childFragmentManager, "ProgressDialog")
        }

        binding.cardviewDelete.setOnClickListener {
            showDeleteConfirmationDialog()
        }

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun showAboutDialog() {
        // Inflate the custom layout
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_about, null)

        // Create the AlertDialog
        val builder = AlertDialog.Builder(requireContext())
            .setTitle("About the App")
            .setView(dialogView)
            .setPositiveButton("OK", null)

        // Set the message with links (using HTML)
        val messageTextView: TextView = dialogView.findViewById(R.id.textViewMessage)
        val messageHtml = """
            My name is<a href="https://www.linkedin.com/in/tejas-khartude-7601b640/"> Tejas khartude</a>.This App is about learning Lithuanian words using Flashcards.
        Follow me on <a href="https://github.com/klaus19">GitHub</a>
        Bigger thanks to voice artist <a href = "https://www.linkedin.com/in/gabriele-jurkonyte/">Gabrielė Jurkonytė</a>.
    """.trimIndent()
        messageTextView.text = Html.fromHtml(messageHtml, Html.FROM_HTML_MODE_LEGACY)
        messageTextView.movementMethod = LinkMovementMethodCompat.getInstance()

        // Show the dialog
        val dialog = builder.create()
        dialog.show()
    }


    private fun showDeleteConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Warning")
        builder.setMessage("Do you want to delete all the data? All the progress will be reset.")
        builder.setPositiveButton("YES") { dialog, which ->
            resetAppData()
        }
        builder.setNegativeButton("CANCEL", null)
        val dialog = builder.create()
        dialog.show()
    }

    private fun resetAppData() {
        // Clear all relevant shared preferences
        val sharedPreferences1 = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val sharedPreferences2 = requireActivity().getSharedPreferences("settings_prefs", Context.MODE_PRIVATE)

        sharedPreferences1.edit().clear().apply()
        sharedPreferences2.edit().clear().apply()

        // Display a toast message to inform the user
        Toast.makeText(requireContext(), "All data has been deleted.", Toast.LENGTH_SHORT).show()

        // Open the app details screen in system settings
        openAppDetails()
    }

    private fun openAppDetails() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", requireActivity().packageName, null)
        intent.data = uri
        startActivity(intent)
    }

    override fun onAvatarSelected(avatarResId: Int) {
        binding.imageAvatar.setImageResource(avatarResId)

        // Save the selected avatar resource ID in SharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("settings_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("selected_avatar", avatarResId)
        editor.apply()
    }
}
