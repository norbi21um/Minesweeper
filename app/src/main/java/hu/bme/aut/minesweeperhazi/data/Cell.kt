package hu.bme.aut.minesweeperhazi.data

import androidx.room.PrimaryKey

data class Cell (
    var state:Int,
    var isFlagged: Boolean,
    var isOpened:Boolean
)