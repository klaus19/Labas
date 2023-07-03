package com.example.visuallithuanian.custom

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class OverlappingLayoutManager: RecyclerView.LayoutManager() {

    private val overlapAmount: Int = 50 // Adjust the overlap amount as needed

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        if (itemCount == 0) {
            removeAndRecycleAllViews(recycler)
            return
        }

        detachAndScrapAttachedViews(recycler)

        val parentWidth = width
        val parentHeight = height

        var left = paddingLeft
        var top = paddingTop

        for (position in 0 until itemCount) {
            val view: View = recycler.getViewForPosition(position)
            addView(view)

            measureChildWithMargins(view, 0, 0)

            val width: Int = getDecoratedMeasuredWidth(view)
            val height: Int = getDecoratedMeasuredHeight(view)

            layoutDecoratedWithMargins(view, left, top, left + width, top + height)

            left += overlapAmount
            top += overlapAmount
        }
    }

    override fun canScrollVertically(): Boolean {
        return true
    }



    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler, state: RecyclerView.State): Int {
        if (childCount == 0 || dy == 0) {
            return 0
        }

        val scrolled = super.scrollVerticallyBy(dy, recycler, state)

        offsetChildrenVertical(-scrolled)

        return scrolled
    }


}