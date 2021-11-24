package com.example.secondassignment

class DraughtModel {
    var stones = mutableSetOf<DraughtPieces>()

    init {
        default()

    }

    private fun default(){
        stones.removeAll(stones)
        for (row in 0 until 3){
            for (col in 0 until 8){
                if (row % 2 == 0 && col % 2 == 0){
                    stones.add(DraughtPieces(col , row, DraughtPlayer.BLUE))
                    stones.add(DraughtPieces(col , 7-row, DraughtPlayer.RED))
                }
                if (row % 2 == 1 && col % 2 == 1){
                    stones.add(DraughtPieces(col , row, DraughtPlayer.BLUE))
                    stones.add(DraughtPieces(col , 7-row, DraughtPlayer.RED))
                }

            }
        }
    }

    fun stonePos(col: Int, row: Int): DraughtPieces?{
        for (p in stones){
            if(col == p.col && row == p.row)
                return p
        }
        return null
    }

    fun move(fromC: Int, fromR: Int,toC: Int, toR: Int){
        var fromStone = stonePos(fromC,fromR) ?: null

        stonePos(toC,toR)?.let {
            if (it.player == fromStone?.player)
                return
            stones.remove(it) }
        fromStone?.col = toC
        fromStone?.row = toR
    }
}