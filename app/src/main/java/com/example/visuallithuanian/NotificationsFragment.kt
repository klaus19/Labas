package com.example.visuallithuanian

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.work.*
import com.example.visuallithuanian.databinding.FragmentNotificationsBinding
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.utils.NotificationWorker
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.concurrent.TimeUnit

class NotificationsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationsBinding
    lateinit var bottomNav: BottomNavigationView
    private lateinit var sharedPreferences: SharedPreferences

    private var selectedCardViewId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        sharedPreferences = requireActivity().getSharedPreferences("NotificationsPrefs", Context.MODE_PRIVATE)

        // Taking the BottomNavigation view instance from Activity into Fragment
        bottomNav = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        bottomNav.visibility = View.VISIBLE

        binding.imageNotifyBack.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.cardviewTurnOffNotifications.setOnClickListener {
            selectCardView(binding.cardviewTurnOffNotifications)
            cancelNotifications()
        }

        binding.cardviewNotMuch.setOnClickListener {
            selectCardView(binding.cardviewNotMuch)
            scheduleNotification(2)
        }

        binding.cardviewRegular.setOnClickListener {
            selectCardView(binding.cardviewRegular)
            scheduleNotification(1)
        }

        // Restore the saved color state
        restoreSavedColor()

        return binding.root
    }

    private fun cancelNotifications() {
        val workManager = WorkManager.getInstance(requireContext())
        workManager.cancelAllWork()
    }

    private fun selectCardView(cardView: CardView) {
        // Reset the background color of previously selected card view
        selectedCardViewId?.let {
            val previousSelectedCardView = binding.root.findViewById<CardView>(it)
            previousSelectedCardView?.setCardBackgroundColor(Color.WHITE)
        }

        // Change the background color of the selected card view
        cardView.setCardBackgroundColor(Color.parseColor("#ef5350"))

        // Save the selected card view ID
        selectedCardViewId = cardView.id
        saveSelectedColor(cardView.id, Color.parseColor("#ef5350"))
    }

    private fun saveSelectedColor(cardViewId: Int, color: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt("selectedCardViewId", cardViewId)
        editor.putInt("selectedColor", color)
        editor.apply()
    }

    private fun restoreSavedColor() {
        val defaultColor = Color.WHITE
        val savedCardViewId = sharedPreferences.getInt("selectedCardViewId", -1)
        val savedColor = sharedPreferences.getInt("selectedColor", defaultColor)

        if (savedCardViewId != -1) {
            val savedCardView = binding.root.findViewById<CardView>(savedCardViewId)
            savedCardView?.setCardBackgroundColor(savedColor)
            selectedCardViewId = savedCardViewId
        }
    }

    private fun scheduleNotification(days: Int) {
        val workManager = WorkManager.getInstance(requireContext())

        val notificationWorkRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInitialDelay(days.toLong(), TimeUnit.DAYS)
            .build()

        workManager.enqueue(notificationWorkRequest)
    }
}
