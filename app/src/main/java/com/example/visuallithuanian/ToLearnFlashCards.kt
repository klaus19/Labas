package com.example.visuallithuanian

import android.Manifest
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.adapter.FlashcardpaiAdapter
import com.example.visuallithuanian.custom.OverlappingLayoutManager
import com.example.visuallithuanian.databinding.FragmentToLearnFlashCardsBinding
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.example.visuallithuanian.viewModel.FlashCardViewmodel
import com.example.visuallithuanian.viewModel.WordViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


class ToLearnFlashCards : Fragment() {

    private lateinit var binding:FragmentToLearnFlashCardsBinding
    private lateinit var layoutManager: OverlappingLayoutManager

    // for Integrsting Speech Recognation
    private lateinit var speechRecognizer: SpeechRecognizer
    private val REQUEST_CODE_SPEECH_PERMISSION=1
    private val REQUEST_CODE_SPEECH_INPUT=2


    lateinit var bottomNav:BottomNavigationView
    val cardViewmodel:  FlashCardViewmodel by viewModels{
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentToLearnFlashCardsBinding.inflate(layoutInflater,container,false)

        //Taking the bOTTOMNavigation view instance from Activity into Fragment
        bottomNav = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        bottomNav.visibility = View.VISIBLE

        binding.backIcon.setOnClickListener {
           findNavController().navigate(R.id.action_toLearnFlashCards_to_flashCards)

        }

        binding.recyclerview.layoutManager = OverlappingLayoutManager(requireContext())


        layoutManager = OverlappingLayoutManager(requireContext())

        binding.recyclerview.layoutManager = OverlappingLayoutManager(requireContext())

        // Request necessary permissions
        requestSpeechPermissions()

        binding.imageSpeech.setOnClickListener {

            startSpeechRecognition()
        }

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext())
        speechRecognizer.setRecognitionListener(SpeechRecogintionListener())


        val adapter = FlashcardpaiAdapter{cardPair->
            cardViewmodel.deleteCards(cardPair)

        }


        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.adapter = adapter

//        binding.imageSpeech.setOnClickListener {
//            val lastCard = adapter.currentList.lastOrNull()
//            val text = lastCard?.front
//            if (!text.isNullOrEmpty()) {
//                Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
//                Log.d("Card", "$text")
//            }
//            }


        //Swipe Gesture

        val itemTouchHelper = ItemTouchHelper(object :ItemTouchHelper.SimpleCallback(
            0,ItemTouchHelper.LEFT
        ){

            private val SWIPE_FACTOR=0f
            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView

                // Calculate rotation angle based on swipe distance
                val rotation = dX * -0.2f // Adjust this rotation factor as needed

                // Apply rotation transformation to the card view
                itemView.pivotX = itemView.width.toFloat()
                itemView.pivotY = itemView.height.toFloat() / 2
                itemView.rotation = rotation
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val cardPair = adapter.currentList[position]
                // Handle swipe left to delete the card
                cardViewmodel.deleteCards(cardPair)
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.recyclerview)

        binding.recyclerview.layoutManager = OverlappingLayoutManager(requireContext())
      //  binding.recyclerview.rotation=10f

        //Observe  the data changes for the items added
        cardViewmodel.allWords.observe(requireActivity()) { cardPairs ->

                adapter.submitList(cardPairs)

                if (cardPairs.isEmpty()){
                    binding.emptyImage.visibility = View.VISIBLE
                    binding.imageKeyboard.visibility = View.GONE
                    binding.imageSpeech.visibility = View.GONE
                }else{
                    binding.emptyImage.visibility = View.GONE
                    binding.imageSpeech.visibility = View.VISIBLE
                    binding.imageKeyboard.visibility = View.VISIBLE
                }
            }

        return binding.root
    }

    private fun requestSpeechPermissions() {
        val permissions = arrayOf(Manifest.permission.RECORD_AUDIO)
        ActivityCompat.requestPermissions(requireActivity(),permissions,REQUEST_CODE_SPEECH_PERMISSION)
    }

    private fun startSpeechRecognition() {
        val speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        startActivityForResult(speechIntent,REQUEST_CODE_SPEECH_INPUT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode ==REQUEST_CODE_SPEECH_INPUT && resultCode== AppCompatActivity.RESULT_OK && data!=null){
            val results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val spokenTest = results?.get(0)?:""

            compareSpeech(spokenTest)
        }
    }

    private fun compareSpeech(spokenTest: String) {
        val targetWord = view?.findViewById<TextView>(R.id.textView2)

        if (spokenTest.equals(targetWord)){

            Toast.makeText(requireContext(),"Correct spelling", Toast.LENGTH_SHORT).show()

        }else{
            Toast.makeText(requireContext(),"InCorrect", Toast.LENGTH_SHORT).show()
        }
    }

    private inner class SpeechRecogintionListener: RecognitionListener {
        override fun onReadyForSpeech(params: Bundle?) {

        }

        override fun onBeginningOfSpeech() {

        }

        override fun onRmsChanged(rmsdB: Float) {

        }

        override fun onBufferReceived(buffer: ByteArray?) {

        }

        override fun onEndOfSpeech() {

        }

        override fun onError(error: Int) {

        }

        override fun onResults(results: Bundle?) {

        }

        override fun onPartialResults(partialResults: Bundle?) {

        }

        override fun onEvent(eventType: Int, params: Bundle?) {

        }

    }

}