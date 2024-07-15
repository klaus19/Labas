package com.example.visuallithuanian.custom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.example.visuallithuanian.R

class AvatarDialogFragment:DialogFragment() {

    interface AvatarSelectionListener {
        fun onAvatarSelected(avatarResId: Int)
    }

    private var listener: AvatarSelectionListener? = null

    fun setAvatarSelectionListener(listener: AvatarSelectionListener) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_avatar_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ImageView>(R.id.avatar1).setOnClickListener { selectAvatar(R.drawable.fox1) }
        view.findViewById<ImageView>(R.id.avatar2).setOnClickListener { selectAvatar(R.drawable.unicorn1) }
        view.findViewById<ImageView>(R.id.avatar3).setOnClickListener { selectAvatar(R.drawable.girl1) }
        view.findViewById<ImageView>(R.id.avatar4).setOnClickListener { selectAvatar(R.drawable.boy1) }
    }

    private fun selectAvatar(avatarResId: Int) {
        listener?.onAvatarSelected(avatarResId)
        dismiss()
    }


}