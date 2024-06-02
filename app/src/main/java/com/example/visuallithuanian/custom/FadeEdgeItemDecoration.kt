package com.example.visuallithuanian.custom

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.visuallithuanian.R

class FadeEdgeItemDecoration(private val context: android.content.Context) : RecyclerView.ItemDecoration() {

    private val fadeWidth = 50 // Width of the fade effect in pixels
    private val paint = Paint()

    init {
        paint.isAntiAlias = true
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val itemCount = parent.childCount
        for (i in 0 until itemCount) {
            val child = parent.getChildAt(i)
            val childLeft = child.left
            val childRight = child.right

            // Apply fade to left edge
            if (childLeft < fadeWidth) {
                val fadeRect = Rect(0, child.top, childLeft + fadeWidth, child.bottom)
                paint.shader = android.graphics.LinearGradient(
                    fadeRect.left.toFloat(), 0f, fadeRect.right.toFloat(), 0f,
                    intArrayOf(ContextCompat.getColor(context, R.color.transparent), ContextCompat.getColor(context, R.color.white)),
                    null,
                    android.graphics.Shader.TileMode.CLAMP
                )
                c.drawRect(fadeRect, paint)
            }

            // Apply fade to right edge
            if (childRight > parent.width - fadeWidth) {
                val fadeRect = Rect(childRight - fadeWidth, child.top, parent.width, child.bottom)
                paint.shader = android.graphics.LinearGradient(
                    fadeRect.left.toFloat(), 0f, fadeRect.right.toFloat(), 0f,
                    intArrayOf(ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.transparent)),
                    null,
                    android.graphics.Shader.TileMode.CLAMP
                )
                c.drawRect(fadeRect, paint)
            }
        }
    }
}
