package com.example.secondassignment

interface DraughtsInterface {
    fun stonePos(col: Int, row: Int): DraughtPieces?
    fun move(fromC: Int, fromR: Int,toC: Int, toR: Int)
}