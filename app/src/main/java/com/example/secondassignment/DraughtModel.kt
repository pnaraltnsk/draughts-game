package com.example.secondassignment

import android.util.Log

class DraughtModel {
    var stones = mutableSetOf<DraughtPieces>()
    var player: Boolean = false
    var cap: Boolean = false
    var c_col: Int = -1
    var c_row: Int = -1
    init {
        default()

    }

    private fun default(){
        stones.removeAll(stones)
        for (row in 0 until 3){
            for (col in 0 until 8){
                if (row % 2 == 0 && col % 2 == 0){
                    stones.add(DraughtPieces(col , row, DraughtPlayer.BLUE))
                    stones.add(DraughtPieces(col+1 , 7-row, DraughtPlayer.RED))
                }
                if (row % 2 == 1 && col % 2 == 1){
                    stones.add(DraughtPieces(col , row, DraughtPlayer.BLUE))
                    stones.add(DraughtPieces(col-1 , 7-row, DraughtPlayer.RED))
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

        if(!cap){
            if ((fromStone?.player == DraughtPlayer.BLUE) && player)
                playerBlue(fromC,fromR,toC,toR,fromStone)

            if ((fromStone?.player == DraughtPlayer.RED) && !player)
                playerRed(fromC,fromR,toC,toR,fromStone)
        }
        else{
            if ((fromStone?.player == DraughtPlayer.BLUE) && player)
                chainBlue(fromC,fromR,toC,toR,fromStone)

            if ((fromStone?.player == DraughtPlayer.RED) && !player)
                chainRed(fromC,fromR,toC,toR,fromStone)
        }


    }

    fun playerBlue(fromC: Int, fromR: Int,toC: Int, toR: Int, fromStone: DraughtPieces){
        if(stonePos(toC,toR) == null){
            if (toR == fromR+1){
                if(toC == fromC-1 || toC == fromC+1){
                    stones.add(DraughtPieces(toC,toR,fromStone.player))
                    stones.remove(fromStone)
                    player = false}
            }
            else if (toR == fromR+2){
                if(toC == fromC-2){
                    if(stonePos(fromC-1,fromR+1)!= null ){
                        stonePos(fromC-1,fromR+1)?.let {
                            if (it.player == fromStone?.player)
                                return
                            stones.add(DraughtPieces(toC,toR,fromStone.player))
                            stones.remove(fromStone)
                            stones.remove(it)
                            if ((toC+2< 8 && toC-2 > 0 && toR < 8) && (stonePos(toC-1,toR+1)!= null && stonePos(toC-2,toR+2)== null) || (stonePos(toC+1,toR+1)!= null && stonePos(toC+2,toR+2)== null)){
                                cap = true
                                c_col = toC
                                c_row = toR
                            }
                            else
                                player = false
                            if(toR == 0)


                            Log.i("CHECKK2","("+ it.col+","+it.row+")")}


                    }
                }
                else if(toC == fromC+2){
                    if(stonePos(fromC+1,fromR+1)!= null){

                        stonePos(fromC+1,fromR+1)?.let {
                            if (it.player == fromStone?.player)
                                return
                            stones.add(DraughtPieces(toC,toR,fromStone.player))
                            stones.remove(fromStone)
                            stones.remove(it)
                            if ((toC+2< 8 && toC-2 > 0 && toR < 8) && (stonePos(toC-1,toR+1)!= null && stonePos(toC-2,toR+2)== null) || (stonePos(toC+1,toR+1)!= null && stonePos(toC+2,toR+2)== null)){
                                cap = true
                                c_col = toC
                                c_row = toR
                            }
                            else
                                player = false }
                    }
                }
            }
        }
    }
    fun playerRed(fromC: Int, fromR: Int,toC: Int, toR: Int, fromStone: DraughtPieces){
        if(stonePos(toC,toR) == null){
            if (toR == fromR-1){
                if(toC == fromC-1 || toC == fromC+1){
                    stones.add(DraughtPieces(toC,toR,fromStone.player))
                    stones.remove(fromStone)
                    player = true}
            }
            else if (toR == fromR-2){
                if(toC == fromC-2){
                    if(stonePos(fromC-1,fromR-1)!= null ){
                        stonePos(fromC-1,fromR-1)?.let {
                            if (it.player == fromStone?.player)
                                return
                            stones.add(DraughtPieces(toC,toR,fromStone.player))
                            stones.remove(fromStone)
                            stones.remove(it)
                            if ((toC+2 < 8 && toC-2 > 0 && toR > 0) && (stonePos(toC-1,toR-1)!= null && stonePos(toC-2,toR-2)== null) || (stonePos(toC+1,toR-1)!= null && stonePos(toC+2,toR-2)== null)){
                                cap = true
                                c_col = toC
                                c_row = toR
                            }
                            else
                                player = true
                            Log.i("CHECKK2","("+ it.col+","+it.row+")")}

                    }
                }
                else if(toC == fromC+2){
                    if(stonePos(fromC+1,fromR-1)!= null){

                        stonePos(fromC+1,fromR-1)?.let {
                            if (it.player == fromStone?.player)
                                return
                            stones.add(DraughtPieces(toC,toR,fromStone.player))
                            stones.remove(fromStone)
                            stones.remove(it)
                            if ((toC+2 < 8 && toC-2 > 0 && toR > 0) && (stonePos(toC-1,toR-1)!= null && stonePos(toC-2,toR-2)== null) || (stonePos(toC+1,toR-1)!= null && stonePos(toC+2,toR-2)== null)){
                                cap = true
                                c_col = toC
                                c_row = toR
                            }
                            else
                                player = true}

                    }
                }
            }

        }
    }


    fun chainBlue(fromC: Int, fromR: Int,toC: Int, toR: Int, fromStone: DraughtPieces){
        if(fromC == c_col && fromR == c_row && toR == fromR+2){
            if(toC == fromC-2){
                if(stonePos(fromC-1,fromR+1)!= null ){
                    stonePos(fromC-1,fromR+1)?.let {
                        if (it.player == fromStone?.player)
                            return
                        stones.add(DraughtPieces(toC,toR,fromStone.player))
                        stones.remove(fromStone)
                        stones.remove(it)
                        if ((toC+2 < 8 && toC-2 > 0 && toR < 8) && (stonePos(toC-1,toR+1)!= null && stonePos(toC-2,toR+2)== null) || (stonePos(toC+1,toR+1)!= null && stonePos(toC+2,toR+2)== null)){
                            cap = true
                            c_col = toC
                            c_row = toR
                        }
                        else{
                            player = false
                            cap = false}
                        Log.i("CHECKK2","("+ it.col+","+it.row+")")}
                }
            }
            else if(toC == fromC+2){
                if(stonePos(fromC+1,fromR+1)!= null){
                    stonePos(fromC+1,fromR+1)?.let {
                        if (it.player == fromStone?.player)
                            return
                        stones.add(DraughtPieces(toC,toR,fromStone.player))
                        stones.remove(fromStone)
                        stones.remove(it)
                        if ((toC+2< 8 && toC-2 > 0 && toR < 8) && (stonePos(toC-1,toR+1)!= null && stonePos(toC-2,toR+2)== null) || (stonePos(toC+1,toR+1)!= null && stonePos(toC+2,toR+2)== null)){
                            cap = true
                            c_col = toC
                            c_row = toR
                        }
                        else{
                            player = false
                            cap = false}}
                }
            }
        }
    }


    fun chainRed(fromC: Int, fromR: Int,toC: Int, toR: Int, fromStone: DraughtPieces){
        if(fromC == c_col && fromR == c_row && toR == fromR-2){
            if(toC == fromC-2){
                if(stonePos(fromC-1,fromR-1)!= null ){
                    stonePos(fromC-1,fromR-1)?.let {
                        if (it.player == fromStone?.player)
                            return
                        stones.add(DraughtPieces(toC,toR,fromStone.player))
                        stones.remove(fromStone)
                        stones.remove(it)
                        if ((toC+2 < 8 && toC-2 > 0 && toR > 0) && (stonePos(toC-1,toR-1)!= null && stonePos(toC-2,toR-2)== null) || (stonePos(toC+1,toR-1)!= null && stonePos(toC+2,toR-2)== null)){
                            cap = true
                            c_col = toC
                            c_row = toR
                        }
                        else{
                            player = false
                            cap = false}
                        Log.i("CHECKK2","("+ it.col+","+it.row+")")}

                }
            }
            else if(toC == fromC+2){
                if(stonePos(fromC+1,fromR-1)!= null){

                    stonePos(fromC+1,fromR-1)?.let {
                        if (it.player == fromStone?.player)
                            return
                        stones.add(DraughtPieces(toC,toR,fromStone.player))
                        stones.remove(fromStone)
                        stones.remove(it)
                        if ((toC+2 < 8 && toC-2 > 0 && toR > 0) && (stonePos(toC-1,toR-1)!= null && stonePos(toC-2,toR-2)== null) || (stonePos(toC+1,toR-1)!= null && stonePos(toC+2,toR-2)== null)){
                            cap = true
                            c_col = toC
                            c_row = toR
                        }
                        else{
                            player = false
                            cap = false}}
                }
            }
        }
    }

}