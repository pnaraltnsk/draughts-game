package com.example.secondassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity(), DraughtsInterface {

    var draughts = DraughtModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val draughtsV = findViewById<CustomView>(R.id.customview)
        draughtsV.draughtsInterface = this

        findViewById<Button>(R.id.resetbtn).setOnClickListener{
            draughts.default()
            draughtsV.invalidate()
        }
    }

    override fun stonePos(col: Int, row: Int): DraughtPieces? {
        return draughts.stonePos(col,row)
    }

    override fun move(fromC: Int, fromR: Int, toC: Int, toR: Int) {
        draughts.move(fromC,fromR,toC,toR)
        val draughtsV = findViewById<CustomView>(R.id.customview)
        draughtsV.invalidate()
    }


}