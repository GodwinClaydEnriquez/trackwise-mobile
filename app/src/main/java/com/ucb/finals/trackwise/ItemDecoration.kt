package com.ucb.finals.trackwise

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class TableItemDecoration : RecyclerView.ItemDecoration() {

    private val paint = Paint().apply {
        color = 0xFF000000.toInt() // black color
        strokeWidth = 2f
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val childCount = parent.childCount
        val child = parent.getChildAt(0)
        val itemWidth = child?.width ?: 0
        val itemHeight = child?.height ?: 0

        for (i in 0 until childCount) {
            val currentChild = parent.getChildAt(i)
            val left = currentChild.left.toFloat()
            val top = currentChild.top.toFloat()
            val right = currentChild.right.toFloat()
            val bottom = currentChild.bottom.toFloat()

            c.drawLine(left, top, right, top, paint)
            c.drawLine(left, bottom, right, bottom, paint)

            for (j in 0..itemWidth step itemWidth / 8) {
                c.drawLine(left + j, top, left + j, bottom, paint)
            }
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(2, 2, 2, 2)
    }
}
