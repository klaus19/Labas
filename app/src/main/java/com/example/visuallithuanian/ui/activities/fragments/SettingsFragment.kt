package com.example.visuallithuanian.ui.activities.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.SpinnerAdapter
import com.example.visuallithuanian.custom.AvatarDialogFragment
import com.example.visuallithuanian.databinding.FragmentSettingsBinding
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale


class SettingsFragment : Fragment(),AvatarDialogFragment.AvatarSelectionListener {

    private lateinit var binding:FragmentSettingsBinding
    lateinit var bottomNav: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater,container,false)


        // Taking the BottomNavigation view instance from Activity into Fragment
        bottomNav = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        bottomNav.visibility = View.VISIBLE

        binding.imageAvatar.setOnClickListener {
            val dialog = AvatarDialogFragment()
            dialog.setAvatarSelectionListener(this)
            dialog.show(childFragmentManager,"AvatarDialogFragment")
        }

        // Retrieve the saved avatar resource ID from SharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("settings_prefs", Context.MODE_PRIVATE)
        val avatarResId = sharedPreferences.getInt("selected_avatar", R.drawable.avatar) // default avatar if none is selected
        binding.imageAvatar.setImageResource(avatarResId)


        binding.cardviewNotify.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_notificationsFragment)
        }

        binding.cardviewProgress.setOnClickListener {
            showProgressDialog()
        }


        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun showProgressDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_custom_buttons, null)

        val builder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        val buttonToLearn = dialogView.findViewById<LinearLayout>(R.id.buttonToLearn)
        val buttonLearnt = dialogView.findViewById<LinearLayout>(R.id.buttonLearnt)
        val textToLearnCount = dialogView.findViewById<TextView>(R.id.textToLearnCount)
        val textLearntCount = dialogView.findViewById<TextView>(R.id.textLearntCount)

        // Set your actual counts here
        textToLearnCount.text = "0" // Replace with actual count
        textLearntCount.text = "0" // Replace with actual count

        buttonToLearn.setOnClickListener {
            Toast.makeText(requireContext(), "ToLearn clicked", Toast.LENGTH_SHORT).show()
            builder.dismiss()
        }

        buttonLearnt.setOnClickListener {
            Toast.makeText(requireContext(), "Learnt clicked", Toast.LENGTH_SHORT).show()
            builder.dismiss()
        }

        builder.show()
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