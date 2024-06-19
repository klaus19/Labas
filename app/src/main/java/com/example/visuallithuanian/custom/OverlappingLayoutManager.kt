
package com.example.visuallithuanian.custom
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import androidx.annotation.Dimension
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R

class OverlappingLayoutManager(context: Context) : RecyclerView.LayoutManager() {

    private val horizontalOverlap: Float =
        context.resources.getDimension(R.dimen.card_horizontal_overlap)
    private val verticalOverlap: Float =
        context.resources.getDimension(R.dimen.card_vertical_overlap)
    @get:Dimension
    private val tiltAngle: Float =
        context.resources.getDimension(R.dimen.card_tilt_angle)
    private var interpolator: Interpolator = LinearInterpolator() // Use an interpolator for smooth tilting

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State?) {
        if (itemCount == 0) {
            removeAndRecycleAllViews(recycler)
            return
        }

        detachAndScrapAttachedViews(recycler)

        val parentWidth = width
        val parentHeight = height

        var zIndex = 0

        for (position in 0 until itemCount) {
            val view = recycler.getViewForPosition(position)
            addView(view)

            // Set z-index for the view
            val elevation = zIndex.toFloat()
            ViewCompat.setElevation(view, elevation)
            zIndex++

            measureChildWithMargins(view, 0, 0)
            val width = getDecoratedMeasuredWidth(view)
            val height = getDecoratedMeasuredHeight(view)

            val centerX = parentWidth / 2f
            val centerY = parentHeight / 2f

            val itemOffset = calculateItemOffset(position)
            val newCenterX = centerX + itemOffset * horizontalOverlap

            val left = (newCenterX - (width / 2)).toInt()
            val top = (centerY - (height / 2)).toInt()
            val right = (newCenterX + (width / 2)).toInt()
            val bottom = (centerY + (height / 2)).toInt()

            layoutDecoratedWithMargins(view, left, top, right, bottom)

            applyTilt(view, position)
        }
    }

    private fun applyTilt(view: View, position: Int) {
        val tilt = calculateTilt(position) * tiltAngle
        view.pivotX = (view.width / 2).toFloat()
        view.pivotY = (view.height / 2).toFloat()
        view.rotation = tilt
    }

    protected open fun calculateItemOffset(position: Int): Float {
        return if (position % 2 == 0) -1f else 1f
    }

    protected open fun calculateTilt(position: Int): Float {
        return if (position % 2 == 0) -1f else 1f
    }

    fun setInterpolator(interpolator: Interpolator) {
        this.interpolator = interpolator
    }
}