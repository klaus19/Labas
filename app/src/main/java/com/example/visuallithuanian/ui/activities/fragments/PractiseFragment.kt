package com.example.visuallithuanian.ui.activities.fragments


import android.annotation.SuppressLint
import com.example.visuallithuanian.adapter.PractiseAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.visuallithuanian.R
import com.example.visuallithuanian.Utils.shuffleList
import com.example.visuallithuanian.constants.ImageStore
import com.example.visuallithuanian.databinding.FragmentPractiseBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PractiseFragment : Fragment() {
    lateinit var binding: FragmentPractiseBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentPractiseBinding.inflate(layoutInflater,container,false)


        val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.recyclerViewPractise.layoutManager = layoutManager

        // Shuffle the list of image names
        val shuffledImageNames = ImageStore.imagesNamesMap.values.toList().shuffleList()
        val shuffledImageResources = ImageStore.imagesNamesMap.keys.toList().shuffleList()


        val adapter = PractiseAdapter(shuffledImageResources,shuffledImageNames)
        binding.recyclerViewPractise.adapter = adapter



        return binding.root

    }

}