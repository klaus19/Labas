package com.example.visuallithuanian.base

import android.os.Handler
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel

class BottomNavigationScrollListener(
    private val viewModel: BottomNavigationViewModel
):RecyclerView.OnScrollListener() {

    private var isScrolling = false
    private val handler = Handler()    // This assures that hnadler is iniyialised only once

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0 || dy < 0) {
            viewModel.setBottomNavigationVisibility(false)
            if (!isScrolling) {
                isScrolling = true
                handler.postDelayed({
                    if (isScrolling) {
                        viewModel.setBottomNavigationVisibility(true)
                        isScrolling = false
                    }
                }, 2000)
            }
        }
    }

}