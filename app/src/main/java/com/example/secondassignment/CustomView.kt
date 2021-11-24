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
    private lateinit var _red : Paint
    private lateinit var _blue : Paint
    private var _pointers: Int = 0
    private var _touch_x: HashMap<Int, Float> = HashMap<Int, Float>()
    private var _touch_y: HashMap<Int, Float> = HashMap<Int, Float>()


    var draughtsInterface : DraughtsInterface? = null

    init{
        _red = Paint(Paint.ANTI_ALIAS_FLAG)
        _blue = Paint(Paint.ANTI_ALIAS_FLAG)
        _red.setColor(Color.argb(255, 255, 0, 0))
        _blue.setColor(Color.argb(255, 0, 0, 255))
    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        val boardSide = min(width, height) * scaleFactor
        side = boardSide / 8f
        originX = (width - boardSide) / 2f
        originY = (height - boardSide) / 2f

        drawBoard(canvas)
        drawPieces(canvas)
    }

    private fun drawBoard(canvas: Canvas) {
        for (row in 0 until 8)
            for (col in 0 until 8)
                drawSquare(canvas, col, row, (col + row) % 2 == 1)
    }

    private fun drawSquare(canvas: Canvas, col: Int, row: Int, isDark: Boolean) {
        paint.color = if (isDark) darkGray else lightGray
        canvas.drawRect(originX + col * side, originY + row * side, originX + (col + 1)* side, originY + (row + 1) * side, paint)
    }

    private fun drawPieces(canvas: Canvas) {
        for (row in 0 until 8){
            for (col in 0 until 8 ){
                //if (row % 2 == 0 && col % 2 == 0){
                val stn = draughtsInterface?.stonePos(col,row)
                if(stn != null){
                    if (stn.player == DraughtPlayer.BLUE)
                        canvas.drawCircle(originX + (col * side) + (side/2), originY + (row * side) + (side/2), side/2, _blue)
                    if (stn.player == DraughtPlayer.RED)
                        canvas.drawCircle(originX + (col * side) + (side/2), originY + ((row) * side) + (side/2), side/2, _red)
                }

                //if (row % 2 == 1 && col % 2 == 1){
                //    canvas.drawCircle(originX + (col * side) + (side/2), originY + (row * side) + (side/2), side/2, _blue)
                //    canvas.drawCircle(originX + (col * side) + (side/2), originY + ((7-row) * side) + (side/2), side/2, _red)
                //}
            }
        }
    }

    private fun drawSingleTouch(canvas: Canvas?) {
        for(key in _touch_x.keys) {
            canvas?.save()
            canvas?.translate(_touch_x[key]!!, _touch_y[key]!!)

            canvas?.restore()
        }
    }

}