package com.example.visuallithuanian.custom

import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.LayoutParams
import com.example.visuallithuanian.R

class OverlappingLayoutManager(context: Context) : RecyclerView.LayoutManager() {
    private val horizontalOverlap =
        context.resources.getDimensionPixelOffset(R.dimen.card_horizontal_overlap) // Increase this value for more overlap
    private val verticalOverlap =
        context.resources.getDimensionPixelOffset(R.dimen.card_vertical_overlap) // Adjust if needed
    private val tiltAngle = 5f // Adjust the tilt angle as desired

    override fun generateDefaultLayoutParams(): LayoutParams {
        return LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
    }


    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        if (itemCount == 0) {
            removeAndRecycleAllViews(recycler!!)
            return
        }

        detachAndScrapAttachedViews(recycler!!)

        var currentTilt = tiltAngle

        for (position in 0 until itemCount) {
            val view = recycler.getViewForPosition(position)
            addView(view)
            measureChildWithMargins(view, 0, 0)
            val width = getDecoratedMeasuredWidth(view)
            val height = getDecoratedMeasuredHeight(view)

            // Calculate scale factor based on position
            val scaleFactor = 1f - (position * 0.1f) // Adjust the scaling formula as needed

            // Apply scale factor to layout measurements
            val scaledWidth = (width * scaleFactor).toInt()
            val scaledHeight = (height * scaleFactor).toInt()

            val centerX = scaledWidth / 2f
            val centerY = scaledHeight / 2f

            // Adjust layout positioning to account for scaling and overlap
            val left = (centerX - scaledWidth / 2).toInt() + (position * horizontalOverlap)
            val top = (centerY - scaledHeight / 2).toInt() + (position * verticalOverlap)
            val right = left + scaledWidth
            val bottom = top + scaledHeight

            layoutDecoratedWithMargins(view, left, top, right, bottom)

            // Set alternating tilt based on position
            val childViewGroup = view.findViewById<ViewGroup>(R.id.card_view)
            childViewGroup.rotation = if (position % 2 == 0) currentTilt else -currentTilt
        }
    }

    fun getTopPosition(): Int? {
        val topView = getChildAt(0)
        return if (topView != null) {
            val topPosition = getPosition(topView)
            topPosition.takeIf { it != RecyclerView.NO_POSITION }
        } else {
            null
        }
    }
}
