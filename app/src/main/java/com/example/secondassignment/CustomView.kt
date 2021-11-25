package com.example.secondassignment

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import java.lang.Double.min
import kotlin.math.min

class CustomView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val scaleFactor = 1.0f
    private final var xx = 20f
    private final var yy = 200f
    private final var side = 130f
    private val lightGray = Color.LTGRAY
    private val darkGray = Color.DKGRAY
    private val paint = Paint()
    private lateinit var _red : Paint
    private lateinit var _blue : Paint
    private var _pointers: Int = 0
    private var f_col: Int = -1
    private var f_row: Int = -1

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
        xx = (width - boardSide) / 2f
        yy = (height - boardSide) / 2f

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
        canvas.drawRect(xx + col * side, yy + row * side, xx + (col + 1)* side, yy + (row + 1) * side, paint)
    }

    private fun drawPieces(canvas: Canvas) {
        for (row in 0 until 8){
            for (col in 0 until 8 ){
                val stn = draughtsInterface?.stonePos(col,row)
                if(stn != null){
                    if (stn.player == DraughtPlayer.BLUE)
                        canvas.drawCircle(xx + (col * side) + (side/2), yy + (row * side) + (side/2), side/2, _blue)
                    if (stn.player == DraughtPlayer.RED)
                        canvas.drawCircle(xx + (col * side) + (side/2), yy + ((row) * side) + (side/2), side/2, _red)
                }


            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return false

        when ( event.action){
            MotionEvent.ACTION_DOWN -> {
                f_col = ((event.x - xx) / side).toInt()
                f_row =  ((event.y - yy) / side).toInt()
                //Log.i("CHECK","("+f_col+","+f_row+")")


            }
            MotionEvent.ACTION_UP -> {
                val col = ((event.x - xx) / side).toInt()
                val row = ((event.y - yy) / side).toInt()
                val p = draughtsInterface?.stonePos(f_col,f_row)

                draughtsInterface?.move(f_col,f_row,col,row)
                //Log.i("TAGG","from"+f_col+","+f_row+"to"+col+","+row)

            }
            MotionEvent.ACTION_MOVE -> {

            }
        }
        return true
    }




}