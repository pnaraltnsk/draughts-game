package com.example.secondassignment

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.lang.Double.min
import kotlin.math.min

class CustomView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val scaleFactor = 1.0f
    private final var originX = 20f
    private final var originY = 200f
    private final var side = 130f
    private val lightGray = Color.LTGRAY
    private val darkGray = Color.DKGRAY
    private val paint = Paint()
    private lateinit var _green: Paint
    private lateinit var _blue: Paint

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        val boardSide = min(width, height) * scaleFactor
        side = boardSide / 8f
        originX = (width - chessBoardSide) / 2f
        originY = (height - chessBoardSide) / 2f

        drawBoard(canvas)
    }

    private fun drawBoard(canvas: Canvas) {
        for (row in 0 until 8)
            for (col in 0 until 8)
                drawSquare(canvas, col, row, (col + row) % 2 == 1)
    }

    private fun drawSquare(canvas: Canvas, col: Int, row: Int, isDark: Boolean) {
        paint.color = if (isDark) darkGray else lightGray
        canvas.drawRect(originX + col * cellSide, originY + row * cellSide, originX + (col + 1)* cellSide, originY + (row + 1) * cellSide, paint)
    }

}