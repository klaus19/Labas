package com.example.visuallithuanian.custom

import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.LayoutParams
import com.example.visuallithuanian.R


class OverlappingLayoutManager(context: Context) : RecyclerView.LayoutManager() {
    private val horizontalOverlap =
        context.resources.getDimensionPixelOffset(R.dimen.card_horizontal_overlap)
    private val verticalOverlap =
        context.resources.getDimensionPixelOffset(R.dimen.card_vertical_overlap)
    private val tiltAngle = 3f // Adjust the tilt angle as needed

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

        val leftTilt = tiltAngle
        val rightTilt = -tiltAngle
        var currentTilt = leftTilt

        for (position in 0 until itemCount) {
            val view = recycler.getViewForPosition(position)
            addView(view)
            measureChildWithMargins(view, 0, 0)
            val width = getDecoratedMeasuredWidth(view)
            val height = getDecoratedMeasuredHeight(view)

            val centerX = width / 2f
            val centerY = height / 2f

            layoutDecoratedWithMargins(
                view,
                (centerX - (width / 2)).toInt(),
                (centerY - (height / 2)).toInt(),
                (centerX + (width / 2)).toInt(),
                (centerY + (height / 2)).toInt()
            )

            val childViewGroup = view.findViewById<ViewGroup>(R.id.card_view)
            childViewGroup.rotation = currentTilt

            currentTilt = if (currentTilt == leftTilt) rightTilt else leftTilt
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